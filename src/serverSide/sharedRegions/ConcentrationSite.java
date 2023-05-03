package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import clientSide.stubs.*;
import commInfra.*;
import genclass.GenericIO;


/**
 *  Concentration Site.
 *
 *    The Concentration Site is implemented as an implicit monitor.
 *    All public methods are executed in mutual exclusion.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class ConcentrationSite {
    
    /**
     * Ordinary thieves Queue.
     */
    private MemFIFO<Integer> thievesQueue;

    /**
     * Number of ordinary thieves waiting master decision.
     */
    private int waitingThieves;

    /**
     * Counter of summoned thieves
     */
    private int recruited;

    /**
     * Master is going to sum up the results 
     */
    private boolean results;

    /**
     * Summon thieve to an Concentration Site
     */
    private int summon;

    /**
     * The thief summoned responds to the call
     */
    private boolean summoned;

    /**
     * PreparingConcentration Site
     */
    private int preparingAP;

    /**
     * Number of members on Assault parties
     */
    private int[] party;

    /**
     * Number of thieves currently assault the museum
     */
    private int heisting;

    /**
     * Indicate which the Concentration Site is heisting
     */
    private int rooms[];

    /**
     * Getter room assign to the Concentration Site
     * 
     * @param ap Concentration Site
     * @return room address to heist
     */
    public synchronized int getRoom(int ap){
        return rooms[ap];
    }

    /**
     * Return one Concentration Site available
     * 
     * @return Concentration Site
     */
    public synchronized int getAssautlParty(){
        return rooms[0]<0? 0:1;
    }


    /**
     *  Reference to ordinary threads.
     */
    private final ConcentrationSiteClientProxy [] ord;

    /**
     *  Reference to master thread.
     */
    private ConcentrationSiteClientProxy master;

    /**
     *   Number of entity groups requesting the shutdown.
     */
    private int nEntities;

    /**
     * Reference to the general reposStub.itory.
     */
    private final GeneralReposStub reposStub;

    /**
     * ConcentrationSite instantiation
     *
     * @param reposStub reference to the general reposStub.itory
     */
    public ConcentrationSite(GeneralReposStub reposStub) {

        this.reposStub = reposStub;
        try { this.thievesQueue = new MemFIFO<>(new Integer[SimulConsts.M-1]);
        } catch (MemException e) {
            GenericIO.writelnString ("Instantiation of waiting FIFO failed: " + e.getMessage ());
            System.exit (1);
        }

        this.waitingThieves = 0;
        this.results = false;
        this.recruited = 0;
        this.summoned = true;
        this.summon = -1;
        this.preparingAP = -1;
        this.heisting = 0;
        this.party = new int[2];
        this.rooms = new int[2];
        for(int i=0; i<2; i++){
            party[i] = 0;
            rooms[i] = -1; 
        } 

        master = null;
        ord = new ConcentrationSiteClientProxy [SimulConsts.M-1];
        for (int i = 0; i < SimulConsts.M-1; i++)
            ord[i] = null;
        nEntities = 0;
    }






    /**
     * The master thief appraise the situation of how the heist is going and takes a decision of is next step based on that
     * 
     * @param roomState indicate if all rooms are empty
     * @return the master decision
     */
    public synchronized int appraiseSit(boolean roomState) {
        if(roomState){
            if(heisting>0){
                heisting--;
                return 2;
            } 
            return 3;
        }
        if(waitingThieves>=SimulConsts.E && (rooms[0]<0 || rooms[1]<0)) return 1;
        if(heisting>0){
            heisting--;
            return 2;
        } 

        while (waitingThieves<SimulConsts.E) {
            try {  wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }


    /**
     * The master thief prepares an Concentration Site to lauch in excursion
     * 
     * @param ap Concentration Site to prepare
     * @param room to assault
     */
    public synchronized void prepareAssaultParty(int ap, int room) {
        reposStub.setApRoom(ap, room);
        preparingAP = ap;

        while (recruited < SimulConsts.E) {
            if(summoned){
                summoned = false;
                try {
                    summon = thievesQueue.read();
                    waitingThieves--;
                } catch (Exception e) {}
                notifyAll();
            } 

            

            try { wait();
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            }
        }

        recruited = 0;
        summon = -1;
        preparingAP = -1;
        rooms[ap] = room;
        heisting += SimulConsts.E;

        // Update Master stat
        master = (ConcentrationSiteClientProxy) Thread.currentThread();
        master.setMasterState(MasterStates.ASSEMBLING_A_GROUP);
        reposStub.setMasterState(0, master.getMasterState());
    }


    /**
     * The ordinary thieve makes the last preparations before going in an excursion
     * 
     * @return joined Concentration Site
     */
    public synchronized int prepareExcursion() {
        // Update Ordinary state
        int ordinaryId = ((ConcentrationSiteClientProxy) Thread.currentThread()).getOrdinaryId();
        ord[ordinaryId] = (ConcentrationSiteClientProxy) Thread.currentThread();
        ord[ordinaryId].setOrdinaryState(OrdinaryStates.CRAWLING_INWARDS);
        reposStub.setOrdinaryState(ordinaryId, ord[ordinaryId].getOrdinaryState());

        recruited++;
        summoned = true;
        notifyAll();

        party[preparingAP]++;
        reposStub.setOrdinarySituation(ordinaryId, 'P');
        return preparingAP;
    }




    /**
     * The ordinary thief indicates to the master that he is available
     * 
     * @param ap Concentration Site from which the ordinary thieve work before
     * @return master service decision
     */
    public synchronized boolean amINeeded(int ap){
        //Update Ordinary state
        int ordinaryId = ((ConcentrationSiteClientProxy) Thread.currentThread()).getOrdinaryId();
        ord[ordinaryId] = (ConcentrationSiteClientProxy) Thread.currentThread();
        ord[ordinaryId].setOrdinaryState(OrdinaryStates.CONCENTRATION_SITE);
        reposStub.setOrdinaryState(ordinaryId, ord[ordinaryId].getOrdinaryState());

        if(ap>=0 && --party[ap]==0){
            reposStub.setApRoom(ap, -1);
            rooms[ap]=-1;
        } 

        
        reposStub.setOrdinarySituation(ordinaryId, 'W');
        try {
            thievesQueue.write(ordinaryId);
            waitingThieves++;
        } catch (Exception e) {}
        notifyAll();

        while(summon!=ordinaryId && !results){
			try { wait();
			} catch (InterruptedException e) {
				GenericIO.writelnString(" "+e.getMessage());
                System.exit(0);
			}
		}

        return summon==ordinaryId;
    }



    /**
     * All the paitings were finally robbed, now its time to count the gains
     */
    public synchronized void sumUpResults() {
        results = true;
        notifyAll();
        
        // Update Master state
        master = (ConcentrationSiteClientProxy) Thread.currentThread();
        master.setMasterState(MasterStates.PRESENTING_THE_REPORT);
        reposStub.setMasterState(0, master.getMasterState());
    }


    /**
   *  Operation end of work.
   *
   *   New operation.
   *   @param ordId ordinary id
   */

   public synchronized void endOperation (int ordId)
   {
      while (nEntities == 0)
      { /* the master waits for the termination of the ordinaries */
        try
        { wait ();
        }
        catch (InterruptedException e) {}
      }
      if (ord[ordId] != null)
         ord[ordId].interrupt ();
   }


   /**
   *   Operation server shutdown.
   *
   *   New operation.
   */

   public synchronized void shutdown () {
       nEntities += 1;
       if (nEntities >= SimulConsts.SHT)
          ServerConcentrationSite.waitConnection = false;
       notifyAll ();                                        // the master may now terminate
   }

}

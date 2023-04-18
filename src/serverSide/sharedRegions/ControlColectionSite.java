package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import clientSide.stubs.*;
import commInfra.*;
import genclass.GenericIO;


public class ControlColectionSite {
    
    /**
     * Indicate ordinary thieve handed a canvas
     */
    private boolean handed;

    /**
     * Indicate if the thieve gives a canvas or is empty
     */
    private int canvas;

    /**
     * Indicate master thieve as collected the canvas
     */
    private boolean collected;

    /**
     * Indicate the room assaulted
     */
    private int room;

    /**
     * State of each room, if is full or not
     */
    private boolean[] rooms;

    /**
     * Index of rooms still with paintings
     */
    private int idx;

    /**
     * Getter room state list
     * 
     * @return room state list
     */
    public synchronized int getRoomIdx(){
        while(idx<SimulConsts.N){
            if(rooms[idx]) break;
            idx++;
        }
        return idx;
    }

    /**
     *   Number of entity groups requesting the shutdown.
     */
    private int nEntities;

    /**
     *   Reference to the general repository.
     */
    private final GeneralReposStub reposStub;

    /**
     * Bar instantiation
     *
     * @param repos reference to the general repository
     */
    public ControlCollectionSite(GeneralReposStub reposStub){
        this.reposStub = reposStub;
        this.canvas = -1;
        this.room = -1;
        this.idx = 0;
        this.rooms = new boolean[SimulConsts.N];
        for(int i=0; i<SimulConsts.N; i++) rooms[i]=true;
        this.handed = false;
        this.collected = false;
    }

    


    /**
     * Master starts the heist the museum operation
     */
    public synchronized void startOperation(){
        //Update Master state
		((Master) Thread.currentThread()).setMasterState(MasterStates.DECIDING_WHAT_TO_DO);
		reposStub.setMasterState(((Master) Thread.currentThread()).getMasterState());
    }


    /**
     * Master hide until return of the ordinaries 
     */
    public synchronized void takeARest(){

        while(!handed){
			try { wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

        if(canvas==0) rooms[room] = false;
        else reposStub.setRobbedPaintings();
        
        canvas = -1; 
        room = -1;

        //Update Master state
		((Master) Thread.currentThread()).setMasterState(MasterStates.WAITING_FOR_GROUP_ARRIVAL);
		reposStub.setMasterState(((Master) Thread.currentThread()).getMasterState());

    }



    /**
     * Ordinary thieves takes the canvas out of the cylinder and hands it to the master thief, or tells her he is coming empty-handed
     * 
     * @param canvas or empty handed
     * @param ap assault party
     * @param members member id
     * @param room heisted by the thief
     */
    public synchronized void handACanvas(int canvas, int room, int ap, int members){
        
        while(handed){
			try { wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


        handed = true;
        this.canvas = canvas;
        this.room = room;
        notifyAll();
        reposStub.setCanvas(ap * SimulConsts.E + members, 0);

        while(!collected){
			try { wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

        collected = false;
    }




    /**
     * The master thief stores it in the back of a van
     */
    public synchronized void collectACanvas(){
        collected = true;
        handed = false;
        notifyAll();
        
        //Update Master state
		((Master) Thread.currentThread()).setMasterState(MasterStates.DECIDING_WHAT_TO_DO);
		reposStub.setMasterState(((Master) Thread.currentThread()).getMasterState());
    }


    /**
   *   Operation server shutdown.
   *
   *   New operation.
   */

   public synchronized void shutdown ()
   {
       nEntities += 1;
       if (nEntities >= SimulConsts.M)
          ServerMuseumControlColectionSite.waitConnection = false;
       notifyAll ();                                        // the barber may now terminate
   }
}

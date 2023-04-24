package clientSide.entities;

import clientSide.stubs.*;
import serverSide.main.SimulConsts;

public class Master extends Thread {

    /**
     * Master Id
     */
    private int masterId;

    /**
     * Master State
     */
    private int masterState;

    /**
     * Reference to the Assault partyStub 
     */
    private final AssaultPartyStub[] partyStub;

    /**
     * Reference to the Concentration Site
     */
    private final ConcentrationSiteStub csStub;

    /**
     * Reference to the Control and Collection Site
     */
    private final ControlCollectionSiteStub ccsStub;

    /**
     * Reference to the general reposStubitory
     */
    private final GeneralReposStub reposStub;

    /**
     * Instantiation of a master thread.
     * 
     * @param masterId        Master Id
     * @param masterState     Master state
     * @param partyStub       Reference to AssaultpartyStub
     * @param csStub          Reference to ConcentrationSite
     * @param ccsStub         Reference to ControlCollectionSite
     * @param reposStub       Reference to GeneralreposStub
     */
    public Master(int masterId, int masterState, GeneralReposStub reposStub, ConcentrationSiteStub csStub, ControlCollectionSiteStub ccsStub, AssaultPartyStub[] partyStub) {
        this.masterState = masterState;
        this.masterId = masterId;
        this.partyStub = partyStub;
        this.csStub = csStub;
        this.ccsStub = ccsStub;
        this.reposStub = reposStub;
    }


    /**
     * Get master Id
     * 
     * @return master Id
     */
    public int getMasterId() {
        return masterId;
    }

    /**
     * Set master Id
     * 
     * @param masterId master Id
     */
    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    /**
     * Get master State
     * 
     * @return master state
     */
    public int getMasterState() {
        return masterState;
    }

    /**
     * Set master State
     * 
     * @param masterState master state
     */
    public void setMasterState(int masterState) {
        this.masterState = masterState;
    }

    /**
     * @return GeneralreposStub
     */
    public GeneralReposStub getReposStub() {
        return reposStub;
    }

    /**
     * Life cycle of the master.
     */
    @Override
    public void run() {
        int room;
        ccsStub.startOperation();
        
        boolean assault = true;
        while (assault) {
            System.out.println("getRoomIdx");
            room = ccsStub.getRoomIdx();
            System.out.println("appraiseSit");
            switch (csStub.appraiseSit(room>=SimulConsts.N)){
                case 1:
                    System.out.println("getAssautlParty");
                    int ap = csStub.getAssautlParty();
                    System.out.println("prepareAssaultParty");
                    csStub.prepareAssaultParty(ap, room);
                    System.out.println("sendAssaultParty");
                    partyStub[ap].sendAssaultParty(csStub.getRoom(ap));
                    break;


                case 2:
                    System.out.println("takeARest");
                    ccsStub.takeARest();
                    System.out.println("collectACanvas");
                    ccsStub.collectACanvas();
                    break;



                case 3:
                    System.out.println("sumUpResults");
                    csStub.sumUpResults();
                    assault = false;
                    break;
            }
        }

    }

}

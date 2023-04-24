package clientSide.entities;

import clientSide.stubs.*;
import serverSide.main.SimulConsts;

public class Ordinary extends Thread {

    /**
     * Ordinary Id
     */
    private int ordinaryId;

    /**
     * Ordinary State
     */
    private int ordinaryState;

    /**
     * Reference to the Assault Party
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
     * Reference to the Museum
     */
    private final MuseumStub museumStub;

    /**
     * Reference to the general repository
     */
    private final GeneralReposStub reposStub;

    /**
     * Instantiation of a ordinary thread.
     * 
     * 
     * @param ordinaryId    ordinary Id
     * @param ordinaryState ordinary state
     * @param repos         Reference to GeneralRepos
     */
    public Ordinary(int ordinaryId, int ordinaryState, GeneralReposStub reposStub, ConcentrationSiteStub csStub, ControlCollectionSiteStub ccsStub, AssaultPartyStub[] partyStub, MuseumStub museumStub) {
        this.ordinaryState = ordinaryState;
        this.ordinaryId = ordinaryId;
        this.partyStub = partyStub;
        this.csStub = csStub;
        this.ccsStub = ccsStub;
        this.museumStub = museumStub;
        this.reposStub = reposStub;
    }


    /**
     * Get ordinary Id
     * 
     * @return ordinary Id
     */
    public int getOrdinaryId() {
       
        return ordinaryId;
    }

    /**
     * Set ordinary Id
     * 
     * @param ordinaryId ordinary Id
     */
    public void setOrdinaryId(int ordinaryId) {
        this.ordinaryId = ordinaryId;
    }

    /**
     * Get ordinary State
     * 
     * @return ordinary state
     */
    public int getOrdinaryState() {
        return ordinaryState;
    }

    /**
     * Set ordinary State
     * 
     * @param ordinaryState ordinary state
     */
    public void setOrdinaryState(int ordinaryState) {
        this.ordinaryState = ordinaryState;
    }

    /**
     * @return GeneralRepos
     */
    public GeneralReposStub getRepos() {
        return reposStub;
    }

    /**
     * Life cycle of the ordinary.
     */
    @Override
    public void run() {
        int memberId, room, canvas, ap = -1;
        System.out.println("getOrdinaryMD");
        int md = reposStub.getOrdinaryMD(ordinaryId);
        System.out.println("amINeeded");
        while (csStub.amINeeded(ap)) {
            System.out.println("prepareExcursion");
            ap = csStub.prepareExcursion();
            System.out.println("assignMember");
            memberId = partyStub[ap].assignMember(ap);
            boolean atRoom = true;
            while (atRoom){
                System.out.println("crawlIn");
                atRoom = partyStub[ap].crawlIn(ap, memberId, md);
            }

            System.out.println("getRoom");
            room = partyStub[ap].getRoom();
            System.out.println("rollACanvas");
            canvas = museumStub.rollACanvas(room, ap, memberId);
            System.out.println("assignMember");
            memberId = partyStub[ap].assignMember(ap);
            System.out.println("reverseDirection");
            partyStub[ap].reverseDirection(memberId);
            boolean atSite = true;
            while (atSite){
                System.out.println("crawlOut");
                atSite = partyStub[ap].crawlOut(ap, memberId, md);
            }   
            System.out.println("handACanvas");
            ccsStub.handACanvas(canvas, csStub.getRoom(ap), ap, memberId);
        }
    }

}

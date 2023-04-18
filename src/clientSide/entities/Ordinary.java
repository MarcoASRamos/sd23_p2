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
     * @param name          ordinary Name
     * 
     * @param ordinaryId    ordinary Id
     * @param ordinaryState ordinary state
     * @param repos         Reference to GeneralRepos
     */
    public Ordinary(String name, int ordinaryId, int ordinaryState, GeneralReposStub reposStub, ConcentrationSiteStub csStub,
            ControlCollectionSiteStub ccsStub, AssaultPartyStub[] partyStub, MuseumStub museumStub) {
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
        int memberId, room, canvas, ap = -1, md = 2 + (int) (Math.random() * (SimulConsts.MD - 2) + 1);
        reposStub.setOrdinariesMD(ordinaryId, md);

        while (csStub.amINeeded(ap)) {
       
            ap = csStub.prepareExcursion();

            memberId = partyStub[ap].assignMember(ap);
            boolean atRoom = true;
            while (atRoom)
                atRoom = partyStub[ap].crawlIn(ap, memberId, md);

            room = partyStub[ap].getRoom();
            canvas = museumStub.rollACanvas(room, ap, memberId);

            memberId = partyStub[ap].assignMember(ap);
            partyStub[ap].reverseDirection(memberId);
            boolean atSite = true;
            while (atSite)
                atSite = partyStub[ap].crawlOut(ap, memberId, md);

            ccsStub.handACanvas(canvas, csStub.getRoom(ap), ap, memberId);
        }
    }

}

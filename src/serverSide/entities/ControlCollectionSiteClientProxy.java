
package serverSide.entities;

import serverSide.sharedRegions.*;
import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;

/**
 * Service provider agent for access to the Control Collection Site.
 *
 * Implementation of a client-server model of type 2 (server replication).
 * Communication is based on a communication channel under the TCP protocol.
 */

public class ControlCollectionSiteClientProxy extends Thread implements OrdinaryCloning, MasterCloning {
    /**
     * Number of instantiayed threads.
     */

    private static int nProxy = 0;

    /**
     * Communication channel.
     */

    private ServerCom sconi;
    private int OrdinaryID;

    /**
     * Barber state.
     */

    private int OrdinaryState;

    /**
     * Customer identification.
     */

    private int MasterId;

    /**
     * Customer state.
     */

    private int MasterState;

    /**
     * Interface to the Control Collection Site.
     */

    private ControlCollectionSiteInterface ccsInter;

    /**
     * Instantiation of a client proxy.
     *
     * @param sconi   communication channel
     * @param CCSInter interface to the Control Collection Site
     */

    public ControlCollectionSiteClientProxy(ServerCom sconi, ControlCollectionSiteInterface CCSInter) {
        super("ControlCollectionSiteClientProxy" + ControlCollectionSiteClientProxy.getProxyId());
        this.sconi = sconi;
        this.ccsInter = CCSInter;
    }

    /**
     * Generation of the instantiation identifier.
     *
     * @return instantiation identifier
     */

    private static int getProxyId() {
        Class<?> cl = null; // representation of the ControlCollectionSiteClientProxy object in JVM
        int proxyId; // instantiation identifier

        try {
            cl = Class.forName("serverSide.entities.ControlCollectionSiteClientProxy");
        } catch (ClassNotFoundException e) {
            GenericIO.writelnString("Data type ControlCollectionSiteClientProxy was not found!");
            e.printStackTrace();
            System.exit(1);
        }
        synchronized (cl) {
            proxyId = nProxy;
            nProxy += 1;
        }
        return proxyId;
    }

    /**
     * Life cycle of the service provider agent.
     */

    @Override
    public void run() {
        Message inMessage = null, // service request
                outMessage = null; // service reply

        /* service providing */

        inMessage = (Message) sconi.readObject(); // get service request
        try {
            outMessage = ccsInter.processAndReply(inMessage); // process it
        } catch (MessageException e) {
            GenericIO.writelnString("Thread " + getName() + ": " + e.getMessage() + "!");
            GenericIO.writelnString(e.getMessageVal().toString());
            System.exit(1);
        }
        sconi.writeObject(outMessage); // send service reply
        sconi.close(); // close the communication channel
    }

   /**
     * Getter master id
     * @return master id
     */
    @Override
    public int getMasterId() {
        return MasterId;
    }

    /**
     * Setter master id
     * @param masterId
     */
    @Override
    public void setMasterId(int masterId) {
        MasterId = masterId;
    }

    /**
     * Getter master state
     * @return master state
     */
    @Override
    public int getMasterState() {
        return MasterState;
    }

    /**
     * Setter master state
     * @param masterState
     */
    @Override
    public void setMasterState(int masterState) {
        MasterState = masterState;
    }

    /**
     * Getter ordinary id
     * @return ordinary id
     */
    @Override
    public int getOrdinaryId() {
        return OrdinaryID;
    }

    /**
     * Setter ordinary id
     * @param ordinaryId
     */
    @Override
    public void setOrdinaryId(int ordinaryId) {
        OrdinaryID = ordinaryId;
    }

    /**
     * Getter ordinary state
     * @return ordinary state
     */
    @Override
    public int getOrdinaryState() {
        return OrdinaryState;
    }

    /**
     * Setter ordinary state
     * @param ordinaryState
     */
    @Override
    public void setOrdinaryState(int ordinaryState) {
        OrdinaryState = ordinaryState;
    }
}
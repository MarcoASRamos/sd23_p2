package serverSide.entities;

import serverSide.sharedRegions.*;
import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;

/**
 * Service provider agent for access to the AssaultParty Shop.
 *
 * Implementation of a client-server model of type 2 (server replication).
 * Communication is based on a communication channel under the TCP protocol.
 */

public class AssaultPartyClientProxy extends Thread implements MasterCloning, OrdinaryCloning {
    /**
     * Number of instantiayed threads.
     */

    private static int nProxy = 0;

    /**
     * Communication channel.
     */

    private ServerCom sconi;

    /**
     * Interface to the AssaultParty Shop.
     */

    private AssaultPartyInterface APInt;


    /**
     * Customer identification.
     */

    private int OrdinaryID;

    /**
     * Ordinary state.
     */
    private int OrdinaryState;
    /**
     * Master state.
     */
    private int MasterState;

    /**
     * Master identification.
     */

    private int MasterId;

    /**
     * Instantiation of a client proxy.
     *
     * @param sconi communication channel
     * @param APInt interface to the AssaultParty shop
     */

    public AssaultPartyClientProxy(ServerCom sconi, AssaultPartyInterface APInt) {
        super("AssaultPartyClientProxy" + AssaultPartyClientProxy.getProxyId());
        this.sconi = sconi;
        this.APInt = APInt;
    }

    /**
     * Generation of the instantiation identifier.
     *
     * @return instantiation identifier
     */

    private static int getProxyId() {
        Class<?> cl = null; // representation of the AssaultPartyClientProxy object in JVM
        int proxyId; // instantiation identifier

        try {
            cl = Class.forName("serverSide.entities.AssaultPartyClientProxy");
        } catch (ClassNotFoundException e) {
            GenericIO.writelnString("Data type AssaultPartyClientProxy was not found!");
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
            outMessage = APInt.processAndReply(inMessage); // process it
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
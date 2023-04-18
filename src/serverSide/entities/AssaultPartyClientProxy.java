
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
     * AssaultParty identification.
     */

    private int AssaultPartyId;

    /**
     * AssaultParty state.
     */

    private int AssaultPartyState;

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

    private int MasterID;

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
     * Set AssaultParty id.
     *
     * @param id AssaultParty id
     */

    public void setAssaultPartyId(int id) {
        AssaultPartyId = id;
    }

    /**
     * Get AssaultParty id.
     *
     * @return AssaultParty id
     */

    public int getAssaultPartyId() {
        return AssaultPartyId;
    }

    /**
     * Set ordinary id.
     *
     * @param id ordinary id
     */

    public void setOrdinaryId(int id) {
        OrdinaryID = id;
    }

    /**
     * Get customer id.
     *
     * @return customer id
     */

    public int getOrdinaryId() {
        return OrdinaryID;
    }

    /**
     * Set ordinary id.
     *
     * @param id ordinary id
     */

    public void setMasterID(int id) {
        MasterID = id;
    }

    /**
     * Get customer id.
     *
     * @return customer id
     */

    public int getMasterID() {
        return MasterID;
    }

    /**
     * Set customer state.
     *
     * @param state new customer state
     */

    public void setOrdinaryState(int state) {
        OrdinaryState = state;
    }

    /**
     * Get customer state.
     *
     * @return customer state
     */

    public int getOrdinaryState() {
        return OrdinaryState;
    }

    /**
     * Set customer state.
     *
     * @param state new customer state
     */

    public void setMasterState(int state) {
        MasterState = state;
    }

    /**
     * Get customer state.
     *
     * @return customer state
     */

    public int getMasterState() {
        return MasterState;
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

    @Override
    public int getMasterId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMasterId'");
    }

    @Override
    public void setMasterId(int masterId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMasterId'");
    }
}
package serverSide.entities;

import serverSide.sharedRegions.*;
import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;

/**
 * Service provider agent for access to the Barber Shop.
 *
 * Implementation of a client-server model of type 2 (server replication).
 * Communication is based on a communication channel under the TCP protocol.
 */

public class MuseumClientProxy extends Thread implements OrdinaryCloning {
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

    private MuseumInterface MuseumInter;

    /**
     * Instantiation of a client proxy.
     *
     * @param sconi   communication channel
     * @param CSInter interface to the barber shop
     */

    public MuseumClientProxy(ServerCom sconi, MuseumInterface MuseumInter) {
        super("MuseumClientProxy" + MuseumClientProxy.getProxyId());
        this.sconi = sconi;
        this.MuseumInter = MuseumInter;
    }

    /**
     * Generation of the instantiation identifier.
     *
     * @return instantiation identifier
     */

    private static int getProxyId() {
        Class<?> cl = null; // representation of the BarberShopClientProxy object in JVM
        int proxyId; // instantiation identifier

        try {
            cl = Class.forName("serverSide.entities.BarberShopClientProxy");
        } catch (ClassNotFoundException e) {
            GenericIO.writelnString("Data type BarberShopClientProxy was not found!");
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
            outMessage = MuseumInter.processAndReply(inMessage); // process it
        } catch (MessageException e) {
            GenericIO.writelnString("Thread " + getName() + ": " + e.getMessage() + "!");
            GenericIO.writelnString(e.getMessageVal().toString());
            System.exit(1);
        }
        sconi.writeObject(outMessage); // send service reply
        sconi.close(); // close the communication channel
    }

    @Override
    public int getOrdinaryId() {
        return OrdinaryID;
    }

    @Override
    public void setOrdinaryId(int ordinaryId) {
        OrdinaryID = ordinaryId;
    }

    @Override
    public int getOrdinaryState() {
        return OrdinaryState;
    }

    @Override
    public void setOrdinaryState(int ordinaryState) {
        OrdinaryState = ordinaryState;
    }
}
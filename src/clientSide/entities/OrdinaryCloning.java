package clientSide.entities;


/**
 *    Ordinary cloning.
 *
 *      It specifies his own attributes.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a communication channel under the TCP protocol.
 */

public interface OrdinaryCloning {
    
    /**
     * Get ordinary Id
     * 
     * @return ordinary Id
     */
    public int getOrdinaryId();

    /**
     * Set ordinary Id
     * 
     * @param ordinaryId ordinary Id
     */
    public void setOrdinaryId(int ordinaryId);

    /**
     * Get ordinary State
     * 
     * @return ordinary state
     */
    public int getOrdinaryState();

    /**
     * Set ordinary State
     * 
     * @param ordinaryState ordinary state
     */
    public void setOrdinaryState(int ordinaryState);
}

package clientSide.entities;


/**
 *    Master cloning.
 *
 *      It specifies his own attributes.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a communication channel under the TCP protocol.
 */

public interface MasterCloning {
    
    /**
     * Get master Id
     * 
     * @return master Id
     */
    public int getMasterId();

    /**
     * Set master Id
     * 
     * @param masterId master Id
     */
    public void setMasterId(int masterId);

    /**
     * Get master State
     * 
     * @return master state
     */
    public int getMasterState();

    /**
     * Set master State
     * 
     * @param masterState master state
     */
    public void setMasterState(int masterState);
}

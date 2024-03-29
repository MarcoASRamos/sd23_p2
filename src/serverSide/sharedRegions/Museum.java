package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import clientSide.stubs.*;
import commInfra.*;
import genclass.GenericIO;


/**
 *  Museum.
 *
 *    The Museum is implemented as an implicit monitor.
 *    All public methods are executed in mutual exclusion.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class Museum {
    /**
     * Number of paintings hanging in each room
     */
    private int[] paintings;

    /**
     *  Reference to ordinary threads.
     */
    private final MuseumClientProxy [] ord;

    /**
     *   Number of entity groups requesting the shutdown.
     */
    private int nEntities;

    /**
     * Reference to the general repository.
     */
    private final GeneralReposStub reposStub;

    /**
     * Museum instantiation
     *
     * @param reposStub reference to the general repository
     */
    public Museum(GeneralReposStub reposStub) {

        this.reposStub = reposStub;
        this.paintings = new int[SimulConsts.N];
        for(int i=0; i<SimulConsts.N; i++) 
            paintings[i] = SimulConsts.p +(int)(Math.random() * (SimulConsts.P-SimulConsts.p)+1); 
            reposStub.setRoomPaitings(paintings);

            ord = new MuseumClientProxy [SimulConsts.M-1];
            for (int i = 0; i < SimulConsts.M-1; i++)
                ord[i] = null;
            nEntities = 0;
    }

    /**
     * The thieve detaches the canvas from the framing, 
     * rolls it over and inserts it in a cylinder container 
     * 
     * @param room where assault is happening
     * @param ap Museum
     * @param members id member
     * @return number of canvas stolen by the thieve
     */
    public synchronized int rollACanvas(int room, int ap, int members) {

        if(paintings[room]>0) {
            paintings[room]--;
            reposStub.setRoomPaitings(paintings);
            reposStub.setCanvas(ap * SimulConsts.E + members, 1);
            return 1;
        }
        reposStub.setCanvas(ap * SimulConsts.E + members, 0);
        return 0;
    }


    /**
   *   Operation server shutdown.
   *
   *   New operation.
   */

   public synchronized void shutdown () {
       nEntities += 1;
       if (nEntities >= SimulConsts.SHT-1)
          ServerMuseum.waitConnection = false;
       notifyAll ();                                        // the barber may now terminate
   }
}

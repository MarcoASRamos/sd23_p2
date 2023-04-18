package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import clientSide.stubs.*;
import commInfra.*;
import genclass.GenericIO;


public class Museum {
    /**
     * Number of paintings hanging in each room
     */
    private int[] paintings;

    /**
     *   Number of entity groups requesting the shutdown.
     */
    private int nEntities;

    /**
     * Reference to the general repository.
     */
    private final GeneralReposStub reposStub;

    /**
     * Bar instantiation
     *
     * @param repos reference to the general repository
     */
    public Museum(GeneralReposStub reposStub) {

        this.reposStub = reposStub;
        this.paintings = new int[SimulConsts.N];
        for(int i=0; i<SimulConsts.N; i++) 
            paintings[i] = SimulConsts.p +(int)(Math.random() * (SimulConsts.P-SimulConsts.p)+1); 
            reposStub.setRoomPaitings(paintings);
    }

    /**
     * The thieve detaches the canvas from the framing, 
     * rolls it over and inserts it in a cylinder container 
     * 
     * @param room where assault is happening
     * @param ap assault party
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

   public synchronized void shutdown ()
   {
       nEntities += 1;
       if (nEntities >= SimulConsts.M-1)
          ServerMuseumMuseum.waitConnection = false;
       notifyAll ();                                        // the barber may now terminate
   }
}

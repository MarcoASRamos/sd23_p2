package serverSide.sharedRegions;

import java.util.Arrays;
import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import clientSide.stubs.*;
import commInfra.*;
import genclass.GenericIO;


/**
 *  Assault Party.
 *
 *    The Assault Party is implemented as an implicit monitor.
 *    All public methods are executed in mutual exclusion.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class AssaultParty {



    /**
     * Number of members in the party
     */
    private int members;

    /**
     * Getter/Assign an assault party id to the thieve
     * @param ap assault party
     * @return member id
     */
    public synchronized int assignMember(int ap) {
        members = (members + 1) % SimulConsts.E;
        reposStub.setApElement(ap * SimulConsts.E + members, ((AssaultPartyClientProxy) Thread.currentThread()).getOrdinaryId());
        return members;
    }

    /**
     * Room to heist
     */
    private int room;

    /**
     * Getter room
     * @return room id
     */
    public synchronized int getRoom() {
        return room;
    }

    /**
     * Sended assault party
     */
    private boolean sended;

    /**
     * Inital step for the first member of the assault party
     */
    private boolean init;

    /**
     * The movement of Crawl in has been initializated
     */
    private boolean crawlin;

    /**
     * Number of party members that are at the door of the museum room
     */
    private int atRoom;

    /**
     * The movement of Crawl out has been initializated
     */
    private boolean crawlout;

    /**
     * Positions of each member during crawl line
     */
    private int[] pos;

    /**
     * Tha last member signals to reverse march
     */

    private boolean reversed;

    /**
     * Reference to the general repository.
     */
    private final GeneralReposStub reposStub;

    /**
     * Distaces in units from the site to the each museum room
     */
    private final int[] rooms;

    /**
     *  Reference to ordinary threads.
     */
    private final AssaultPartyClientProxy [] ord;

    /**
     *  Reference to master thread.
     */
    private AssaultPartyClientProxy master;

    /**
     *   Number of entity groups requesting the shutdown.
     */
    private int nEntities;

    /**
     * AP instantiation
     *
     * @param reposStub reference to the general repository
     */

    public AssaultParty(GeneralReposStub reposStub) {
        this.reposStub = reposStub;
        this.rooms = reposStub.getRoomDistances();
        this.room = -1;
        this.members = -1;
        this.reversed = false;
        this.sended = false;
        this.init = false;
        this.crawlin = false;
        this.atRoom = 0;
        this.crawlout = false;
        this.pos = new int[SimulConsts.E];
        for (int i = 0; i < SimulConsts.E; i++)
            pos[i] = 0;

        master = null;
        ord = new AssaultPartyClientProxy [SimulConsts.M-1];
        for (int i = 0; i < SimulConsts.M-1; i++)
            ord[i] = null;
        nEntities = 0;
    }

    /**
     * Operation Reverse direction
     * @param member id
     */
    public synchronized void reverseDirection(int member) {

        if (member == SimulConsts.E - 1) {
            init = true;
            reversed = true;
            atRoom = 0;
            crawlin = false;
            sended = false;
            notifyAll();
        }

        // Update Ordinary state
        int ordinaryId = ((AssaultPartyClientProxy) Thread.currentThread()).getOrdinaryId();
        ord[ordinaryId] = (AssaultPartyClientProxy) Thread.currentThread();
        ord[ordinaryId].setOrdinaryState(OrdinaryStates.CRAWLING_OUTWARDS);
        reposStub.setOrdinaryState(ordinaryId, ord[ordinaryId].getOrdinaryState());

    }

    /**
     * The master sends the assault party to the museum to heist an especific room
     * 
     * @param room to heist
     */
    public synchronized void sendAssaultParty(int room) {

        crawlin = false;
        sended = true;
        init = true;
        crawlout = false;
        reversed = false;
        this.room = room;
        notifyAll();

        // Update Master state
        master = (AssaultPartyClientProxy) Thread.currentThread();
        master.setMasterState(MasterStates.DECIDING_WHAT_TO_DO);
        reposStub.setMasterState(0, master.getMasterState());
    }

    /**
     * The assault party get in line and crawl to the room of the museum
     * 
     * @param ap     number
     * @param member id of the thieve in the crawl line
     * @param md     maximum distance capable by the thive
     * @return true if the thieve get to the room
     */
    public synchronized boolean crawlIn(int ap, int member, int md) {
        int move = 0;

        if (sended) {
            for (int i = md; i > 0; i--) {
                if (valid(member, i)) {
                    move = i;
                    break;
                }
            }
        } else {
            move = member == 0 ? 3 : 0;
        }

        
        int ordinaryId = ((AssaultPartyClientProxy) Thread.currentThread()).getOrdinaryId();
        while ((crawlin && move < 1) || (member != 0 && init) || !sended) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = md; i > 0; i--) {
                if (valid(member, i)) {
                    move = i;
                    break;
                }
            }
        }

        if (member == 0 && init) {
            crawlin = true;
            init = false;
        }

        pos[member] += move;
        if (pos[member] > rooms[room])
            pos[member] = rooms[room];

        reposStub.setPosition(ap * SimulConsts.E + member, pos[member]);
        notifyAll();

        if (pos[member] == rooms[room]) {
            atRoom++;
            while (atRoom < SimulConsts.E) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            notifyAll();

            // Update Ordinary state
            ord[ordinaryId] = (AssaultPartyClientProxy) Thread.currentThread();
            ord[ordinaryId].setOrdinaryState(OrdinaryStates.AT_A_ROOM);
            reposStub.setOrdinaryState(ordinaryId, ord[ordinaryId].getOrdinaryState());
        }

        return pos[member] < rooms[room];
    }

    /**
     * The assault party get in line and crawl back to the site
     * 
     * @param ap     number
     * @param member id of the thieve in the crawl line
     * @param md     maximum distance capable by the thive
     * @return true if the thieve get to the site
     */
    public synchronized boolean crawlOut(int ap, int member, int md) {
        int move = 0;

        if (reversed) {
            for (int i = md; i > 0; i--) {
                if (valid(member, -i)) {
                    move = i;
                    break;
                }
            }
        } else {
            move = member == 0 ? 3 : 0;
        }

        int ordinaryId = ((AssaultPartyClientProxy) Thread.currentThread()).getOrdinaryId();
        while ((crawlout && move < 1) || (member != 0 && init) || !reversed) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = md; i > 0; i--) {
                if (valid(member, -i)) {
                    move = i;
                    break;
                }
            }

        }

        if (member == 0 && init) {
            crawlout = true;
            init = false;
        }

        pos[member] -= move;
        if (pos[member] < 0)
            pos[member] = 0;

        reposStub.setPosition(ap * SimulConsts.E + member, pos[member]);
        notifyAll();

        if (pos[member] == 0) {
            // Update Ordinary state
            ord[ordinaryId] = (AssaultPartyClientProxy) Thread.currentThread();
            ord[ordinaryId].setOrdinaryState(OrdinaryStates.COLLECTION_SITE);
            reposStub.setOrdinaryState(ordinaryId, ord[ordinaryId].getOrdinaryState());
        }

        return pos[member] > 0;
    }

    /**
     * Fuction to validate the resulting state given a thief move
     *
     * @param member id in the party
     * @param p      movement to test
     * @return true if the resulting state is valid
     */
    private synchronized boolean valid(int member, int p) {
        int[] test = pos.clone();
        test[member] += p;
        if (test[member] < 0)
            test[member] = 0;
        if (test[member] > rooms[room])
            test[member] = rooms[room];

        Arrays.sort(test);

        for (int i = SimulConsts.E - 1; i > 0; i--) {
            if (test[i - 1] - test[i] == 0 && (test[i] == 0 || test[i] == rooms[room]))
                continue;
            if (test[i] - test[i - 1] > SimulConsts.S || test[i - 1] - test[i] == 0)
                return false;
        }

        return true;
    }


    /**
   *   Operation server shutdown.
   *
   *   New operation.
   */

   public synchronized void shutdown ()
   {
       nEntities += 1;
       if (nEntities >= SimulConsts.SHT)
            ServerAssaultParty.waitConnection = false;
          
       notifyAll ();
   }
}
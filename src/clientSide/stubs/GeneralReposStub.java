
package clientSide.stubs;

import serverSide.main.*;
import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;
import serverSide.sharedRegions.GeneralReposInterface;

/**
 *  Stub to the general repository.
 *
 *    It instantiates a remote reference to the general repository.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */


public class GeneralReposStub {
     /**
     *  Name of the platform where is located the general repository server.
     */

     private String serverHostName;

     /**
      *  Port number for listening to service requests.
      */
 
     private int serverPortNumb;
 
     /**
      *   Instantiation of a stub to the general repository.
      *
      *     @param serverHostName name of the platform where is located the barber shop server
      *     @param serverPortNumb port number for listening to service requests
      */
 
     public GeneralReposStub (String serverHostName, int serverPortNumb)
     {
         this.serverHostName = serverHostName;
         this.serverPortNumb = serverPortNumb;
     }
     
 
 


     /**
      *   Set master state.
      *
      *     @param id merter id
      *     @param state master state
      */
 
     public void setMasterState (int id, int state)
     {
         ClientCom com;                                                 // communication channel
         Message outMessage,                                            // outgoing message
                 inMessage;                                             // incoming message
 
         com = new ClientCom (serverHostName, serverPortNumb);
         while (!com.open ())
         { try
         { Thread.sleep ((long) (1000));
         }
         catch (InterruptedException e) {}
         }
         outMessage = new Message (MessageType.STMST, id, state);
         com.writeObject (outMessage);
         inMessage = (Message) com.readObject ();
         if (inMessage.getMsgType() != MessageType.SACK)
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
             GenericIO.writelnString (inMessage.toString ());
             System.exit (1);
         }
         com.close ();
     }
 
     /**
      *   Set Ordinary state.
      *
      *     @param id Ordinary id
      *     @param state Ordinary state
      */
 
     public void setOrdinaryState (int id, int state)
     {
         ClientCom com;                                                 // communication channel
         Message outMessage,                                            // outgoing message
                 inMessage;                                             // incoming message
 
         com = new ClientCom (serverHostName, serverPortNumb);
         while (!com.open ())
         { try
         { Thread.sleep ((long) (1000));
         }
         catch (InterruptedException e) {}
         }
         outMessage = new Message (MessageType.STOST, id, state);
         com.writeObject (outMessage);
         inMessage = (Message) com.readObject ();
         if (inMessage.getMsgType() != MessageType.SACK)
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
             GenericIO.writelnString (inMessage.toString ());
             System.exit (1);
         }
         com.close ();
     }
 
     

      /**
      * Set Ordinary situation
      * 
      * @param id ordinary
      * @param sit ordinary situation
      */

      public void setOrdinarySituation (int id, char sit)
     {
         ClientCom com;                                                 // communication channel
         Message outMessage,                                            // outgoing message
                 inMessage;                                             // incoming message
 
         com = new ClientCom (serverHostName, serverPortNumb);
         while (!com.open ())
         { try
         { Thread.sleep ((long) (1000));
         }
         catch (InterruptedException e) {}
         }
         outMessage = new Message (MessageType.STOSIT, id, sit);
         com.writeObject (outMessage);
         inMessage = (Message) com.readObject ();
         if (inMessage.getMsgType() != MessageType.SACK)
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
             GenericIO.writelnString (inMessage.toString ());
             System.exit (1);
         }
         com.close ();
     }



    /**
     * Set ordinary thieves maximum distances
     * 
     * @param id of the ordinary thieve
     * @param md of the ordinary thieve
     */
    public synchronized void setOrdinariesMD(int id, int md) {
        ClientCom com;                                                 // communication channel
        Message outMessage,                                            // outgoing message
                inMessage;                                             // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }
        outMessage = new Message (MessageType.STOMD, id, md);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.SACK)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
    }

    /**
     * 
     * @param ap assault party
     * @param room room to heist
     */
    public synchronized void setApRoom(int ap, int room){
        ClientCom com;                                                 // communication channel
        Message outMessage,                                            // outgoing message
                inMessage;                                             // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }
        outMessage = new Message (MessageType.STAPR, ap, room);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.SACK)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
    }

    /**
     * Set Assault Party element
     * 
     * @param elem index (= ap*SimulConsts.E+memberId)
     * @param tid  ordinary thieve id
     */
    public synchronized void setApElement(int elem, int tid) {
        ClientCom com;                                                 // communication channel
        Message outMessage,                                            // outgoing message
                inMessage;                                             // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }
        outMessage = new Message (MessageType.STAPE, elem, tid);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.SACK)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
    }

    /**
     * Update carrying a canvas
     * 
     * @param elem   index
     * @param canvas carry
     */
    public synchronized void setCanvas(int elem, int canvas) {
        ClientCom com;                                                 // communication channel
        Message outMessage,                                            // outgoing message
                inMessage;                                             // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }
        outMessage = new Message (MessageType.STCVS, elem, canvas);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.SACK)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
    }

    /**
     * Update thieve position
     * 
     * @param elem index
     * @param pos  actual position of the thieve in line
     */
    public synchronized void setPosition(int elem, int pos) {
        ClientCom com;                                                 // communication channel
        Message outMessage,                                            // outgoing message
                inMessage;                                             // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }
        outMessage = new Message (MessageType.STPOS, elem, pos);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.SACK)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
    }

    /**
     * Set number of paitings on the walls
     * 
     * @param paitings on each room
     */
    public synchronized void setRoomPaitings(int[] paitings) {
        ClientCom com;                                                 // communication channel
        Message outMessage,                                            // outgoing message
                inMessage;                                             // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }
        outMessage = new Message (MessageType.STRMP, paitings);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.SACK)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
    }

    /**
     * Set distance of the rooms
     * 
     * @param distances of each room
     */
    public synchronized void setRoomDistances(int[] distances) {
        ClientCom com;                                                 // communication channel
        Message outMessage,                                            // outgoing message
                inMessage;                                             // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }
        outMessage = new Message (MessageType.STRD, distances);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.SACK)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
    }

    /**
     * Set robbed paintings
     */
    public synchronized void setRobbedPaintings() {
        ClientCom com;                                                 // communication channel
        Message outMessage,                                            // outgoing message
                inMessage;                                             // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }
        outMessage = new Message (MessageType.STRBP);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.SACK)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
    }





 
     /**
      *   Operation server shutdown.
      *
      *   New operation.
      */
 
     public void shutdown ()
     {
         ClientCom com;                                                 // communication channel
         Message outMessage,                                            // outgoing message
                 inMessage;                                             // incoming message
 
         com = new ClientCom (serverHostName, serverPortNumb);
         while (!com.open ())
         { try
         { Thread.sleep ((long) (1000));
         }
         catch (InterruptedException e) {}
         }
         outMessage = new Message (MessageType.SHUT);
         com.writeObject (outMessage);
         inMessage = (Message) com.readObject ();
         if (inMessage.getMsgType() != MessageType.SHUTDONE)
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
             GenericIO.writelnString (inMessage.toString ());
             System.exit (1);
         }
         com.close ();
     }
}

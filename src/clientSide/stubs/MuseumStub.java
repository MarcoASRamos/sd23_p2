package clientSide.stubs;

import serverSide.main.*;
import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;
import serverSide.sharedRegions.GeneralReposInterface;

public class MuseumStub {
    /**
     * Name of the platform where is located the bar server.
     */

     private String serverHostName;

     /**
      * Port number for listening to service requests.
      */
 
     private int serverPortNumb;
 
     /**
      * Instantiation of a stub to the bar.
      *
      * @param serverHostName name of the platform where is located the bar server
      * @param serverPortNumb port number for listening to service requests
      */
 
     public MuseumStub (String serverHostName, int serverPortNumb) {
         this.serverHostName = serverHostName;
         this.serverPortNumb = serverPortNumb;
     }



     /**
     * The thieve detaches the canvas from the framing, 
     * rolls it over and inserts it in a cylinder container 
     * Operation say Goodbye
     *
     * It is called by the waiter to say goodbye to a student that's leaving the restaurant
     * @param clientId id of the thread student that request this waiter operation
     * 
     * @param room where assault is happening
     * @param ap assault party
     * @param members id member
     * @return number of canvas stolen by the thieve
     */
    public synchronized int rollACanvas(int room, int ap, int members) {
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

        outMessage = new Message (MessageType.RAC, room, ap, members);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.RACDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
        return inMessage.rollACanvas();
    }



    public void shutdown () {
        ClientCom com;                    // communication channel
        Message outMessage,               // outgoing message
                inMessage;                // incoming message

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

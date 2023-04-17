package clientSide.stubs;

import serverSide.main.*;
import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;
import serverSide.sharedRegions.GeneralReposInterface;

public class AssaultPartyStub {
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
 
     public AssaultPartyStub (String serverHostName, int serverPortNumb) {
         this.serverHostName = serverHostName;
         this.serverPortNumb = serverPortNumb;
     }





     /**
     * Getter Assign an assault party id to the thieve
     * 
     * @param ap assault paty asssigned
     * @return member id
     */
    public synchronized int assignMember(int ap) {
        ClientCom com;                      // communication channel
        Message outMessage,                 // outgoing message
                inMessage;                  // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.AM, ap, ((Ordinary) Thread.currentThread()).getOrdinaryId());
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.AMDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
        return inMessage.assignMember();
    }



    /**
     * Getter room
     * 
     * @return room id
     */
    public synchronized int getRoom() {
        ClientCom com;                     // communication channel
        Message outMessage,                // outgoing message
                inMessage;                 // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.GRAP);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.GRAPDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
        return inMessage.getRoomAP();
    }



    /**
     * Last member indicate reverse direction to the first ordinary member
     * 
     * @param member id
     */
    public synchronized void reverseDirection(int member) {
        ClientCom com;                      // communication channel
        Message outMessage,                 // outgoing message
                inMessage;                  // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.RD, member, ((Ordinary) Thread.currentThread()).getOrdinaryId(), ((Ordinary) Thread.currentThread()).getOrdinaryState());
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.RDDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
    }

    /**
     * The master sends the assault party to the museum to heist an especific room
     * 
     * @param room to heist
     */
    public synchronized void sendAssaultParty(int room) {
        ClientCom com;                       // communication channel
        Message outMessage,                  // outgoing message
                inMessage;                   // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.SAP, room, ((Master) Thread.currentThread()).getMasterState());
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.SAPDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
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
        ClientCom com;                 // communication channel
        Message outMessage,            // outgoing message
                inMessage;             // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.CI, ap, member, md, ((Ordinary) Thread.currentThread()).getOrdinaryId() ,((Ordinary) Thread.currentThread()).getOrdinaryState());
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.CIDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
        return inMessage.crawlIn();
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
        ClientCom com;                  // communication channel
        Message outMessage,             // outgoing message
                inMessage;              // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.CO, ap, member, md, ((Ordinary) Thread.currentThread()).getOrdinaryId() ,((Ordinary) Thread.currentThread()).getOrdinaryState());
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.CODONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close (); 
        return inMessage.crawlOut();
    }




    public void shutdown () {
        ClientCom com;          // communication channel
        Message outMessage,     // outgoing message
                inMessage;      // incoming message

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

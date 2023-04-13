package clientSide.stubs;

import commInfra.MessageType;

public class ConcentrationSiteStub {
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
 
     public ConcentrationSiteStub (String serverHostName, int serverPortNumb) {
         this.serverHostName = serverHostName;
         this.serverPortNumb = serverPortNumb;
     }
 


     /**
     * Getter room assign to the assault party
     * 
     * @param ap assault party
     * @return room address to heist
     */
    public synchronized int getRoom(int ap){
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

        outMessage = new Message (MessageType.GRCS, ap);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.GRCSDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
        return inMessage.getRoom();
    }

    /**
     * Return one assault party available
     * 
     * @return assault party
     */
    public synchronized int getAssautlParty(){
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

        outMessage = new Message (MessageType.GAP);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.GAPDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
        return inMessage.getAssautlParty();
    }


    /**
     * The master thief appraise the situation of how the heist is going 
     * and takes a decision of is next step based on that
     * 
     * @param roomState indicate if all rooms are empty
     * @return the master decision
     */
    public synchronized int appraiseSit(boolean roomState) {
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

        outMessage = new Message (MessageType.AS, roomState);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.ASDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
        return inMessage.appraiseSit();
    }


    /**
     * The master thief prepares an assault party to lauch in excursion
     * 
     * @param ap assault party to prepare
     * @param room to assault
     */
    public synchronized void prepareAssaultParty(int ap, int room) {
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

        outMessage = new Message (MessageType.PAP, ap, room);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.PAPDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
    }


    /**
     * The ordinary thieve makes the last preparations before going in an excursion
     * 
     * @return joined assault party
     */
    public synchronized int prepareExcursion() {
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

        outMessage = new Message (MessageType.PE);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.PEDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
        return inMessage.prepareExcursion();
    }



    /**
     * The ordinary thief indicates to the master that he is available
     * 
     * @param ap assault party from which the ordinary thieve work before
     * @return master service decision
     */
    public synchronized boolean amINeeded(int ap){
        ClientCom com;                // communication channel
        Message outMessage,           // outgoing message
                inMessage;            // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.AIN, ap);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.AINDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
        return inMessage.amINeeded();
    }



    /**
     * All the paitings were finally robbed, now its time to count the gains
     */
    public synchronized void sumUpResults() {
        ClientCom com;                   // communication channel
        Message outMessage,              // outgoing message
                inMessage;               // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.SUTR, );
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.SUTRDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
    }



     

    public void shutdown () {
        ClientCom com;              // communication channel
        Message outMessage,         // outgoing message
                inMessage;          // incoming message

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

    public boolean getEndOp() {
        ClientCom com;               // communication channel
        Message outMessage,          // outgoing message
                inMessage;           // incoming message

        com = new ClientCom (serverHostName, serverPortNumb);
        while (!com.open ())
        { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
        }
        outMessage = new Message (MessageType.GENDOP);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.GENDOPDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
        return inMessage.getEndOp();
    }

    /**
     *  Operation end of work.
     *
     *   New operation.
     */
    public void endOperation() {
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
        outMessage = new Message (MessageType.ENDOP);
        com.writeObject (outMessage);
        inMessage = (Message) com.readObject ();
        if (inMessage.getMsgType() != MessageType.ENDOPDONE)
        { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
            GenericIO.writelnString (inMessage.toString ());
            System.exit (1);
        }
        com.close ();
    }
}

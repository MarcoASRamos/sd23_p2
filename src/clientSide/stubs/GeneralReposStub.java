
package clientSide.stubs;

import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;
import serverSide.main.SimulConsts;

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
      *   Operation initialization of the simulation.
      *
      *     @param fileName logging file name
      */
 
     public void initSimul (String fileName)
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
         outMessage = new Message (MessageType.LOGF, fileName);
         com.writeObject (outMessage);
         inMessage = (Message) com.readObject ();
         if (inMessage.getMsgType() != MessageType.LOGFDONE)
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
             GenericIO.writelnString (inMessage.toString ());
             System.exit (1);
         }
         com.close ();
     }
 
     /**
      *   Increment number of Portions.
      */
 
     public synchronized void updateNPortions () {
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
         outMessage = new Message (MessageType.UPNPRT);
         com.writeObject (outMessage);
         inMessage = (Message) com.readObject ();
         if (inMessage.getMsgType() != MessageType.UPNPRTDONE)
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
             GenericIO.writelnString (inMessage.toString ());
             System.exit (1);
         }
         com.close ();
     }
 
     /**
      *   Increment number of nCourses.
      */
 
     public synchronized void updateNCourses () {
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
         outMessage = new Message (MessageType.UPNCRS);
         com.writeObject (outMessage);
         inMessage = (Message) com.readObject ();
         if (inMessage.getMsgType() != MessageType.UPNCRSDONE)
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
             GenericIO.writelnString (inMessage.toString ());
             System.exit (1);
         }
         com.close ();
     }
 
     /**
      *   Set seating.
      *
      *     @param id value integer
      */
 
     public synchronized void setNSeats (int id) {
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
         outMessage = new Message (MessageType.SETNST, id);
         com.writeObject (outMessage);
         inMessage = (Message) com.readObject ();
         if (inMessage.getMsgType() != MessageType.SETNSTDONE)
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
             GenericIO.writelnString (inMessage.toString ());
             System.exit (1);
         }
         com.close ();
     }
 
 
 
 
 
 
     /**
      *   Set student state.
      *
      *     @param id student id
      *     @param state student state
      */
 
     public void setStudentState (int id, int state)
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
         outMessage = new Message (MessageType.STSST, id, state);
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
      *   Set waiter state.
      *
      *     @param id waiter id
      *     @param state waiter state
      */
 
     public void setWaiterState (int id, int state)
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
         outMessage = new Message (MessageType.STWST, id, state);
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
      *   Set chef state.
      *
      *     @param id chef id
      *     @param state chef state
      */
 
     public void setChefState (int id, int state)
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
         outMessage = new Message (MessageType.STCST, id, state);    //alterar para stcst - set chef state
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

package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import commInfra.*;



/**
 *  Interface to the Museum.
 *
 *    It is responsible to validate and process the incoming message, execute the corresponding method on the
 *    Museum and generate the outgoing message.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class MuseumInterface {
    /**
   *  Reference to the museum.
   */

   private final Museum museum;

   /**
    *  Instantiation of an interface to the museum.
    *
    *    @param museum reference to the museum
    */
 
    public MuseumInterface (Museum museum)
    {
       this.museum = museum;
    }
 
   /**
    *  Processing of the incoming messages.
    *
    *  Validation, execution of the corresponding method and generation of the outgoing message.
    *
    *    @param inMessage service request
    *    @return service reply
    *    @throws MessageException if the incoming message is not valid
    */
 
    public Message processAndReply (Message inMessage) throws MessageException {
         Message outMessage = null;                                     // outgoing message
   
         /* validation of the incoming message */
   
         switch (inMessage.getMsgType ()){

            // check nothing
            case MessageType.SHUT:
            case MessageType.RAC:     
               break;
            default:                   throw new MessageException ("Invalid message type!", inMessage);
         }
 


      /* processing */
 
      switch (inMessage.getMsgType ()) { 
         
         case MessageType.RAC: 
            int rac = museum.rollACanvas(inMessage.getRoom(), inMessage.getAp(), inMessage.getMember());
            outMessage = new Message (MessageType.RACDONE, rac);
            break;
                                    
         case MessageType.SHUT:     
            museum.shutdown ();
            outMessage = new Message (MessageType.SHUTDONE);
            break;
         }
 
      return (outMessage);
    }
}

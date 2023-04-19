package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import commInfra.*;


public class ControlCollectionSiteInterface {

    /**
   *  Reference to the ccs.
   */

   private final ControlCollectionSite ccs;

   /**
    *  Instantiation of an interface to the ccs.
    *
    *    @param ccs reference to the ccs
    */
 
    public ControlCollectionSiteInterface (ControlCollectionSite ccs)
    {
       this.ccs = ccs;
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
 
    public Message processAndReply (Message inMessage) throws MessageException
    {
        Message outMessage = null;                                     // outgoing message
    
        /* validation of the incoming message */
    
        switch (inMessage.getMsgType ()){ 

            //verify master state
            case MessageType.SO: 
            case MessageType.TAR:
            case MessageType.CAC:
                if ((inMessage.getMasterState () < MasterStates.PLANNING_THE_HEIST) || (inMessage.getMasterState () > MasterStates.PRESENTING_THE_REPORT))
                throw new MessageException ("Invalid master state!", inMessage);                     
                break;

            // check nothing
            case MessageType.SHUT:
            case MessageType.GRI:   
            case MessageType.HAC:
                break;
            default:                   throw new MessageException ("Invalid message type!", inMessage);
        } 

        /* processing */
 
        switch (inMessage.getMsgType ()) { 
            
            case MessageType.SO: 
            case MessageType.TAR:
            case MessageType.CAC:
            case MessageType.GRI:   
            case MessageType.HAC: break;
                                    
            case MessageType.SHUT:     ccs.shutdown ();
                                    outMessage = new Message (MessageType.SHUTDONE);
                                    break;
            }

        return (outMessage);
    }
    
}

package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import commInfra.*;


/**
 *  Interface to the Control Collection Site.
 *
 *    It is responsible to validate and process the incoming message, execute the corresponding method on the
 *    Control Collection Site and generate the outgoing message.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

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
                ((ControlCollectionSiteClientProxy) Thread.currentThread ()).setMasterState (inMessage.getMasterState ());
                ccs.startOperation();
                outMessage = new Message (MessageType.SODONE);
                break;

            case MessageType.TAR:
                ((ControlCollectionSiteClientProxy) Thread.currentThread ()).setMasterState (inMessage.getMasterState ());
                ccs.takeARest();
                outMessage = new Message (MessageType.TARDONE);
                break;

            case MessageType.CAC:
                ((ControlCollectionSiteClientProxy) Thread.currentThread ()).setMasterState (inMessage.getMasterState ());
                ccs.collectACanvas();
                outMessage = new Message (MessageType.CACDONE);
                break;

            case MessageType.GRI:   
                int gri = ccs.getRoomIdx();
                outMessage = new Message (MessageType.GRIDONE, gri);
                break;

            case MessageType.HAC: 
                ccs.handACanvas(inMessage.getCanvas(), inMessage.getRoom(), inMessage.getAp(), inMessage.getMember());
                outMessage = new Message (MessageType.HACDONE);
                break;
                                    
            case MessageType.SHUT:     
                ccs.shutdown ();
                outMessage = new Message (MessageType.SHUTDONE);
                break;
            }

        return (outMessage);
    }
    
}

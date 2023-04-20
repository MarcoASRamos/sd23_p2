package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import commInfra.*;

public class AssaultPartyInterface {
    
    /**
   *  Reference to the ap.
   */

   private final AssaultParty ap;

   /**
    *  Instantiation of an interface to the ap.
    *
    *    @param ap reference to the ap
    */
 
    public AssaultPartyInterface (AssaultParty ap)
    {
       this.ap = ap;
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

            //verify ordinary state
            case MessageType.AM: 
                if ((inMessage.getOrdinaryId () < 0) || (inMessage.getOrdinaryId () >= SimulConsts.M))
                    throw new MessageException ("Invalid ordinary id!", inMessage);                      
                break;

            //verify ordinary id and state
            case MessageType.RD:
            case MessageType.CI:
            case MessageType.CO:  
                if ((inMessage.getOrdinaryId () < 0) || (inMessage.getOrdinaryId () >= SimulConsts.M))
                    throw new MessageException ("Invalid ordinary id!", inMessage);
                else if ((inMessage.getOrdinaryState () < OrdinaryStates.CONCENTRATION_SITE) || (inMessage.getOrdinaryState () > OrdinaryStates.COLLECTION_SITE))
                    throw new MessageException ("Invalid ordinary state!", inMessage);
                break;

            //verify master state
            case MessageType.SAP: 
                if ((inMessage.getMasterState () < MasterStates.PLANNING_THE_HEIST) || (inMessage.getMasterState () > MasterStates.PRESENTING_THE_REPORT))
                throw new MessageException ("Invalid master state!", inMessage);                     
                break;

            // check nothing
            case MessageType.SHUT:
            case MessageType.GRAP:     
                break;
            default:                   throw new MessageException ("Invalid message type!", inMessage);
       }


 
        /* processing */
    
        switch (inMessage.getMsgType ()) { 
            
            case MessageType.AM: 
            ((AssaultPartyClientProxy) Thread.currentThread ()).setOrdinaryId (inMessage.getOrdinaryId ());
                int am = ap.assignMember(inMessage.getAp());
                outMessage = new Message (MessageType.GRAPDONE, am);
                break;

            case MessageType.RD: 
                ((AssaultPartyClientProxy) Thread.currentThread ()).setOrdinaryId (inMessage.getOrdinaryId ());
                ((AssaultPartyClientProxy) Thread.currentThread ()).setOrdinaryState (inMessage.getOrdinaryState ());
                ap.reverseDirection(inMessage.getMember());
                outMessage = new Message (MessageType.GRAPDONE);
                break;

            case MessageType.CI: 
                ((AssaultPartyClientProxy) Thread.currentThread ()).setOrdinaryId (inMessage.getOrdinaryId ());
                ((AssaultPartyClientProxy) Thread.currentThread ()).setOrdinaryState (inMessage.getOrdinaryState ());
                boolean ci = ap.crawlIn(inMessage.getAp(), inMessage.getMember(), inMessage.getMd());
                outMessage = new Message (MessageType.GRAPDONE, ci);
                break;

            case MessageType.CO: 
                ((AssaultPartyClientProxy) Thread.currentThread ()).setOrdinaryId (inMessage.getOrdinaryId ());
                ((AssaultPartyClientProxy) Thread.currentThread ()).setOrdinaryState (inMessage.getOrdinaryState ());
                boolean co = ap.crawlOut(inMessage.getAp(), inMessage.getMember(), inMessage.getMd());
                outMessage = new Message (MessageType.GRAPDONE, co);
                break;

            case MessageType.SAP:
                ((AssaultPartyClientProxy) Thread.currentThread ()).setMasterState (inMessage.getMasterState ());
                ap.sendAssaultParty(inMessage.getRoom());
                outMessage = new Message (MessageType.GRAPDONE);
                break;

            case MessageType.GRAP: 
                int gr = ap.getRoom();
                outMessage = new Message (MessageType.GRAPDONE, gr);
                break;
                                    
            case MessageType.SHUT:     
                ap.shutdown ();
                outMessage = new Message (MessageType.SHUTDONE);
                break;
            }

        return (outMessage);
    }
}

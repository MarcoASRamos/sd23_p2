package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import commInfra.*;


public class ConcentrationSiteInterface {

    /**
   *  Reference to the cs.
   */

   private final ConcentrationSite cs;

   /**
    *  Instantiation of an interface to the cs.
    *
    *    @param cs reference to the cs
    */
 
    public ConcentrationSiteInterface (ConcentrationSite cs)
    {
       this.cs = cs;
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

            //verify ordinary id and state
            case MessageType.PE:
            case MessageType.AIN:  
                if ((inMessage.getOrdinaryId () < 0) || (inMessage.getOrdinaryId () >= SimulConsts.M))
                    throw new MessageException ("Invalid ordinary id!", inMessage);
                else if ((inMessage.getOrdinaryState () < OrdinaryStates.CONCENTRATION_SITE) || (inMessage.getOrdinaryState () > OrdinaryStates.COLLECTION_SITE))
                    throw new MessageException ("Invalid ordinary state!", inMessage);
                break;

            //verify master state
            case MessageType.PAP: 
            case MessageType.SUTR: 
                if ((inMessage.getMasterState () < MasterStates.PLANNING_THE_HEIST) || (inMessage.getMasterState () > MasterStates.PRESENTING_THE_REPORT))
                throw new MessageException ("Invalid master state!", inMessage);                     
                break;

            // check nothing
            case MessageType.SHUT:
            case MessageType.GRCS: 
            case MessageType.GAP: 
            case MessageType.AS:     
                break;
            default:                   throw new MessageException ("Invalid message type!", inMessage);
        }
 
        /* processing */
    
        switch (inMessage.getMsgType ()) { 
            
            case MessageType.PE:
                ((ConcentrationSiteClientProxy) Thread.currentThread ()).setOrdinaryId (inMessage.getOrdinaryId ());
                ((ConcentrationSiteClientProxy) Thread.currentThread ()).setOrdinaryState (inMessage.getOrdinaryState ());
                int pe = cs.prepareExcursion();
                outMessage = new Message (MessageType.PEDONE, pe);
                break;

            case MessageType.AIN: 
                ((ConcentrationSiteClientProxy) Thread.currentThread ()).setOrdinaryId (inMessage.getOrdinaryId ());
                ((ConcentrationSiteClientProxy) Thread.currentThread ()).setOrdinaryState (inMessage.getOrdinaryState ());
                System.out.println("AMIneeded interf");
                boolean ain = cs.amINeeded(inMessage.getAp());
                System.out.println("AMIneeded interf done");
                outMessage = new Message (MessageType.AINDONE, ain);
                break;

            case MessageType.PAP: 
                ((ConcentrationSiteClientProxy) Thread.currentThread ()).setMasterState (inMessage.getMasterState ());
                cs.prepareAssaultParty(inMessage.getAp(), inMessage.getRoom());
                outMessage = new Message (MessageType.PAPDONE);
                    break;

            case MessageType.SUTR: 
                ((ConcentrationSiteClientProxy) Thread.currentThread ()).setMasterState (inMessage.getMasterState ());
                cs.sumUpResults();
                outMessage = new Message (MessageType.SUTRDONE);
                break;

            case MessageType.GRCS: 
                int gr = cs.getRoom(inMessage.getAp());
                outMessage = new Message (MessageType.GRCSDONE, gr);
                break;

            case MessageType.GAP: 
                int gap = cs.getAssautlParty();
                outMessage = new Message (MessageType.GAPDONE, gap);
                break;

            case MessageType.AS: 
                System.out.println("appraiseSit interf");
                int as = cs.appraiseSit(inMessage.getRoomStt());
                System.out.println("appraiseSit interf done");
                outMessage = new Message (MessageType.ASDONE, as);
                break;
            
            case MessageType.ENDOP:     
                cs.endOperation(inMessage.getOrdinaryId());
                outMessage = new Message (MessageType.SHUTDONE);
                break;    

            case MessageType.SHUT:     
                cs.shutdown ();
                outMessage = new Message (MessageType.SHUTDONE);
                break;
            }

        return (outMessage);
    }
}

package serverSide.sharedRegions;

import clientSide.entities.*;
import commInfra.*;
import serverSide.entities.GeneralReposClientProxy;
import serverSide.main.SimulConsts;

/**
 * Interface to the General Repository of Information
 *
 * It is responsible to validate and process the incoming message, execute the
 * corresponding method on the
 * General Repository and generate the outgoing message.
 * Implementation of a client-server model of type 2 (server replication).
 * Communication is based on a communication channel under the TCP protocol.
 */
public class GeneralReposInterface {
    /**
     * Reference to the General Repos
     */
    private final GeneralRepos repos;

    /**
     * Instantiation of an interface to the General Repos.
     * 
     * @param repos reference to the General Repository
     */
    public GeneralReposInterface(GeneralRepos repos) {
        this.repos = repos;
    }

    /**
     * Processing of the incoming messages
     * Validation, execution of the corresponding method and generation of the
     * outgoing message.
     * 
     * @param inMessage service request
     * @return service reply
     * @throws MessageException if incoming message was not valid
     */
    public Message processAndReply(Message inMessage) throws MessageException {
        // outGoing message
        Message outMessage = null;

        /* Validation of the incoming message */

        switch (inMessage.getMsgType()) {

            // verify Master state
            case MessageType.:
                if ((inMessage.getMasterState () < MasterStates.PLANNING_THE_HEIST) || (inMessage.getMasterState () > MasterStates.PRESENTING_THE_REPORT))
                throw new MessageException ("Invalid master state!", inMessage);
                break;

            // verify Oedinary state
            case MessageType.:
                if ((inMessage.getOrdinaryId () < 0) || (inMessage.getOrdinaryId () >= SimulConsts.M))
                throw new MessageException ("Invalid ordinary id!", inMessage); 
                break;

            // verify only message type
            case MessageType.STMST:
            case MessageType.STOST:
            case MessageType.STOSIT:
            case MessageType.STOMD:
            case MessageType.STAPR:
            case MessageType.STAPE:
            case MessageType.STCVS:
            case MessageType.STPOS:
            case MessageType.STRMP:
            case MessageType.STRD:
            case MessageType.STRBP:
                break;
            default:
                throw new MessageException("Invalid message type!", inMessage);
        }

        /* Processing of the incoming message */

        switch (inMessage.getMsgType()) {

            case MessageType.STMST:
                repos.setMasterState(inMessage.());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STOST:
                repos.setOrdinaryState(inMessage.());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STOSIT:
                repos.setOrdinarySituation(inMessage.());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STOMD:
                repos.setOrdinariesMD(inMessage.());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STAPR:
                repos.setApRoom(inMessage.());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STAPE:
                repos.setApElement(inMessage.());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STCVS:
                repos.setCanvas(inMessage.());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STPOS:
                repos.setPosition(inMessage.());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STRMP:
                repos.setRoomPaitings(inMessage.());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STRD:
                repos.setRoomDistances(inMessage.());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STRBP:
                repos.setRobbedPaintings(inMessage.());
                outMessage = new Message(MessageType.SACK);
                break;


            case MessageType.:
                repos.(inMessage.());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.SHUT:
                repos.shutdown();
                outMessage = new Message(MessageType.SHUTDONE);
                break;
        }
        return (outMessage);
    }

}
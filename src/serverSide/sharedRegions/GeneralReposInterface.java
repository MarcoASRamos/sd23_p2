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

            // verify only message type
            case MessageType.STMST:
            case MessageType.STOST:
            case MessageType.STOSIT:
            case MessageType.GTOMD:
            case MessageType.STAPR:
            case MessageType.STAPE:
            case MessageType.STCVS:
            case MessageType.STPOS:
            case MessageType.STRMP:
            case MessageType.GTRD:
            case MessageType.STRBP:
                break;
            default:
                throw new MessageException("Invalid message type!", inMessage);
        }

        /* Processing of the incoming message */

        switch (inMessage.getMsgType()) {

            case MessageType.STMST:
                repos.setMasterState(inMessage.getMasterState());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STOST:
                repos.setOrdinaryState(inMessage.getOrdinaryId(), inMessage.getOrdinaryState());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STOSIT:
                repos.setOrdinarySituation(inMessage.getOrdinaryId(), inMessage.getSit());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.GTOMD:
                int md = repos.getOrdinaryMD(inMessage.getOrdinaryId());
                outMessage = new Message(MessageType.GTOMDDONE, md);
                break;

            case MessageType.STAPR:
                repos.setApRoom(inMessage.getAp(), inMessage.getRoom());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STAPE:
                repos.setApElement(inMessage.getElem(), inMessage.getOrdinaryId());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STCVS:
                repos.setCanvas(inMessage.getElem(), inMessage.getCanvas());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STPOS:
                repos.setPosition(inMessage.getElem(), inMessage.getPos());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.STRMP:
                repos.setRoomPaitings(inMessage.getPaintings());
                outMessage = new Message(MessageType.SACK);
                break;

            case MessageType.GTRD:
                int[] dists = repos.getRoomDistances();
                outMessage = new Message(MessageType.GTRDDONE, dists);
                break;

            case MessageType.STRBP:
                repos.setRobbedPaintings();
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
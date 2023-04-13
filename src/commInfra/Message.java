package commInfra;

import java.io.*;

import clientSide.stubs.*;
import genclass.GenericIO;

/**
 *   Internal structure of the exchanged messages.
 *
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */

public class Message implements Serializable
{
    /**
     *  Serialization key.
     */

    private static final long serialVersionUID = 2021L;

    /**
     *  Message type.
     */

    private int msgType = -1;

    /**
     *  Master identification.
     */

    private int masterId = -1;

    /**
     *  Master state.
     */

    private int masterState = -1;

    /**
     *  Ordinary identification.
     */

    private int ordinaryId = -1;

    /**
     *  Ordinary state.
     */

    private int ordinaryState = -1;


    /**
     *  End of operations.
     */
    private boolean endOp = false;

    /**
     *  Name of the logging file.
     */

    private String fName = null;







    /**
     *  Course number.
     */

    private int course = -1;





    /**
     *  Message instantiation (form 1).
     *
     *     @param type message type
     */

    public Message (int type)
    {
        msgType = type;
    }

    /**
     *  Message instantiation (form 2).
     *
     *     @param type message type
     *     @param id Master / client identification
     */

    public Message (int type, int id)
    {
        msgType = type;
        if ((msgType == MessageType.CWTR) || )
            studId= id;
        else if ((msgType == MessageType.SGBY)){
            clientId= id;
            GenericIO.writelnString("\n\n\nMessage client id "+clientId);
        }

        else if ((msgType == MessageType.RBAR) || (msgType == MessageType.CPRTDONE))
            OrdinaryState = id;
        else if ((msgType == MessageType.SPREP) || (msgType == MessageType.PPRES) ||
                (msgType == MessageType.CLEANDONE) || (msgType == MessageType.CPREPDONE))
            chefState = id;
        else if((msgType == MessageType.SETNST))
            seatId = id;
        else if((msgType == MessageType.SETLARV) || (msgType == MessageType.GETLARVDONE))
            lastArv = id;
        else if((msgType == MessageType.SETFARV) || (msgType == MessageType.GETFARVDONE))
            firstArv = id;
        else if((msgType == MessageType.GETLEATDONE))
            lastEat = id;
        else if((msgType == MessageType.GCHAT))
            course = id;
        else {
            GenericIO.writelnString ("Message type = " + msgType + ": non-implemented instantiation!");
            System.exit (1);
        }
    }

    /**
     * seat id logger
     */
    private int seatId = -1;

    /**
     * getter seat id
     */
    public int getSeatId(){
        return seatId;
    }

    /**
     *  Message instantiation (form 3).
     *
     *     @param type message type
     *     @param id Master identification
     *     @param state Master state
     */

    public Message (int type, int id, int state)
    {
        msgType = type;
        if((msgType == MessageType.ETR) || (msgType == MessageType.SARVE) ||
                (msgType == MessageType.EXIT) || (msgType == MessageType.RMENU) ||
                (msgType == MessageType.PREPO) || (msgType == MessageType.INFCP) ||
                (msgType == MessageType.JTLK) || (msgType == MessageType.SEAT) ||
                (msgType == MessageType.EEAT)|| (msgType == MessageType.STSST) ||
                (msgType == MessageType.ETRDONE) || (msgType == MessageType.SARVEDONE)||
                (msgType == MessageType.EXITDONE) || (msgType == MessageType.RMENUDONE)||
                (msgType == MessageType.INFCPDONE) || (msgType == MessageType.PREPODONE)||
                (msgType == MessageType.JTLKDONE) || (msgType == MessageType.SEATDONE)){
            studId= id;
            studState = state;
        }
        else if((msgType == MessageType.BAWTR)|| (msgType == MessageType.BAWTRDONE)){
            chefId= id;
            chefState = state;
        }
        else if((msgType == MessageType.PRESB) || (msgType == MessageType.SALC)){
            clientId = id;
            OrdinaryState = state;
        }
        else if((msgType == MessageType.EVRFIN)){
            studId = id;
            course = state;
        }
        else if((msgType == MessageType.STWST)) {
            OrdinaryId = id;
            OrdinaryState = state;
        } else {
            GenericIO.writelnString ("Message type = " + msgType + ": non-implemented instantiation!");
            System.exit (1);
        }
    }

    /**
     *  Message instantiation (form 7).
     *
     *     @param type message type
     *     @param bool boolean flags
     */

    public Message (int type, boolean bool) {
        msgType = type;
        if((msgType == MessageType.HAPBD))
            haveDelivered = bool;
        else if((msgType == MessageType.ACSRVDONE))
            haveServed = bool;
        else if((msgType == MessageType.HTOBCDONE))
            hasCompleted = bool;
        else if((msgType == MessageType.EVRCHSDONE))
            hasChosen = bool;
        else if((msgType == MessageType.ENDOPDONE))
           endOp = bool;
    }

    /**
     *  Message instantiation (form 9).
     *
     *     @param type message type
     *     @param fName log file
     */
    public Message (int type, String fName){
        msgType = type;
        this.fName = fName;
    }



    /**
     *  Getting message type.
     *
     *     @return message type
     */

    public int getMsgType ()
    {
        return (msgType);
    }

    /**
     *  Getting Master identification.
     *
     *     @return Master identification
     */

    public int getMasterId ()
    {
        return (masterId);
    }

    /**
     *  Getting Master state.
     *
     *     @return Master state
     */

    public int getMasterState ()
    {
        return (masterState);
    }

    /**
     *  Getting Ordinary identification.
     *
     *     @return Ordinary identification
     */

    public int getOrdinaryId ()
    {
        return (ordinaryId);
    }

    /**
     *  Getting Ordinary state.
     *
     *     @return Ordinary state
     */

    public int getOrdinaryState ()
    {
        return (ordinaryState);
    }





    /**
     *  Getting have all clients been served served
     *
     *  @return haveServed
     */

    public boolean getHaveServed ()
    {
        return (haveServed);
    }



    /**
     *  Getting end of operations flag (barber).
     *
     *     @return end of operations flag
     */

    public boolean getEndOp ()
    {
        return (endOp);
    }

    /**
     *  Getting name of logging file.
     *
     *     @return name of the logging file
     */

    public String getLogFName ()
    {
        return (fName);
    }

    /**
     *  Printing the values of the internal fields.
     *
     *  It is used for debugging purposes.
     *
     *     @return string containing, in separate lines, the pair field name - field value
     */

    @Override
    public String toString ()
    {
        return ("Message type = " + msgType +
                "\nMaster Id = " + studId +
                "\nMaster State = " + studState +
                "\nOrdinary Id = " + OrdinaryId +
                "\nOrdinary State = " + OrdinaryState +
                "\nEnd of Operations (Ordinary) = " + endOp +

                
            
                "\nCourse = " + course +
                "\nhasChosen = " + hasChosen +
                "\nhasCompleted = " + hasCompleted +
                "\nhaveDelivered = " + haveDelivered +
                "\nhaveServed = " + haveServed +
                "\nlastEat = " + lastEat +

                "\nservice = " + (service == null? "null" : service.toString()) +
                "\nName of logging file = " + fName );
    }
}

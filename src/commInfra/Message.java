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
     *  Client identification.
     */

    private int clientId = -1;







    /**
     *  Course number.
     */

    private int course = -1;

    /**
     *  Verify if all students chosen.
     */

    private boolean hasChosen = false;

    /**
     *  Verify if have all clients been served.
     */
    private boolean haveServed = false;

    /**
     *  Verify if has order been completed.
     */
    private boolean hasCompleted = false;

    /**
     *  Verify if have all portions been delivered.
     */
    private boolean haveDelivered = false;

    /**
     * Id of last Eat student
     */
    private int lastEat=-1;

    /**
     * Id of last arrived student
     */
    private int lastArv = -1;

    /**
     * Id of first arrived student
     */
    private int firstArv = -1;






    /**
     * waiter service requested
     */
    private ServiceRequest service = null;

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
     *     @param id student / client identification
     */

    public Message (int type, int id)
    {
        msgType = type;
        if ((msgType == MessageType.CWTR) || (msgType == MessageType.SWTR) ||
                (msgType == MessageType.HBILL) || (msgType == MessageType.STAB)||
                (msgType == MessageType.CWTRDONE) || (msgType == MessageType.HBILLDONE)||
                (msgType == MessageType.SWTRDONE) || (msgType == MessageType.STABDONE)||
                (msgType == MessageType.EVRFINDONE) || (msgType == MessageType.GCHATDONE))
            studId= id;
        else if ((msgType == MessageType.SGBY)){
            clientId= id;
            GenericIO.writelnString("\n\n\nMessage client id "+clientId);
        }

        else if ((msgType == MessageType.RBAR) || (msgType == MessageType.GPAD) ||
                (msgType == MessageType.PREPB) || (msgType == MessageType.NOTEC) ||
                (msgType == MessageType.CPRT) || (msgType == MessageType.RBARDONE) ||
                (msgType == MessageType.LOKADONE) || (msgType == MessageType.GPADDONE) ||
                (msgType == MessageType.PREPBDONE) || (msgType == MessageType.PRESBDONE)||
                (msgType == MessageType.SALCDONE) || (msgType == MessageType.NOTECDONE) ||
                (msgType == MessageType.CPRTDONE))
            waiterState = id;
        else if ((msgType == MessageType.SPREP) || (msgType == MessageType.PPRES) ||
                (msgType == MessageType.HNPR) || (msgType == MessageType.KAWTR) ||
                (msgType == MessageType.CPREP) || (msgType == MessageType.CLEAN) ||
                (msgType == MessageType.SPREPDONE) || (msgType == MessageType.PPRESDONE)||
                (msgType == MessageType.HNPRDONE) || (msgType == MessageType.KAWTRDONE)||
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
     *     @param id student identification
     *     @param state student state
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
        else if((msgType == MessageType.BAWTR)|| (msgType == MessageType.BAWTRDONE) || (msgType == MessageType.STCST)){
            chefId= id;
            chefState = state;
        }
        else if((msgType == MessageType.PRESB) || (msgType == MessageType.SALC)){
            clientId = id;
            waiterState = state;
        }
        else if((msgType == MessageType.EVRFIN)){
            studId = id;
            course = state;
        }
        else if((msgType == MessageType.STWST)) {
            waiterId = id;
            waiterState = state;
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
     *  Message instantiation (form 8).
     *
     *     @param type message type
     *     @param service waiter service requested
     */

    public Message (int type, ServiceRequest service) {
        msgType = type;
        this.service = service;
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
     * waiter service ruequested
     */
    private ServiceRequest service = null;

    /**
     *  Getting message type.
     *
     *     @return service
     */

    public ServiceRequest getServiceRequested ()
    {
        return (service);
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
     *  Getting student identification.
     *
     *     @return student identification
     */

    public int getStudentId ()
    {
        return (studId);
    }

    /**
     *  Getting student state.
     *
     *     @return student state
     */

    public int getStudentState ()
    {
        return (studState);
    }

    /**
     *  Getting waiter identification.
     *
     *     @return waiter identification
     */

    public int getWaiterId ()
    {
        return (waiterId);
    }

    /**
     *  Getting waiter state.
     *
     *     @return waiter state
     */

    public int getWaiterState ()
    {
        return (waiterState);
    }


    /**
     *  Getting client id.
     *
     *     @return client id
     */

    public int getClientId ()
    {
        return (clientId);
    }

    /**
     *  Getting course.
     *
     *     @return course
     */

    public int getCourse ()
    {
        return (course);
    }

    /**
     *  Getting last arrived student.
     *
     *     @return lastArv
     */

    public int getLastToArrive ()
    {
        return (lastArv);
    }

    /**
     *  Getting first arrived student.
     *
     *  @return firstArv
     */

    public int getFirstToArrive ()
    {
        return (firstArv);
    }

    /**
     *  Getting last eating.
     *
     *  @return lastEat
     */

    public int getLastEat ()
    {
        return (lastEat);
    }

    /**
     *  Getting has chosen.
     *
     *  @return hasChosen
     */

    public boolean getHasChosen ()
    {
        return (hasChosen);
    }

    /**
     *  Getting has completed.
     *
     *  @return hasCompleted
     */

    public boolean getHasCompleted ()
    {
        return (hasCompleted);
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
     *  Getting have all portions been delivered
     *
     *  @return haveDelivered
     */

    public boolean getHaveDelivered ()
    {
        return (haveDelivered);
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
                "\nOrdinary Id = " + waiterId +
                "\nOrdinary State = " + waiterState +
                "\nEnd of Operations (waiter) = " + endOp +

                
                

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

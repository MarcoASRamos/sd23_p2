package commInfra;

/**
 *   Type of the exchanged messages.
 *the
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */

public class MessageType
{
    /**
     *  Initialization of the logging file name (service request).
     */

    public static final int LOGF = 1;

    /**
     *  Logging file was initialized (reply).
     */

    public static final int LOGFDONE = 2;

    /**
     *  Server shutdown (service request).
     */

    public static final int SHUT = 3;

    /**
     *  Server was shutdown (reply).
     */

    public static final int SHUTDONE = 4;









    /**
     *  Set waiter state (service request).
     */

    public static final int STWST = 5;

    /**
     *  Set student state (service request).
     */

    public static final int STSST = 6;

    /**
     *  Set Chef states (service request).
     */

    public static final int STCST = 7;

    /**
     *  Setting acknowledged (reply).
     */

    public static final int SACK = 8;





    //master msg

    /**
     *  Request start operation (service request).
     */

    public static final int  SO = 9;

    /**
     *  Operation started started (reply).
     */

    public static final int  SODONE = 10;

    /**
     *  Request get room index (service request).
     */

    public static final int  GRI = 11;

    /**
     *  Get room index (reply).
     */

    public static final int  GRIDONE = 12;

    /**
     *  Request appraise situation (service request).
     */

    public static final int AS = 13;

    /**
     *  Appraise situation (reply).
     */

    public static final int ASDONE = 14;

    /**
     *  Request prepare assult party (service request).
     */

    public static final int PAP = 15;

    /**
     *  Prepare assult party (reply).
     */

    public static final int PAPDONE = 16;

    /**
     *  Request Send assault party (service request).
     */

    public static final int SAP = 17;

    /**
     *  Send assault party (reply).
     */

    public static final int SAPDONE = 18;

    /**
     *  Request get room (service request).
     */

    public static final int GR = 19;

    /**
     *  get room done (reply).
     */

    public static final int GRDONE = 20;

    /**
     *  Request take a canvas (service request).
     */

    public static final int TAC = 21;

    /**
     *  take a canvas (reply).
     */

    public static final int TACDONE = 22;

    /**
     *  Request collect a canvas (service request).
     */

    public static final int CAC = 23;

    /**
     *  collect a canvas done (reply).
     */

    public static final int CACDONE = 24;

    /**
     *  Request sum up the results (service request).
     */

    public static final int SUTR = 25;

    /**
     *  sum up the results done (reply).
     */

    public static final int SUTRDONE = 26;











    /**
     *  Request am I needed (service request).
     */

    public static final int AIN = 27;

    /**
     *  am I needed done (reply).
     */

    public static final int AINDONE = 28;

    /**
     *  Request prepare excursion (service request).
     */

    public static final int PE = 29;

    /**
     *  prepare excursion done (reply).
     */

    public static final int PEDONE = 30;

    /**
     *  Request assign member (service request).
     */

    public static final int AM = 31;

    /**
     * assign member done (reply).
     */
    public static final int AMDONE = 32;

    /**
     *  Request crawl in (service request).
     */

    public static final int CI = 33;

    /**
     *  crawl in done (reply).
     */

    public static final int CIDONE = 34;




    //////get room dos ordinarios?????




    /**
     *  Request roll a canvas (service request).
     */

    public static final int RAC = 35;

    /**
     *  roll a canvas done (reply).
     */

    public static final int RACDONE = 36;

    /**
     *  Request reverse direction (service request).
     */

    public static final int RD = 37;

    /**
     * reverse direction done (reply).
     */

    public static final int RDDONE = 38;

    /**
     *  Request crawl out (service request).
     */

    public static final int CO = 39;

    /**
     *  crawl out done (reply).
     */

    public static final int CODONE = 40;

    /**
     *  Request hand a canvas (service request).
     */

    public static final int HAC = 41;

    /**
     * hand a canvas done (reply).
     */

    public static final int HACDONE = 42;











    


    /**
     *  Request exit (service request).
     */

    public static final int EXIT = 85;

    /**
     * exit done (reply).
     */
    public static final int EXITDONE = 86;


    //getters e setters
    /**
     *  Request  (getter request).
     */

    public static final int GETFARV = 87;

    /**
     *  done (getter reply).
     */

    public static final int GETFARVDONE = 88;

    /**
     *  Request  (setter request).
     */

    public static final int SETFARV = 89;

    /**
     *  done (setter reply).
     */

    public static final int SETFARVDONE = 90;

    /**
     *  Request get last to arrive (getter request).
     */

    public static final int GETLARV = 91;

    /**
     * get last to arrive done (getter reply).
     */

    public static final int GETLARVDONE = 92;

    /**
     *  Request set last to arrive (setter request).
     */

    public static final int SETLARV = 93;

    /**
     * set last to arrive done (setter reply).
     */

    public static final int SETLARVDONE = 94;

    /**
     *  Request get last eat (getter request).
     */

    public static final int GETLEAT = 95;

    /**
     * get last eat done (getter reply).
     */

    public static final int GETLEATDONE = 96;











    /**
     *  End of work - waiter (service request).
     */

    public static final int ENDOP = 97;

    /**
     *  waiter goes home (reply).
     */

    public static final int ENDOPDONE = 98;

    /**
     *  End of work - waiter (service request).
     */

    public static final int UPNPRT = 99;

    /**
     *  waiter goes home (reply).
     */

    public static final int UPNPRTDONE = 100;

    /**
     *  End of work - waiter (service request).
     */

    public static final int UPNCRS = 101;

    /**
     *  waiter goes home (reply).
     */

    public static final int UPNCRSDONE = 102;

    /**
     *  End of work - waiter (service request).
     */

    public static final int SETNST = 103;

    /**
     *  waiter goes home (reply).
     */

    public static final int SETNSTDONE = 104;

    /**
     *  End of work - waiter (service request).
     */

    public static final int GENDOP = 105;

    /**
     *  waiter goes home (reply).
     */

    public static final int GENDOPDONE = 106;

}

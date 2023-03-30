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

    //chef msg

    /**
     *  Request start watch news (service request).
     */

    public static final int  WNWS = 9;

    /**
     *  Preparation started (reply).
     */

    public static final int  WNWSDONE = 10;

    /**
     *  Request start preparation (service request).
     */

    public static final int  SPREP = 11;

    /**
     *  Preparation started (reply).
     */

    public static final int  SPREPDONE = 12;

    /**
     *  Request proceed preparation (service request).
     */

    public static final int PPRES = 13;

    /**
     *  Presentation proceeded (reply).
     */

    public static final int PPRESDONE = 14;

    /**
     *  Request from kitchen alert waiter (service request).
     */

    public static final int KAWTR = 15;

    /**
     *  waiter alerted (reply).
     */

    public static final int KAWTRDONE = 16;

    /**
     *  Request from Bar alert waiter (service request).
     */

    public static final int BAWTR = 17;

    /**
     *  waiter alerted (reply).
     */

    public static final int BAWTRDONE = 18;

    /**
     *  Request Have Next Portion Ready (service request).
     */

    public static final int HNPR = 19;

    /**
     *  Next Portion Ready done (reply).
     */

    public static final int HNPRDONE = 20;

    /**
     *  Request Continue preparation (service request).
     */

    public static final int CPREP = 21;

    /**
     * Preparation continued  (reply).
     */

    public static final int CPREPDONE = 22;

    /**
     *  Request have all portions been delivered (service request).
     */

    public static final int HAPBD = 23;

    /**
     *  have all portions been delivered done (reply).
     */

    public static final int HAPBDDONE = 24;

    /**
     *  Request has the order been completed (service request).
     */

    public static final int HTOBC = 25;

    /**
     *  has the order been completed (reply).
     */

    public static final int HTOBCDONE = 26;

    /**
     *  Request clean up (service request).
     */

    public static final int CLEAN = 27;

    /**
     *  kitchen cleaned (reply).
     */

    public static final int CLEANDONE = 28;


    //waiter msgs
    /**
     *  Request look around (service request).
     */

    public static final int LOKA = 29;

    /**
     *  look around done (reply).
     */

    public static final int LOKADONE = 30;

    /**
     *  Request say goodbye (service request).
     */

    public static final int SGBY = 31;

    /**
     * say goodbye done (reply).
     */
<<<<<<< HEAD:ASS2_T2_Gx/src/commInfra/MessageType.java
=======

>>>>>>> b0f551e8d8b4ec0c35aee2d4cf2084d505de2dd1:Ass2_T2_Gx/src/commInfra/MessageType.java
    public static final int SGBYDONE = 32;

    /**
     *  Request salute the client (service request).
     */

    public static final int SALC = 33;

    /**
     *  salute the client done (reply).
     */

    public static final int SALCDONE = 34;

    /**
     *  Request return to the bar (service request).
     */

    public static final int RBAR = 35;

    /**
     * return to the bar done (reply).
     */

    public static final int RBARDONE = 36;

    /**
     *  Request get the pad (service request).
     */

    public static final int GPAD = 37;

    /**
     * get the pad done (reply).
     */

    public static final int GPADDONE = 38;

    /**
     *  Request hand the note to the chef (service request).
     */

    public static final int NOTEC = 39;

    /**
     * hand the note to the chef done (reply).
     */

    public static final int NOTECDONE = 40;

    /**
     *  Request collect portion (service request).
     */

    public static final int CPRT = 41;

    /**
     * collect portion done (reply).
     */

    public static final int CPRTDONE = 42;

    /**
     *  Request deliver portion (service request).
     */

    public static final int DPRT = 43;

    /**
     * deliver portion done (reply).
     */

    public static final int DPRTDONE = 44;

    /**
     *  Request have all clients been served (service request).
     */

    public static final int ACSRV = 45;

    /**
     * have all clients been served done (reply).
     */

    public static final int ACSRVDONE = 46;

    /**
     *  Request prepare the bill (service request).
     */

    public static final int PREPB = 47;

    /**
     * prepare the bill done (reply).
     */

    public static final int PREPBDONE = 48;

    /**
     *  Request present the bill (service request).
     */

    public static final int PRESB = 49;

    /**
     * present the bill done (reply).
     */

    public static final int PRESBDONE = 50;



    //student msgs
    /**
     *  Request enter (service request).
     */

    public static final int ETR = 51;

    /**
     * enter done (reply).
     */

    public static final int ETRDONE = 52;

    /**
     *  Request seat at table (service request).
     */

    public static final int STAB = 53;

    /**
     * seat at table done (reply).
     */

    public static final int STABDONE = 54;

    /**
     *  Request read the menu (service request).
     */

    public static final int RMENU = 55;

    /**
     * read the menu done (reply).
     */

    public static final int RMENUDONE = 56;

    /**
     *  Request prepare the order (service request).
     */

    public static final int PREPO = 57;

    /**
     * prepare the order done (reply).
     */

    public static final int PREPODONE = 58;

    /**
     *  Request add up ones choice (service request).
     */

    public static final int ADDCHC = 59;

    /**
     * add up ones choice done (reply).
     */

    public static final int ADDCHCDONE = 60;

    /**
     *  Request has everybody chosen (service request).
     */

    public static final int EVRCHS = 61;

    /**
     * has everybody chosen done (reply).
     */

    public static final int EVRCHSDONE = 62;

    /**
     *  Request call the waiter (service request).
     */

    public static final int CWTR = 63;

    /**
     * call the waiter done (reply).
     */

    public static final int CWTRDONE = 64;

    /**
     *  Request describe the order (service request).
     */

    public static final int DSBO = 65;

    /**
     * describe the order done (reply).
     */

    public static final int DSBODONE = 66;

    /**
     *  Request join the talk (service request).
     */

    public static final int JTLK = 67;

    /**
     * join the talk done (reply).
     */

    public static final int JTLKDONE = 68;

    /**
     *  Request inform companion (service request).
     */

    public static final int INFCP = 69;

    /**
     * inform companion done (reply).
     */

    public static final int INFCPDONE = 70;

    /**
     *  Request has everybody finished (service request).
     */

    public static final int EVRFIN = 71;

    /**
     * has everybody finished  done (reply).
     */

    public static final int EVRFINDONE = 72;

    /**
     *  Request go chatting (service request).
     */

    public static final int GCHAT = 73;

    /**
     * go chating  done (reply).
     */
    public static final int GCHATDONE = 74;

    /**
     *  Request signal the waiter (service request).
     */

    public static final int SWTR = 75;

    /**
     * signal the waiter done (reply).
     */

    public static final int SWTRDONE = 76;

    /**
     *  Request start eating (service request).
     */

    public static final int SEAT = 77;

    /**
     * start eating done (reply).
     */

    public static final int SEATDONE = 78;

    /**
     *  Request end eating (service request).
     */

    public static final int EEAT = 79;

    /**
     * end eating done (reply).
     */

    public static final int EEATDONE = 80;

    /**
     *  Request should have arrived earlier (service request).
     */

    public static final int SARVE = 81;

    /**
     * should have arrived earlier done (reply).
     */

    public static final int SARVEDONE = 82;

    /**
     *  Request honour the bill (service request).
     */

    public static final int HBILL = 83;

    /**
     * honour the bill done (reply).
     */

    public static final int HBILLDONE = 84;

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

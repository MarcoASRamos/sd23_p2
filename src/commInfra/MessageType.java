package commInfra;

/**
 *   Type of the exchanged messages.
 *the
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */

public class MessageType
{
    // repos functions 

    /**
     *  Server shutdown (service request).
     */

    public static final int SHUT = 1;

    /**
     *  Server was shutdown (reply).
     */

    public static final int SHUTDONE = 2;

    /**
     *  End of work - master (service request).
     */

     public static final int ENDOP = 3;

     /**
      *  master goes home (reply).
      */
 
     public static final int EOPDONE = 4;


    /**
     *  Set master state (service request).
     */

    public static final int STMST = 5;


    /**
     *  Set ordinary state (service request).
     */

    public static final int STOST = 6;

    /**
     *  Set ordinary situation (service request).
     */

     public static final int STOSIT = 7;

    /**
     *  Set ordinary md (service request).
     */

     public static final int STOMD = 8;

    /**
     *  Set ap room (service request).
     */

     public static final int STAPR = 9;

    /**
     *  Set ap element (service request).
     */

     public static final int STAPE = 10;

    /**
     *  Set canvas (service request).
     */

     public static final int STCVS = 11;

    /**
     *  Set position (service request).
     */

     public static final int STPOS = 12;

    /**
     *  Set room paitings (service request).
     */

     public static final int STRMP = 13;

    /**
     *  Set room distances (service request).
     */

     public static final int STRD = 14;

    /**
     *  Set robbed paitings (service request).
     */

     public static final int STRBP = 15;

    /**
     *  Setting acknowledged (reply).
     */

    public static final int SACK = 16;

    //entities functions

    /**
     *  Request start operation (service request).
     */

    public static final int  SO = 17;

    /**
     *  Operation started started (reply).
     */

    public static final int  SODONE = 18;

    /**
     *  Request get room index (service request).
     */

    public static final int  GRI = 19;

    /**
     *  Get room index (reply).
     */

    public static final int  GRIDONE = 20;

    /**
     *  Request appraise situation (service request).
     */

    public static final int AS = 21;

    /**
     *  Appraise situation (reply).
     */

    public static final int ASDONE = 22;

    /**
     *  Request get assult party (service request).
     */

     public static final int GAP = 23;

     /**
      *  get assult party (reply).
      */
 
     public static final int GAPDONE = 24;

    /**
     *  Request prepare assult party (service request).
     */

    public static final int PAP = 25;

    /**
     *  Prepare assult party (reply).
     */

    public static final int PAPDONE = 26;

    /**
     *  Request Send assault party (service request).
     */

    public static final int SAP = 27;

    /**
     *  Send assault party (reply).
     */

    public static final int SAPDONE = 28;

    /**
     *  Request get room at concentration site (service request).
     */

    public static final int GRCS = 29;

    /**
     *  get room at concentration site done (reply).
     */

    public static final int GRCSDONE = 30;

    /**
     *  Request take a rest (service request).
     */

    public static final int TAR = 31;

    /**
     *  take a rest (reply).
     */

    public static final int TARDONE = 32;

    /**
     *  Request collect a canvas (service request).
     */

    public static final int CAC = 33;

    /**
     *  collect a canvas done (reply).
     */

    public static final int CACDONE = 34;

    /**
     *  Request sum up the results (service request).
     */

    public static final int SUTR = 35;

    /**
     *  sum up the results done (reply).
     */

    public static final int SUTRDONE = 36;

    /**
     *  Request am I needed (service request).
     */

    public static final int AIN = 37;

    /**
     *  am I needed done (reply).
     */

    public static final int AINDONE = 38;

    /**
     *  Request prepare excursion (service request).
     */

    public static final int PE = 39;

    /**
     *  prepare excursion done (reply).
     */

    public static final int PEDONE = 40;

    /**
     *  Request assign member (service request).
     */

    public static final int AM = 41;

    /**
     * assign member done (reply).
     */
    public static final int AMDONE = 42;

    /**
     *  Request crawl in (service request).
     */

    public static final int CI = 43;

    /**
     *  crawl in done (reply).
     */

    public static final int CIDONE = 44;

    /**
     *  Request get room at assault party (service request).
     */

     public static final int GRAP = 45;

     /**
      *  get room at assault party done (reply).
      */
 
     public static final int GRAPDONE = 46;

    /**
     *  Request roll a canvas (service request).
     */

    public static final int RAC = 47;

    /**
     *  roll a canvas done (reply).
     */

    public static final int RACDONE = 48;

    /**
     *  Request reverse direction (service request).
     */

    public static final int RD = 49;

    /**
     * reverse direction done (reply).
     */

    public static final int RDDONE = 50;

    /**
     *  Request crawl out (service request).
     */

    public static final int CO = 51;

    /**
     *  crawl out done (reply).
     */

    public static final int CODONE = 52;

    /**
     *  Request hand a canvas (service request).
     */

    public static final int HAC = 53;

    /**
     * hand a canvas done (reply).
     */

    public static final int HACDONE = 54;



}

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
     *  End of work - master (service request).
     */

     public static final int ENDOP = 5;

     /**
      *  master goes home (reply).
      */
 
     public static final int EOPDONE = 6;


    /**
     *  Set master state (service request).
     */

    public static final int STMST = 7;

    /**
     *  Set ordinary state (service request).
     */

    public static final int STOST = 8;

    /**
     *  Setting acknowledged (reply).
     */

    public static final int SACK = 9;








    /**
     *  Request start operation (service request).
     */

    public static final int  SO = 10;

    /**
     *  Operation started started (reply).
     */

    public static final int  SODONE = 11;

    /**
     *  Request get room index (service request).
     */

    public static final int  GRI = 12;

    /**
     *  Get room index (reply).
     */

    public static final int  GRIDONE = 13;

    /**
     *  Request appraise situation (service request).
     */

    public static final int AS = 14;

    /**
     *  Appraise situation (reply).
     */

    public static final int ASDONE = 15;

    /**
     *  Request get assult party (service request).
     */

     public static final int GAP = 16;

     /**
      *  get assult party (reply).
      */
 
     public static final int GAPDONE = 17;

    /**
     *  Request prepare assult party (service request).
     */

    public static final int PAP = 18;

    /**
     *  Prepare assult party (reply).
     */

    public static final int PAPDONE = 19;

    /**
     *  Request Send assault party (service request).
     */

    public static final int SAP = 20;

    /**
     *  Send assault party (reply).
     */

    public static final int SAPDONE = 21;

    /**
     *  Request get room at concentration site (service request).
     */

    public static final int GRCS = 22;

    /**
     *  get room at concentration site done (reply).
     */

    public static final int GRCSDONE = 23;

    /**
     *  Request take a rest (service request).
     */

    public static final int TAR = 24;

    /**
     *  take a rest (reply).
     */

    public static final int TARDONE = 25;

    /**
     *  Request collect a canvas (service request).
     */

    public static final int CAC = 26;

    /**
     *  collect a canvas done (reply).
     */

    public static final int CACDONE = 27;

    /**
     *  Request sum up the results (service request).
     */

    public static final int SUTR = 28;

    /**
     *  sum up the results done (reply).
     */

    public static final int SUTRDONE = 29;

    /**
     *  Request am I needed (service request).
     */

    public static final int AIN = 30;

    /**
     *  am I needed done (reply).
     */

    public static final int AINDONE = 31;

    /**
     *  Request prepare excursion (service request).
     */

    public static final int PE = 32;

    /**
     *  prepare excursion done (reply).
     */

    public static final int PEDONE = 33;

    /**
     *  Request assign member (service request).
     */

    public static final int AM = 34;

    /**
     * assign member done (reply).
     */
    public static final int AMDONE = 35;

    /**
     *  Request crawl in (service request).
     */

    public static final int CI = 36;

    /**
     *  crawl in done (reply).
     */

    public static final int CIDONE = 37;

    /**
     *  Request get room at assault party (service request).
     */

     public static final int GRAP = 38;

     /**
      *  get room at assault party done (reply).
      */
 
     public static final int GRAPDONE = 39;

    /**
     *  Request roll a canvas (service request).
     */

    public static final int RAC = 40;

    /**
     *  roll a canvas done (reply).
     */

    public static final int RACDONE = 41;

    /**
     *  Request reverse direction (service request).
     */

    public static final int RD = 42;

    /**
     * reverse direction done (reply).
     */

    public static final int RDDONE = 43;

    /**
     *  Request crawl out (service request).
     */

    public static final int CO = 44;

    /**
     *  crawl out done (reply).
     */

    public static final int CODONE = 45;

    /**
     *  Request hand a canvas (service request).
     */

    public static final int HAC = 46;

    /**
     * hand a canvas done (reply).
     */

    public static final int HACDONE = 48;

    

}

package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import clientSide.stubs.*;
import commInfra.*;
import genclass.GenericIO;
import genclass.TextFile;
import java.util.Objects;


/**
 * General Repository
 *
 * It is responsible to keep the visible internal state of the problem and to
 * provide means for it
 * to be printed in the logging file.
 * It is implemented as an implicit monitor.
 * All public methods are executed in mutual exclusion.
 * There are no internal synchronisation points.
 */

public class GeneralRepos {
    
    /**
     * Name of the logging file.
     */

     private final String logFileName;

     /**
      * State of the Master.
      */
 
     private int masterState;
 
     /**
      * State of the ordinaries.
      */
 
     private int[] ordinaryState;
 
     /**
      * Situation of the ordinaries.
      */
     private char[] ordinarySituation;
 
     /**
      * Maximum displacement of the ordinaries.
      */
     private int[] MDs;
 
     /**
      * Assault party elements
      */
     private int[][] elements;
 
     /**
      * Assault party room
      */
     private int[] apRoom;
 
     /**
      * Rooms number of paintings hanging on the walls
      */
     private int[] paintings;
 
     /**
      * Rooms distance from outside gathering site
      */
     private int[] distances;
 
     /**
      * Number of robbed paintings
      */
     private int robbed;

    /**
     *   Number of entity groups requesting the shutdown.
     */
   private int nEntities;
 
     /**
      * Instantiation of a general repository object.
      */
 
     public GeneralRepos() {
        logFileName = "logger";
 
         // master thieve
         masterState = MasterStates.PLANNING_THE_HEIST;
 
         // ordinarie thives
         ordinaryState = new int[SimulConsts.M - 1];
         ordinarySituation = new char[SimulConsts.M - 1];
         MDs = new int[SimulConsts.M - 1];
         for (int i = 0; i < SimulConsts.M - 1; i++) {
             ordinaryState[i] = OrdinaryStates.CONCENTRATION_SITE;
             ordinarySituation[i] = 'W';
             MDs[i] = 2 + (int) (Math.random() * (SimulConsts.MD - 2) + 1);
         }
 
         // museum
         paintings = new int[SimulConsts.N];
         distances = new int[SimulConsts.N];
         for(int i=0; i<SimulConsts.N; i++)
            distances[i] = SimulConsts.d +(int)(Math.random()* (SimulConsts.D-SimulConsts.d)+1);
 
         // assault parties
         apRoom = new int[2];
         apRoom[0] = -1;
         apRoom[1] = -1;
 
         elements = new int[SimulConsts.M - 1][3];
         for (int i = 0; i < SimulConsts.M - 1; i++) {
             elements[i][0] = -1;
             elements[i][0] = 0;
             elements[i][0] = 0;
         }
 
         // robbed paintings
         robbed = 0;
 
         nEntities = 0;
         reportInitialStatus();
     }
 

    /**
   *   Operation server shutdown.
   *
   *   New operation.
   */

   public synchronized void shutdown () {
       nEntities += 1;
       if (nEntities >= SimulConsts.M)
          ServerGeneralRepos.waitConnection = false;
   }



     /**
      * Set master state.
      *
      * @param state master state
      */
 
     public synchronized void setMasterState(int state) {
         masterState = state;
         reportStatus();
     }
 
     /**
      * Set ordinary state.
      *
      * @param id    ordinary id
      * @param state ordinary state
      */
 
     public synchronized void setOrdinaryState(int id, int state) {
         ordinaryState[id] = state;
         reportStatus();
     }
 
     /**
      * Set ordinary situation
      */
     public synchronized void setOrdinarySituation(int id, char sit) {
         ordinarySituation[id] = sit;
         reportStatus();
     }
 
     /**
      * Get ordinary thieves maximum distances
      * 
      * @param id of the ordinary thieve
      * @return md of the ordinary thieve
      */
     public synchronized int getOrdinaryMD(int id) {
        return MDs[id];
     }
 
     /**
      * 
      * @param ap assault party
      * @param room room to heist
      */
     public synchronized void setApRoom(int ap, int room){
         apRoom[ap] = room;
     }
 
     /**
      * Set Assault Party element
      * 
      * @param elem index (= ap*SimulConsts.E+memberId)
      * @param tid  ordinary thieve id
      */
     public synchronized void setApElement(int elem, int tid) {
         elements[elem][0] = tid;
         reportStatus();
     }
 
     /**
      * Update carrying a canvas
      * 
      * @param elem   index
      * @param canvas carry
      */
     public synchronized void setCanvas(int elem, int canvas) {
         elements[elem][2] = canvas;
         reportStatus();
     }
 
     /**
      * Update thieve position
      * 
      * @param elem index
      * @param pos  actual position of the thieve in line
      */
     public synchronized void setPosition(int elem, int pos) {
         elements[elem][1] = pos;
         reportStatus();
     }
 
     /**
      * Set number of paitings on the walls
      * 
      * @param paitings on each room
      */
     public synchronized void setRoomPaitings(int[] paitings) {
         this.paintings = paitings;
         reportStatus();
     }
 
     /**
      * Get distance of the rooms
      * 
      * @return distances of each room
      */
     public synchronized int[] getRoomDistances() {
        return distances;
     }
 
     /**
      * Set robbed paintings
      */
     public synchronized void setRobbedPaintings() {
         this.robbed++;
     }
 
     /**
      * Write the header to the logging file.
      *
      * The Ordinaries are sleeping and the master are decidingheist next operation.
      * Internal operation.
      */
 
     private void reportInitialStatus() {
         TextFile log = new TextFile(); // instantiation of a text file handler
 
         if (!log.openForWriting(".", logFileName)) {
             GenericIO.writelnString("The operation of creating the file " + logFileName + " failed!");
             System.exit(1);
         }
 
         log.writelnString("                           Heist to the Museum - Description of the internal state\n");
         log.writelnString("  Mstr   Thief 1     Thief 2     Thief 3     Thief 4     Thief 5     Thief 6");
         log.writelnString("Stat    Stat S MD   Stat S MD   Stat S MD   Stat S MD   Stat S MD   Stat S MD");
 
         log.writelnString(
                 "                Assault party 1                         Assault party 2                             Museum");
         log.writelnString(
                 "      Elem 1      Elem 2      Elem 3          Elem 1      Elem 2      Elem 3    Room 1   Room 2   Room 3   Room 4   Room 5");
         log.writelnString(
                 "RId Id Pos Cv   Id Pos Cv   Id Pos Cv   RId Id Pos Cv   Id Pos Cv   Id Pos Cv   NP DT    NP DT    NP DT    NP DT    NP DT");
 
         if (!log.close()) {
             GenericIO.writelnString("The operation of closing the file " + logFileName + " failed!");
             System.exit(1);
         }
         reportStatus();
     }
 
     /**
      * Write the footer to the logging file.
      */
 
     public void reportFinalStatus() {
         TextFile log = new TextFile();
 
         if (!log.openForAppending(".", logFileName)) {
             GenericIO.writelnString("The operation of opening for appending the file " + logFileName + " failed!");
             System.exit(1);
         }
 
         log.writelnString("My friends, tonight's effort produced " + robbed + " priceless paintings!");
 
         if (!log.close()) {
             GenericIO.writelnString("The operation of closing the file " + logFileName + " failed!");
             System.exit(1);
         }
     }
 
     /**
      * Write a state line at the end of the logging file.
      *
      * The current state of entities is organized in a line to be printed.
      */
 
     private void reportStatus() {
         TextFile log = new TextFile(); // instantiation of a text file handler
         String lineStatus = "", line2Status = ""; // state line to be printed
 
         if (!log.openForAppending(".", logFileName)) {
             GenericIO.writelnString("The operation of opening for appending the file " + logFileName + " failed!");
             System.exit(1);
         }
 
         switch (masterState) {
             case MasterStates.PLANNING_THE_HEIST:
                 lineStatus += "PTH  ";
                 break;
             case MasterStates.DECIDING_WHAT_TO_DO:
                 lineStatus += "DWTD ";
                 break;
             case MasterStates.ASSEMBLING_A_GROUP:
                 lineStatus += "AAG  ";
                 break;
             case MasterStates.WAITING_FOR_GROUP_ARRIVAL:
                 lineStatus += "WFGA ";
                 break;
             case MasterStates.PRESENTING_THE_REPORT:
                 lineStatus += "PTR  ";
                 break;
         }
 
         for (int i = 0; i < SimulConsts.M - 1; i++)
             switch (ordinaryState[i]) {
                 case OrdinaryStates.CONCENTRATION_SITE:
                     lineStatus += "   CNS  " + ordinarySituation[i] + "  " + MDs[i];
                     break;
                 case OrdinaryStates.CRAWLING_INWARDS:
                     lineStatus += "   CIN  " + ordinarySituation[i] + "  " + MDs[i];
                     break;
                 case OrdinaryStates.AT_A_ROOM:
                     lineStatus += "   AAR  " + ordinarySituation[i] + "  " + MDs[i];
                     break;
                 case OrdinaryStates.CRAWLING_OUTWARDS:
                     lineStatus += "   COUT  " + ordinarySituation[i] + "  " + MDs[i];
                     break;
                 case OrdinaryStates.COLLECTION_SITE:
                     lineStatus += "   CLS " + ordinarySituation[i] + " " + MDs[i];
                     break;
             }
 
         log.writelnString(lineStatus); // states of thieves
         for (int i = 0; i < SimulConsts.M - 1; i++){
             if(i% SimulConsts.E==0) line2Status += String.format("%2d> ", apRoom[i/SimulConsts.E]);
             line2Status += String.format("%2d  %2d %2d | ", elements[i][0], elements[i][1], elements[i][2]);
         }
 
         for (int i = 0; i < SimulConsts.N; i++)
             line2Status += String.format("%2d %2d    ", paintings[i], distances[i]);
         log.writelnString(line2Status + '\n'); // status of shared regions
 
         if (!log.close()) {
             GenericIO.writelnString("The operation of closing the file " + logFileName + " failed!");
             System.exit(1);
         }
     }

}

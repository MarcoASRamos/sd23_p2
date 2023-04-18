package clientSide.main;

import clientSide.entities.*;
import clientSide.stubs.*;
import serverSide.main.*;
import commInfra.*;
import genclass.GenericIO;

/**
 *    Client side of the Sleeping Barbers (customers).
 *
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */
public class ClientMuseumHeistOrdinary {
    /**
     *  Main method.
     *
     *    @param args runtime arguments
     *        args[0] - name of the platform where is located the bar server
     *        args[1] - port nunber for listening to service requests
     *        args[2] - name of the platform where is located the kitchen server
     *        args[3] - port nunber for listening to service requests
     *        args[4] - name of the platform where is located the general repository server
     *        args[5] - port nunber for listening to service requests
     *        args[6] - name of the platform where is located the bar server
     *        args[7] - port nunber for listening to service requests
     *        args[8] - name of the platform where is located the kitchen server
     *        args[9] - port nunber for listening to service requests
     *        args[10] - name of the platform where is located the general repository server
     *        args[11] - port nunber for listening to service requests
     */

     public static void main (String [] args)
     {
         String[] apServerHostName = new String[2];                     // name of the platform where is located the bar server
         int[] apServerPortNumb = new int[2];                           // port number for listening to service requests

         String csServerHostName;                                       // name of the platform where is located the kitchen server
         int csServerPortNumb = -1;                                     // port number for listening to service requests

         String ccsServerHostName;                                      // name of the platform where is located the bar server
         int ccsServerPortNumb = -1;                                    // port number for listening to service requests

         String museumServerHostName;                                   // name of the platform where is located the kitchen server
         int museumServerPortNumb = -1;                                 // port number for listening to service requests

         String genReposServerHostName;                                 // name of the platform where is located the general repository server
         int genReposServerPortNumb = -1;                               // port number for listening to service requests


         AssaultPartyStub[] apStub = new AssaultPartyStub[2];           // remote reference to the assault parties
         MuseumStub museumStub;                                         // remote reference to the museum
         ConcentrationSiteStub csStub;                                  // remote reference to the concentration site
         ControlCollectionSiteStub ccsStub;                             // remote reference to the control collection site
         GeneralReposStub genReposStub;                                 // remote reference to the general repository
 
 
         /* getting problem runtime parameters */
 
         if (args.length != 10)
         { GenericIO.writelnString ("Wrong number of parameters!");
             System.exit (1);
         }



         apServerHostName[0] = args[0];
         try
         { apServerPortNumb[0] = Integer.parseInt (args[1]);
         }
         catch (NumberFormatException e)
         { GenericIO.writelnString ("args[1] is not a number!");
             System.exit (1);
         }
         if ((apServerPortNumb[0] < 4000) || (apServerPortNumb[0] >= 65536))
         { GenericIO.writelnString ("args[1] is not a valid port number!");
             System.exit (1);
         }



         apServerHostName[1] = args[2];
         try
         { apServerPortNumb[1] = Integer.parseInt (args[3]);
         }
         catch (NumberFormatException e)
         { GenericIO.writelnString ("args[3] is not a number!");
             System.exit (1);
         }
         if ((apServerPortNumb[1] < 4000) || (apServerPortNumb[1] >= 65536))
         { GenericIO.writelnString ("args[3] is not a valid port number!");
             System.exit (1);
         }



         csServerHostName = args[4];
         try
         { csServerPortNumb = Integer.parseInt (args[5]);
         }
         catch (NumberFormatException e)
         { GenericIO.writelnString ("args[5] is not a number!");
             System.exit (1);
         }
         if ((csServerPortNumb < 4000) || (csServerPortNumb >= 65536))
         { GenericIO.writelnString ("args[5] is not a valid port number!");
             System.exit (1);
         }



         ccsServerHostName = args[6];
         try
         { ccsServerPortNumb = Integer.parseInt (args[7]);
         }
         catch (NumberFormatException e)
         { GenericIO.writelnString ("args[7] is not a number!");
             System.exit (1);
         }
         if ((ccsServerPortNumb < 4000) || (ccsServerPortNumb >= 65536))
         { GenericIO.writelnString ("args[7] is not a valid port number!");
             System.exit (1);
         }



         museumServerHostName = args[8];
         try
         { museumServerPortNumb = Integer.parseInt (args[9]);
         }
         catch (NumberFormatException e)
         { GenericIO.writelnString ("args[9] is not a number!");
             System.exit (1);
         }
         if ((museumServerPortNumb < 4000) || (museumServerPortNumb >= 65536))
         { GenericIO.writelnString ("args[9] is not a valid port number!");
             System.exit (1);
         }



         genReposServerHostName = args[10];
         try
         { genReposServerPortNumb = Integer.parseInt (args[11]);
         }
         catch (NumberFormatException e)
         { GenericIO.writelnString ("args[11] is not a number!");
             System.exit (1);
         }
         if ((genReposServerPortNumb < 4000) || (genReposServerPortNumb >= 65536))
         { GenericIO.writelnString ("args[11] is not a valid port number!");
             System.exit (1);
         }
 
 
         /* problem initialization */
         apStub[0] = new AssaultPartyStub (apServerHostName[0], apServerPortNumb[0]);
         apStub[1] = new AssaultPartyStub (apServerHostName[1], apServerPortNumb[1]);
         csStub = new ConcentrationSiteStub (csServerHostName, csServerPortNumb);
         ccsStub = new ControlCollectionSiteStub (ccsServerHostName, ccsServerPortNumb);
         museumStub = new MuseumStub (museumServerHostName, museumServerPortNumb);


         genReposStub = new GeneralReposStub (genReposServerHostName, genReposServerPortNumb);
         Ordinary ord = new Ordinary ("ordinary", , , );
 
         /* start of the simulation */
 
         ord.start ();
 
         /* waiting for the end of the simulation */
 
         GenericIO.writelnString ();
 
         try {
             ord.join();
         } catch (InterruptedException e) {}
         GenericIO.writelnString ("The chef has terminated.");
 
         GenericIO.writelnString ();
         apStub[0].shutdown ();
         apStub[1].shutdown ();
         csStub.shutdown ();
         ccsStub.shutdown ();
         museumStub.shutdown ();
         genReposStub.shutdown ();
     }
 }



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
public class ClientMuseumHeistMaster {
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
     */

     public static void main (String [] args)
     {
         String barServerHostName;                                       // name of the platform where is located the bar server
         int barServerPortNumb = -1;                                     // port number for listening to service requests
         String kitServerHostName;                                       // name of the platform where is located the kitchen server
         int kitServerPortNumb = -1;                                     // port number for listening to service requests
         String genReposServerHostName;                                  // name of the platform where is located the general repository server
         int genReposServerPortNumb = -1;                                // port number for listening to service requests
         BarStub barStub;
         KitchenStub kitStub;
         GeneralReposStub genReposStub;                                  // remote reference to the general repository
 
 
         /* getting problem runtime parameters */
 
         if (args.length != 6)
         { GenericIO.writelnString ("Wrong number of parameters!");
             System.exit (1);
         }
         barServerHostName = args[0];
         try
         { barServerPortNumb = Integer.parseInt (args[1]);
         }
         catch (NumberFormatException e)
         { GenericIO.writelnString ("args[1] is not a number!");
             System.exit (1);
         }
         if ((barServerPortNumb < 4000) || (barServerPortNumb >= 65536))
         { GenericIO.writelnString ("args[1] is not a valid port number!");
             System.exit (1);
         }
         kitServerHostName = args[2];
         try
         { kitServerPortNumb = Integer.parseInt (args[3]);
         }
         catch (NumberFormatException e)
         { GenericIO.writelnString ("args[1] is not a number!");
             System.exit (1);
         }
         if ((kitServerPortNumb < 4000) || (kitServerPortNumb >= 65536))
         { GenericIO.writelnString ("args[1] is not a valid port number!");
             System.exit (1);
         }
         genReposServerHostName = args[4];
         try
         { genReposServerPortNumb = Integer.parseInt (args[5]);
         }
         catch (NumberFormatException e)
         { GenericIO.writelnString ("args[3] is not a number!");
             System.exit (1);
         }
         if ((genReposServerPortNumb < 4000) || (genReposServerPortNumb >= 65536))
         { GenericIO.writelnString ("args[3] is not a valid port number!");
             System.exit (1);
         }
 
 
         /* problem initialization */
 
         barStub = new BarStub (barServerHostName, barServerPortNumb);
         kitStub = new KitchenStub (kitServerHostName, kitServerPortNumb);
         genReposStub = new GeneralReposStub (genReposServerHostName, genReposServerPortNumb);
         Chef chef = new Chef ("chef", 0, barStub, kitStub);
 
         /* start of the simulation */
 
         chef.start ();
 
         /* waiting for the end of the simulation */
 
         GenericIO.writelnString ();
 
         try {
             chef.join();
         } catch (InterruptedException e) {}
         GenericIO.writelnString ("The chef has terminated.");
 
         GenericIO.writelnString ();
         barStub.shutdown ();
         kitStub.shutdown ();
         genReposStub.shutdown ();
     }
}

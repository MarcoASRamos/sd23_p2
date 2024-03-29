package serverSide.main;

import serverSide.entities.*;
import serverSide.sharedRegions.*;
import clientSide.stubs.*;
import commInfra.*;
import genclass.GenericIO;
import java.net.*;

/**
 * Server side of the Control and Collection site of Information.
 *
 * Implementation of a client-server model of type 2 (server replication).
 * Communication is based on a communication channel under the TCP protocol.
 */

public class ServerControlCollectionSite {
    /**
     * Flag signaling the service is active.
     */

    public static boolean waitConnection;

    /**
     * Main method.
     *
     * @param args runtime arguments
     *             args[0] - port nunber for listening to service requests
     *             args[1] - name of the platform where is located the server for
     *             the general repository
     *             args[2] - port nunber where the server for the general repository
     *             is listening to service requests
     */

    public static void main(String[] args) {
        ControlCollectionSite ccs; // barber shop (service to be rendered)
        ControlCollectionSiteInterface ccsInter; // interface to the barber shop
        GeneralReposStub reposStub; // stub to the general repository
        ServerCom scon, sconi; // communication channels
        int portNumb = -1; // port number for listening to service requests
        String reposServerName; // name of the platform where is located the server for the general repository
        int reposPortNumb = -1; // port nunber where the server for the general repository is listening to
                                // service requests

        if (args.length != 3) {
            GenericIO.writelnString("Wrong number of parameters!");
            System.exit(1);
        }
        try {
            portNumb = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            GenericIO.writelnString("args[0] is not a number!");
            System.exit(1);
        }
        if ((portNumb < 4000) || (portNumb >= 65536)) {
            GenericIO.writelnString("args[0] is not a valid port number!");
            System.exit(1);
        }
        reposServerName = args[1];
        try {
            reposPortNumb = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            GenericIO.writelnString("args[2] is not a number!");
            System.exit(1);
        }
        if ((reposPortNumb < 4000) || (reposPortNumb >= 65536)) {
            GenericIO.writelnString("args[2] is not a valid port number!");
            System.exit(1);
        }

        /* service is established */

        reposStub = new GeneralReposStub(reposServerName, reposPortNumb); // communication to the general repository is
                                                                          // instantiated
        ccs = new ControlCollectionSite(reposStub); // service is instantiated
        ccsInter = new ControlCollectionSiteInterface(ccs); // interface to the service is instantiated
        scon = new ServerCom(portNumb); // listening channel at the public port is established
        scon.start();
        GenericIO.writelnString("Service is established!");
        GenericIO.writelnString("Server is listening for service requests.");

        /* service request processing */

        ControlCollectionSiteClientProxy cliProxy; // service provider agent

        waitConnection = true;
        while (waitConnection) {
            try {
                sconi = scon.accept(); // enter listening procedure
                cliProxy = new ControlCollectionSiteClientProxy(sconi, ccsInter); // start a service provider agent to
                // address
                cliProxy.start(); // the request of service
            } catch (SocketTimeoutException e) {
            }
        }
        scon.end(); // operations termination
        GenericIO.writelnString("Server was shutdown.");
    }
}
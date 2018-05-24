package wiremock_POC_Try;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.standalone.CommandLineOptions;


public class serverRunner {
    static stubbing stub =new stubbing();
    public static void main (final String[] args){
        final String[] args2 = new String[args.length + 4];
        args2[args.length] = "--port";
        args2[args.length + 1] = "8083";
        args2[args.length + 2] = "--extensions";
        args2[args.length + 3] = "wiremock_POC_Try.wiremock_ext";
        final CommandLineOptions options = new CommandLineOptions(args2);
        WireMockServer wireMockServer = new WireMockServer(options);
        wireMockServer.start();
        stub.loadStubs(wireMockServer);
    }
}



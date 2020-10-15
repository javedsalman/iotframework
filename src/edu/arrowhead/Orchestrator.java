package edu.arrowhead;

import java.io.IOException;

public class Orchestrator extends ASystem {


    static String serviceToOrchestrate = "randomNumberGenerator";

    static ASystem orc;
    static Service lookupservice;

    public Orchestrator(String ipAddress, Integer ipPort, String serviceName, String role) throws IOException {
        super(ipAddress, ipPort, serviceName, role);
    }


    public static void main(String[] args) {
        try {


            orc = new Orchestrator("localhost", 8440, "LookUP", "S");
            lookupservice = new LookUpService(orc, "LookUP", "/LookUP");

            System.out.println("Orchestrator Started and listening 8440");

            while (true) {
                orc.connect();


                orc.payload = orc.receivePL();
                System.out.println("Orchestration Request " +  orc.payload + " received from Random Numbner Displayer for Random Number Provider Address ");
                try {

                    lookupservice.transceive(serviceToOrchestrate);
                    if (orc.payload.equals(serviceToOrchestrate)) {
                        String serverResponse = lookupservice.transceive();
                        orc.payload = serverResponse + " \n";




                    } else {

                        throw new Exception();

                    }


                } catch (Exception e) {
                    orc.payload = "INVALID SERVICE REQUEST \n";
                }


                orc.sendPL(orc.payload);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                orc.disconnect();
            } catch (Exception e) {
            }
        }
    }
}
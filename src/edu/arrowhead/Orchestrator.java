package edu.arrowhead;

import java.io.IOException;

public class Orchestrator extends ASystem {


    static String serviceToOrchestrate = "randomNumberGenerator";

    static ASystem orc;

    public Orchestrator(String ipAddress, Integer ipPort, String serviceName, String role) throws IOException {
        super(ipAddress, ipPort, serviceName, role);
    }


    public static void main(String[] args) {
        try {


            orc = new Orchestrator("localhost", 8440, "LookUP", "S");

            System.out.println("Orchestrator Started and listening 8440");

            while (true) {
                orc.connect();


                orc.service_Request = orc.receivePL();
                System.out.println("Orchestration Request " +  orc.service_Request + " received from Random Numbner Displayer for Random Number Provider Address ");
                try {

                    orc.service.transceive(serviceToOrchestrate);
                    if (orc.service_Request.equals(serviceToOrchestrate)) {
                        String serverResponse = orc.service.transceive();
                        orc.service_Response = serverResponse + " \n";




                    } else {

                        throw new Exception();

                    }


                } catch (Exception e) {
                    orc.service_Response = "INVALID SERVICE REQUEST \n";
                }


                orc.sendPL(orc.service_Response);
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
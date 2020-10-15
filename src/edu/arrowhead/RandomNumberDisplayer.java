package edu.arrowhead;

import java.io.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class RandomNumberDisplayer extends ASystem{



    private static String  orchestrator_Service_Request = "randomNumberGenerator";
    private static String provider_Service_Request = "Random Number Request";
    private static String provider_Service_Response;
 //   private static Boolean isProviderTimeOut;
    static ASystem con;
    static Service lookupservice;
    static Service displayservice;





    public RandomNumberDisplayer(String ipAddress, Integer ipPort, String serviceName, String role) throws IOException {
        super(ipAddress, ipPort, serviceName, role);
    }


    public static void main(String [] args) throws IOException {



        try {

            con = new RandomNumberDisplayer("localhost", 8480, "Display", "C");
            lookupservice = new LookUpService(con, "LookUP", "/LookUP");


            String delimiter = ":";

            String service_Name_Orchestration = "Orchestrator";
            lookupservice.transceive(service_Name_Orchestration);
            String serverResponse = lookupservice.transceive();

            String[] tokensVal1 = serverResponse.split(delimiter);
            con.remoteIP =   (tokensVal1[0]).trim();
            String orchestrator_PortNumber_Temp =  (tokensVal1[1]).trim();
            con.remotePort  = Integer.parseInt(orchestrator_PortNumber_Temp);
            con.connect(con.remoteIP,con.remotePort);
            orchestrator_Service_Request = "randomNumberGenerator";
            con.sendPL(orchestrator_Service_Request);
            String orchestrator_Service_Response = con.receivePL();
            System.out.println("Received from Orchestrator : the Random Number with TimeStamp Provider address is " + orchestrator_Service_Response);
            con.disconnect();


            String[] tokensVal2 = orchestrator_Service_Response.split(delimiter);

           String provider_HostName = (tokensVal2[0]).trim();
            if (tokensVal2.length>1) {
                String provider_PortNumber_Temp = (tokensVal2[1]).trim();
               int provider_PortNumber = Integer.parseInt(provider_PortNumber_Temp);
                provider_Service_Request = "Random Number with Time Stamp";

                Timer timer = new Timer();

                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {

                        con.remoteIP = provider_HostName;
                        con.remotePort = provider_PortNumber;
                        con.connect(con.remoteIP, con.remotePort);
                        con.sendPL(provider_Service_Request);
                        provider_Service_Response = con.receivePL();
                        try {
                            displayservice = new DisplayService(con, "Display", "/Display");

                            displayservice.transceive(provider_Service_Response);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        con.disconnect();

                        if (provider_Service_Response.equals("INVALID SERVICE REQUEST to Provider")){

                            System.exit(1);
                        }
                    }

                }, new Date(), 1000);


            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}



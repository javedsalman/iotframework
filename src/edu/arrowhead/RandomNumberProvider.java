package edu.arrowhead;


import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class RandomNumberProvider extends ASystem {


    private static String serviceToProvide = "Random Number with Time Stamp";

    static ASystem pro;




    public RandomNumberProvider(String ipAddress, Integer ipPort, String serviceName, String role) throws IOException {
        super(ipAddress, ipPort, serviceName, role);

    }


    public static void main(String[] args) {
        try {


            pro = new RandomNumberProvider("localhost", 8460, "RN", "S");

            System.out.println("Provider Started and listening to the port 8460");



            while (true) {
                pro.connect();


                pro.service_Request = pro.receivePL();
                System.out.println("Message received from Consumer = " + pro.service_Request );


                try {

                    pro.service.transceive("1:100");
                    String s = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

                    String serverResponse = "Number: " + pro.service.transceive() + "   TimeStamp: ";
                    serverResponse += s;
                    System.out.println("Message Sent to Consumer = " + serverResponse );


                    if (pro.service_Request.equals(serviceToProvide)) {

                        pro.service_Response = serverResponse + " \n";

                    } else if (!(pro.service_Request.equals(serviceToProvide))) {

                        throw new Exception();

                    }


                } catch (Exception e) {
                    pro.service_Response = "INVALID SERVICE REQUEST to Provider\n";
                }


                pro.sendPL(pro.service_Response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pro.disconnect();
            } catch (Exception e) {
            }
        }
    }


}
package edu.arrowhead;


import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class RandomNumberProvider extends ASystem {


    private static String serviceToProvide = "Random Number with Time Stamp";

    static ASystem pro;
    static Service rnservice;




    public RandomNumberProvider(String ipAddress, Integer ipPort, String serviceName, String role) throws IOException {
        super(ipAddress, ipPort, serviceName, role);

    }


    public static void main(String[] args) {
        try {


            pro = new RandomNumberProvider("localhost", 8460, "RN", "S");
            rnservice = new RNService(pro, "RN", "/RN");

            System.out.println("Provider Started and listening to the port 8460");



            while (true) {
                pro.connect();


                pro.payload = pro.receivePL();
                System.out.println("Message received from Consumer = " + pro.payload );


                try {

                    rnservice.transceive("1:100");
                    String s = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

                    String serverResponse = "Number: " + rnservice.transceive() + "   TimeStamp: ";
                    serverResponse += s;
                    System.out.println("Message Sent to Consumer = " + serverResponse );


                    if (pro.payload.equals(serviceToProvide)) {

                        pro.payload = serverResponse + " \n";

                    } else if (!(pro.payload.equals(serviceToProvide))) {

                        throw new Exception();

                    }


                } catch (Exception e) {
                    pro.payload = "INVALID SERVICE REQUEST to Provider\n";
                }


                pro.sendPL(pro.payload);
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
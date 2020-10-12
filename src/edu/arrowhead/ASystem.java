package edu.arrowhead;
/**
 * @author javsal@ltu
 */

import java.io.*;

/**
 * This is the core ArrowHead/Abstract System which is responsible for all the inter and intra communication and trasceiving messages between assets, services, network and Systems
 */

public abstract class ASystem {

    /**
     * Local ip and port of the incumbent system
     */
    public  String ipAddress1;
    public String remoteIP;


    /**
     * In case of service consumer (Client) Role the remote IP and Port of the server connected to incumbent System
     */
    public int remotePort;
    public  int ipPort1;

    /**
     * The role of the System or ASystem (incumbent) performing, the role can be either ServerRole S (Providing the services) or ClientRole C (Consuming the services
     */
    public  String role1;
    /**
     * Service request and response from the network
     */

    public  String service_Response;
    public  String service_Request;
    /**
     * The list of services
     */
    Service rnservice;
    Service lookupservice;
    Service displayservice;
    Service service;
    /**
     * Network Interface to connect over the network with other Systems and send and receive payload
     */
    NetworkInterface networkinterface;

    /**
     * default Constructor
     * @throws IOException
     */
    public ASystem() throws IOException {

        networkinterface = new ServerRole(ipAddress1, ipPort1, role1);
    }

    /**
     * ASystem Constructor, it creates an network object either server or client based on the role of the System and also creates a service object based on service being provided or consumed by the incumbent system
     * @param ipAddress local IP Address
     * @param ipPort    local port
     * @param serviceName Name of the service incumbent either producing or consuming
     * @param role Role of the incumbent system either Provider (Server) or Consumer (Client)
     * @throws IOException
     */
    public ASystem(String ipAddress, Integer ipPort, String serviceName, String role) throws IOException {
        this.ipAddress1 = ipAddress;
        this.ipPort1 = ipPort;
        this.role1 = role;
        if (role.equals("S")) {
            networkinterface = new ServerRole(ipAddress1, ipPort1, role1);
        }else if (role.equals("C")) {
            networkinterface = new ClientRole(remoteIP, remotePort, role1);
        }

        rnservice = new RNService(this, "RN", "/RN");
        lookupservice = new LookUpService(this, "LookUP", "/LookUP");
        displayservice = new DisplayService(this, "Display", "/Display");

        if (serviceName.equals("RN")) {
            this.service = rnservice;

        } else if (serviceName.equals("LookUP")) {
            this.service = lookupservice;

        }

        else if (serviceName.equals("Display")) {
            this.service = displayservice;

        }


    }

    /**
     * send the payload over the network
     * @param payload payload sent over the network
     */
    public  void sendPL(String payload) {

        networkinterface.sendPL(payload);

    }

    /**
     * receive the payload from the network
     * @return payload received over the network
     */

    public  String receivePL() {

        service_Request= networkinterface.receivePL();

            return service_Request;


    }

    /**
     * connect as a server
     */
    public  void connect() {
        networkinterface.connect();


    }

    /**
     * connect as a client to the server with hostname and port
     * @param hostname remote server ip address
     * @param port remote server port
     */
    public  void connect(String hostname, int port) {
        networkinterface.connect(hostname,port);



    }

    /**
     * Disconnect from server or client role
     */
    public void disconnect (){

        networkinterface.disconnect();
    }



}

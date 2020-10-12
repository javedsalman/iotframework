package edu.arrowhead;

public interface NetworkInterface {


    void connect();
    void connect(String hostname, int port);
    void sendPL(String payload);
    String receivePL();
    void disconnect ();


}



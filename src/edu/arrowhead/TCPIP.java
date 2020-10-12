package edu.arrowhead;

public abstract class TCPIP implements NetworkInterface {



   public abstract  void connect();
    public abstract void connect(String hostname, int port);
    public abstract  void sendPL(String payload);
    public abstract  String receivePL();
    public abstract  void disconnect ();


}

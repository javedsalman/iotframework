package edu.arrowhead;



import java.io.*;
import java.net.*;;




public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }
  ServerRole serverrole;
    public void run() {
        try {
            serverrole.service_Request =serverrole.receivePL();

            //InputStream input = socket.getInputStream();
            //BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            serverrole.sendPL(serverrole.service_Request);
          /*  OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            do {
                ProtocolTcpIp.receivedPayload = reader.readLine();
                ProtocolTcpIp.sentPayload = new StringBuilder(ProtocolTcpIp.receivedPayload).reverse().toString();
                writer.println("Server: " + ProtocolTcpIp.sentPayload);
               // ProtocolTcpIp.printServerMsgs ();

            } while (!ProtocolTcpIp.receivedPayload.equals("bye"));
         */
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}


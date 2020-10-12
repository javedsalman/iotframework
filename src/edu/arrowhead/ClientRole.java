package edu.arrowhead;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
public class ClientRole extends TCPIP {

    private static Socket clientSocket;
    public static String service_Request;
    public String role;

    public  Socket getTcpipsocket() {
        return clientSocket;
    }

    public  void setTcpipsocket(Socket tcpipsocket) {
        ClientRole.clientSocket = tcpipsocket;
    }

    public ClientRole(String hostname, int port, String role) throws IOException {


    }

    public  void sendPL(String returnMessage) {
        try {
            OutputStream os = getTcpipsocket().getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            returnMessage = returnMessage + "\n";
            bw.write(returnMessage);
            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  String receivePL() {
        try {
            InputStream is = getTcpipsocket().getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String messageTemp = br.readLine();
            service_Request = messageTemp.trim();

            return service_Request;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public void connect() {

    }

    public  void connect(String hostname, int port){

        try
        {
            InetAddress address = InetAddress.getByName(hostname);
            clientSocket = new Socket(address, port);

            setTcpipsocket(clientSocket);

        }
        catch (Exception exception)
        {
            System.out.println("Unable to Connect to " + hostname+ ":" + port + ". ");
            exception.printStackTrace();
            System.exit(1);

        }
    }

    public void disconnect (){

        try
        {
            clientSocket.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

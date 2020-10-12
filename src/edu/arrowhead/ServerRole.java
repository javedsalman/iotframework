package edu.arrowhead;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerRole extends TCPIP {
    private static Socket tcpipsocket;
    public  ServerSocket serverSocket;
    public static String service_Request;




    public  Socket getTcpipsocket() {
        return tcpipsocket;
    }

    public  void setTcpipsocket(Socket tcpipsocket) {
        ServerRole.tcpipsocket = tcpipsocket;
    }

    public ServerRole(String hostname, int port, String role) throws IOException {

        this.serverSocket =  new ServerSocket(port);
        this.tcpipsocket = new Socket();

    }

    public  void sendPL(String returnMessage) {
        try {
            OutputStream os = getTcpipsocket().getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
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
            service_Request = br.readLine();

            return service_Request;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return service_Request;

    }

    public  void connect() {
        try {
            tcpipsocket = serverSocket.accept();
            setTcpipsocket(tcpipsocket);

        }
        catch(
                Exception e)

        {
            e.printStackTrace();

        }
    }

    @Override
    public void connect(String hostname, int port) {

    }

    public void disconnect (){

        try
        {
            tcpipsocket.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }






}

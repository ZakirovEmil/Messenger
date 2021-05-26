package client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ChatConnection {
    public Socket clientSocket;
    public BufferedReader in; // поток чтения из сокета
    public BufferedWriter out; // поток чтения в сокет
    public String ip; // ip адрес клиента
    public int port; // порт соединения

    public ChatConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        try {
            clientSocket = new Socket(ip, port);
            System.out.println("Socket connected");
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
//            new Client1.ReadMsg().start();
//            new Client1.WriteMsg().start();
        } catch (IOException e) {
            e.printStackTrace();
            downService();
        }
    }

    public void sendData(String str){
        try {
            System.out.println("Send:" + str);
            out.write(str);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasData(){
        try {
            return in.ready();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getData() {
        try {
            String str = in.readLine();
            System.out.println("Get:" + str);
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public void downService() {
        try {
            if (!clientSocket.isClosed()) {
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package client.gui.chat;

import java.io.*;
import java.net.Socket;

public class ChatConnection {
    public Socket clientSocket;
    public ObjectInputStream in; // поток чтения из сокета
    public ObjectOutputStream out; // поток чтения в сокет
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
            OutputStream outputStream = clientSocket.getOutputStream();
            out = new ObjectOutputStream(outputStream);
            InputStream inputStream = clientSocket.getInputStream();
            in = new ObjectInputStream(inputStream);
        } catch (IOException e) {
            System.out.println("ss");

            e.printStackTrace();
            downService();
        }
        System.out.println("ss");

    }

    public void sendData(String str) throws IOException {
        if (clientSocket.isConnected()) {
            System.out.println("Send:" + str);
            out.writeObject(str);
            out.flush();
        }
        else {System.out.println("Close");}
    }

//    public boolean hasData(){
//        try {
//            return in.ready();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public String getData() {
        try {
            String str = (String) in.readObject();
            System.out.println("Get:" + str);
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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

package server;

import java.io.*;
import java.net.*;


class ClientHandler extends Thread {

    private Socket clientSocket; // сокет, через который сервер общается с клиентом,
    private ObjectInputStream in; // поток чтения из сокета
    private ObjectOutputStream out; // поток завписи в сокет
    private MessageHandler msgHandler;

    public ClientHandler(Socket socket) throws IOException {
        System.out.println("Client connected");
        clientSocket = socket;
        msgHandler = new MessageHandler();
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        start();
    }

    @Override
    public void run() {
        try {
            String word;
            while (true) {
                word = (String) in.readObject();
                sendMsgAll(word);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sendMsgAll(String msg){
        for (ClientHandler client : Server.serverList) {
            if (client != this){
                client.send(msg);
            }
        }
    }

    private void send(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downService() throws IOException {
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
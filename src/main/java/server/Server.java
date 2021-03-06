package server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server{

    public static final int PORT = 3333;
    public static LinkedList<ClientHandler> serverList = new LinkedList<>();

    public Server() throws IOException {
        Socket socket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server Started");
            while (true){
                socket = serverSocket.accept();
                try {
                    System.out.println("New client");
                    serverList.add(new ClientHandler(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            socket.close();
        }
    }
}

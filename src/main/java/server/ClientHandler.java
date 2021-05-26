package server;

import java.io.*;
import java.net.*;


class ClientHandler extends Thread {

    private Socket clientSocket; // сокет, через который сервер общается с клиентом,
    private BufferedReader in; // поток чтения из сокета
    private PrintWriter out; // поток завписи в сокет

    public ClientHandler(Socket socket) throws IOException {
        System.out.println("Client connected");
        this.clientSocket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
        start();
    }

    @Override
    public void run() {
        try {
            String word;
            while (true) {
                word = in.readLine();
                if (word.equals("stop")) {
                    this.downService();
                    break;
                }
                System.out.println("Echoing: " + word);
                for (ClientHandler client : Server.serverList) {
                    if (client != this){
                        client.send(word);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send(String msg) {
        out.println(msg);
        out.flush();
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
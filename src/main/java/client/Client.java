package client;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток чтения в сокет
    private BufferedReader inputConsole; // поток чтения с консоли
    private String ip; // ip адрес клиента
    private int port; // порт соединения
    private String nickname; // имя клиента

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        try {
            clientSocket = new Socket(ip, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            inputConsole = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            getNickname();
            new ReadMsg().start();
            new WriteMsg().start();
        } catch (IOException e) {
            e.printStackTrace();
            downService();
        }
    }

    private void getNickname() {
        System.out.print("Press your nick: ");
        try {
            nickname = inputConsole.readLine();
            out.write("Hello " + nickname + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ReadMsg extends Thread {
        @Override
        public void run() {

            String str;
            try {
                while (true) {
                    str = in.readLine();
                    if (str.equals("stop")) {
                        downService();
                        break;
                    }
                    System.out.println(str);
                }
            } catch (IOException e) {
                downService();
            }
        }
    }

    public class WriteMsg extends Thread {

        @Override
        public void run() {
            while (true) {
                String text;
                try {
                    text = inputConsole.readLine();
                    if (text.equals("stop")) {
                        out.write("stop" + "\n");
                        downService();
                        break;
                    } else {
                        out.write(nickname + ": " + text + "\n");
                    }
                    out.flush();
                } catch (IOException e) {
                    downService();
                }
            }
        }

    }

    private void downService() {
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

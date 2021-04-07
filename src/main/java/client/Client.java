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
        } finally {
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
                e.printStackTrace();
            } finally {
                downService();
            }
        }
    }

    private class WriteMsg extends Thread {
        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    userWord = inputConsole.readLine(); // сообщения с консоли
                    if (userWord.equals("stop")) {
                        out.write("stop" + "\n");
                        Client.this.downService(); // харакири
                        break; // выходим из цикла если пришло "stop"
                    } else {
                        out.write(userWord); // отправляем на сервер
                    }
                    out.flush(); // чистим
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
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

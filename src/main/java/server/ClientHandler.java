package server;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    public ClientHandler(Socket socket) throws IOException {
        this.clientSocket = socket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    }

    @Override
    public void run(){
        String text;
        try {
            text = in.readLine();
            try {
                out.write(text + "\n");
                out.flush();
            } catch (IOException e) {}
                try {
                    while (true){
                        text = in.readLine();
                        if (text.equals("@stop"))
                            this.downService();
                        break;
                    }
                } catch (IOException ioException) { }
        } catch (IOException e) { }
    }

    private void downService() throws IOException {
        clientSocket.close();
        in.close();
        out.close();
    }
}

package client;

public class Main {

    public static String ip = "localhost";
    public static int port = 3333;

    public static void main(String[] args) {
        new Client(ip, port);
    }
}

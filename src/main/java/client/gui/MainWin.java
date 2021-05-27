package client.gui;

import client.gui.chat.Chat;

import javax.swing.*;

public class MainWin extends JFrame {

    private JPanel chatPanel;
    private Chat chat;
    private String login;
    private char[] password;


    public MainWin() {
        prepareMainFrame();
    }

    private void prepareMainFrame() {
        setTitle("Messenger");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        prepareChat();
        prepareSignIn();


        pack();

        setVisible(true);
    }

    private void prepareChat() {
        chat = new Chat();
        chatPanel = chat.getChatPanel();
        add(chatPanel);
    }

    private void prepareSignIn() {
        var signIn = new SignInWin(this, "SignIn", true);
    }

    public void setChatNameAndPassword(String name, char[] password) {
        chat.setName(name);
    }

    public static void main(String[] args) {
        new MainWin();
    }
}
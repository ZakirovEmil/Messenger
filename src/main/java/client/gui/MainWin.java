package client.gui;

import javax.swing.*;

public class MainWin extends JFrame {

    private JPanel chatPanel;
    private Chat chat;


    public MainWin(){
        prepareMainFrame();
    }

    private void prepareMainFrame(){
        setSize(500, 500);
        setTitle("Messenger");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        prepareChat();

        setVisible(true);
    }

    private void prepareChat(){
        chat = new Chat();
        chatPanel = chat.getChatPanel();
        add(chatPanel);
    }

    public static void main(String[] args){
        new MainWin();
    }
}
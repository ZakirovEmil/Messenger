package client.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chat {
    private ChatPanel chatPanel;
    private ChatConnection connection;
    private ReadMsg readMsg;

    public Chat() {
        chatPanel = new ChatPanel(new SendMsgBtnAction());
        connection = new ChatConnection("localhost", 3333);
        readMsg = new ReadMsg();
    }

    public JPanel getChatPanel(){
        return chatPanel.getChat();
    }

    private class SendMsgBtnAction extends Thread implements ActionListener {

        public SendMsgBtnAction() {
            start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(chatPanel.getMsg());
            String str = chatPanel.getMsg();
            chatPanel.addNewMessage(str);
            connection.sendData(str);
        }
    }

    private class ReadMsg extends Thread {

        public ReadMsg() {
            start();
        }

        @Override
        public void run() {
            while (true){
                chatPanel.addNewMessage(connection.getData());
            }
        }
    }
}

package client.gui.chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

    public void setName(String name){
        chatPanel.setNickField(name);
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
            try {
                connection.sendData(str);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
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

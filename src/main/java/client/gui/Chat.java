package client.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Chat {
    private ChatPanel chatPanel;
    private ChatConnection connection;
    private ReadMsg readMsg;

    public Chat() {
        chatPanel = new ChatPanel(new SendButtonAction());
        connection = new ChatConnection("localhost", 3333);
        readMsg = new ReadMsg();
    }

    public JPanel getChatPanel(){
        return chatPanel.getChat();
    }

    private class SendButtonAction implements ActionListener {

//        public SendButtonAction() {
//            start();
//        }


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
//            while (true){
////                if (connection.hasData()) {
////                    chatPanel.addNewMessage(connection.getData());
////                }
//                var str = connection.getData();
//                if (!str.isEmpty()){
//                    chatPanel.addNewMessage(str);
//                }
//            }
            while (true) {
                String str = null;
                try {
                    str = connection.in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                if (str.equals("stop")) {
//                    break;
//                }
                System.out.println(str);
            }
        }
    }
}

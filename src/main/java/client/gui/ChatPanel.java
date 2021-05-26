package client.gui;
import client.Client;
import message.messages.TextMsg;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class ChatPanel {
    public TextMsg textMsg;
    private JLabel countMembersLabel;
    private JPanel messagesPanel;
    private JTextArea historyChat;
    private JTextField msgField;
    private JTextField nickField;
    private JButton sendBtn;
    private JPanel chatPnl;

    public ChatPanel(ActionListener buttonListener) {
        chatPnl = new JPanel();
//        chatPnl.setBackground(Color.GRAY);
        chatPnl.setLayout(new BoxLayout(chatPnl, BoxLayout.Y_AXIS));

        countMembersLabel = new JLabel("Count members:");
        countMembersLabel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 25));
//        countMembersLabel.setBackground(Color.RED);
//        countMembersLabel.setHorizontalTextPosition(JLabel.LEFT);
        countMembersLabel.setHorizontalAlignment(SwingConstants.LEFT);
//        countMembersLabel.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
//        countMembersLabel.setAlignmentY(JComponent.RIGHT_ALIGNMENT);
        chatPnl.add(countMembersLabel);

        historyChat = new JTextArea("sjgnbsjgnsgjsgjsngjkl");
        historyChat.setPreferredSize(new Dimension(100, 100));
        historyChat.setEditable(false);
        historyChat.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        chatPnl.add(historyChat);

//        messagesPanel = new JPanel();
//        messagesPanel.setMinimumSize(new Dimension(100 ,100));
//        messagesPanel.add(nickField);
//        chatPnl.add(messagesPanel);



        JPanel controlPnl = new JPanel();
        sendBtn = new JButton("Send");
        sendBtn.addActionListener(buttonListener);
        controlPnl.setLayout(new BoxLayout(controlPnl, BoxLayout.X_AXIS));
        msgField = new JTextField(25);
        msgField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        nickField = new JTextField(10);
        nickField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
//        nickField.setBackground(Color.GRAY);
        controlPnl.add(nickField);
        controlPnl.add(msgField);
        controlPnl.add(sendBtn);
        chatPnl.add(controlPnl);

        textMsg = new TextMsg("");

    }

    public String getMsg(){
        return nickField.getText() + ":" + msgField.getText();
    }

    public void addNewMessage(String str){
        historyChat.setText(historyChat.getText() + "\n" + str);
    }

//    privat void ButtonAction() implements ActionListener {
//
//    }

    public JPanel getChat(){
        return chatPnl;
    }

}

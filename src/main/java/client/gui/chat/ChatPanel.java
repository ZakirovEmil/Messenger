package client.gui.chat;

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
    private String nick;
    private JButton sendBtn;
    private JPanel chatPnl;

    public ChatPanel(ActionListener buttonListener) {
        chatPnl = new JPanel();
        chatPnl.setSize(300, 100);
        chatPnl.setLayout(new BoxLayout(chatPnl, BoxLayout.Y_AXIS));

//        countMembersLabel = new JLabel("Count members:");
//        countMembersLabel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 25));
//        countMembersLabel.setHorizontalAlignment(SwingConstants.LEFT);
//        chatPnl.add(countMembersLabel);

        historyChat = new JTextArea();
//        historyChat.setPreferredSize(new Dimension(100, 100));
        historyChat.setEditable(false);
        historyChat.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(historyChat);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        chatPnl.add(scrollPane);

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
        msgField.setSize(25,20);
//        nickField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
//        controlPnl.add(nickField);
        controlPnl.add(msgField);
        controlPnl.add(sendBtn);
        msgField.setMaximumSize(new Dimension(chatPnl.getWidth(), 50));
        chatPnl.add(controlPnl);

        textMsg = new TextMsg("");

    }

    public void setNickField(String nick){
        this.nick = nick;
    }

    public String getMsg() {
        return nick + ":" + msgField.getText();
    }

    public void addNewMessage(String str) {
        historyChat.setText(historyChat.getText() + "\n" + str);
    }

    public JPanel getChat() {
        return chatPnl;
    }

}

package client.new_gui.windows;

import client.new_gui.controllers.MessengerViewModel;
import client.new_gui.model.Connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MessengerView extends JFrame implements ItemListener {

    private MessengerViewModel messengerViewModel;
    private JPanel views;


    public MessengerView() {
        init();
    }

    private void init() {
        messengerViewModel = new MessengerViewModel();

        initGUI();
    }


    private void initGUI() {
        setTitle("Messenger");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        views = new JPanel(new CardLayout());

        initEnter();
        addingViews();


        pack();

        setVisible(true);
    }

    private void addingViews() {
        views.add(new SignView(this, "Messenger", true), "Sign");
        views.add(new ChatView(), "Chats");
    }

    private void initEnter() {
        new SignView(this, "Messenger", true);
    }

    private void bind() {

    }

    private void backBind() {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}

package client.new_gui.windows;

import client.new_gui.controllers.MessengerViewModel;
import client.new_gui.model.Connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MessengerView extends JFrame {

    private MessengerViewModel messengerViewModel;
    private JPanel signView;
    private JPanel views;
    private ChatView chatView;
    private String state;


    public MessengerView() {
        init();
    }

    private void init() {
        messengerViewModel = new MessengerViewModel();

        initGUI();
    }


    private void initGUI() {
        initFrame();

        views = new JPanel(new CardLayout());
        add(views);

        addingViews();

        pack();
        setVisible(true);

        bind();
    }

    private void initFrame() {
        setTitle("Messenger");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void addingViews() {
        signView = new SignView(this);
//        chatView = new ChatView(this);
        views.add(new SignView(this), "Sign");
        views.add(new ChatView(this), "Chat");
    }

    public void changeState(String state) {
        this.state = state;
        backBind();
        bind();
    }

    private void bind() {
        CardLayout cl = (CardLayout)(views.getLayout());
        cl.show(views, messengerViewModel.getState());
    }

    private void backBind() {
        messengerViewModel.setState(state);
    }
}

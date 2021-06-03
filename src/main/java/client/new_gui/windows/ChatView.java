package client.new_gui.windows;

import javax.swing.*;

public class ChatView extends JPanel implements View {

    private JFrame root;

    public ChatView(MessengerView root) {
        this.root = root;
    }
}

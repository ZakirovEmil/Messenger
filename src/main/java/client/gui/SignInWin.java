package client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInWin extends JDialog {

    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JButton okButton;
    private JTextField loginField;
    private JPasswordField passwordField;
    private char[] password;
    private MainWin root;

    public SignInWin(MainWin root, String title, boolean modal) {
        super(root, title, modal);
        this.root = root;
        prepareWin();
    }

    private void prepareWin() {
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        loginLabel = new JLabel("Login:");
        loginLabel.setSize(new Dimension(30, 10));
        loginField = new JTextField(20);
        loginField.setPreferredSize(new Dimension(5, 25));
        passwordLabel = new JLabel("Password:");
        passwordLabel.setSize(new Dimension(30, 10));
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(5, 25));
        okButton = new JButton("OK");
        okButton.addActionListener(new okButtonAction());
//        setMinimumSize(new Dimension(300, 200));
        add(loginLabel);
        add(loginField);
        add(passwordLabel);
        add(passwordField);
        add(okButton);

        pack();
        setVisible(true);

    }

    public boolean hasLogin() {
        return !loginField.getText().isEmpty();
    }

    public boolean hasPassword() {
        return passwordField.getPassword().length != 0;
    }

    private void closeWin() {
        dispose();
    }

    private class okButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (hasLogin() && hasPassword()) {
                root.setChatNameAndPassword(loginField.getText(), passwordField.getPassword());
                closeWin();
            }
        }
    }
}

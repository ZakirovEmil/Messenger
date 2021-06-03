package client.new_gui.windows;

import client.gui.MainWin;
import client.new_gui.controllers.SignViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class SignView extends JPanel implements View {

    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JButton okButton;
    private JTextField loginField;
    private JPasswordField passwordField;
    private SignViewModel signViewModel;
    private char[] password;
    private MessengerView root;
    private boolean successful;

    public SignView(MessengerView root) {
        this.root = root;
        signViewModel = new SignViewModel();
        initGUI();
        bind();
    }


    private void initGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        loginLabel = new JLabel("Login:");
        loginLabel.setSize(new Dimension(30, 10));
        loginField = new JTextField(20);
        loginField.setPreferredSize(new Dimension(5, 25));
        addListener(loginField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setSize(new Dimension(30, 10));
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(5, 25));
        addListener(passwordField);

        okButton = new JButton("OK");
        okButton.addActionListener(new okButtonAction());
//        setMinimumSize(new Dimension(300, 200));

        add(loginLabel);
        add(loginField);
        add(passwordLabel);
        add(passwordField);
        add(okButton);
//        setVisible(true);
    }

    private void addListener(JTextField loginField) {
        loginField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                backBind();
                bind();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                backBind();
                bind();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                backBind();
                bind();
            }
        });
    }

    private class okButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            backBind();
//            bind();
            root.changeState("Chat");

        }
    }

    private void bind() {
        okButton.setEnabled(signViewModel.isEnabledIn());
//        loginField.setText(signViewModel.getLogin());
//        passwordField.setText(Arrays.toString(signViewModel.getPassword()));
//        root.changeState("Sign");
    }

    private void backBind() {
        signViewModel.setLogin(loginField.getText());
        signViewModel.setPassword(passwordField.getPassword());
    }
}

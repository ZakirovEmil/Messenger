package client.new_gui.controllers;

import client.new_gui.model.Connector;
import client.new_gui.model.SignModel;
import client.new_gui.windows.SignView;

public class SignViewModel {

    private char[] password;
    private String login;
    private boolean btnEnabledUp = false;
    private boolean btnEnabledIn = false;
    private SignModel model;

    public SignViewModel() {
        model = new SignModel(this);
    }


    public void setLogin(String login) {
        if (login.isEmpty() || login.length() > 30) {
            setBtnStatus(false);
            return;
        }
        setBtnStatus(true);
        this.login = login;

    }

    public void setPassword(char[] password) {
        if (password.length == 0 || password.length > 30) {
            setBtnStatus(false);
            return;
        }
        setBtnStatus(true);
        this.password = password;
    }

    private void setBtnStatus(boolean status) {
        btnEnabledUp = status;
        btnEnabledIn = status;
    }

    public boolean isEnabledUp() {
        return btnEnabledUp;
    }

    public boolean isEnabledIn() {
        return btnEnabledIn;
    }
}

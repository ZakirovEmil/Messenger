package client.new_gui.controllers;

import client.new_gui.model.SignModel;

public class SignViewModel {

    private char[] password;
    private String login;
    private boolean btnEnabledUp = false;
    private boolean btnEnabledIn = false;
    private SignModel model;

    public SignViewModel() {
        password = new char[]{};
        model = new SignModel(this);
    }


    public void setLogin(String lg) {
        checkField();
        this.login = lg;

    }

    public void setPassword(char[] psw) {
        checkField();
        password = psw;
    }

    private void checkField() {
        if (password.length == 0 || login.isEmpty()) {
            setBtnStatus(false);
        } else {
            setBtnStatus(true);
        }
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

    public char[] getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}

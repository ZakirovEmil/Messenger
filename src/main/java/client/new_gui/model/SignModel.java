package client.new_gui.model;

import client.new_gui.controllers.SignViewModel;
import message.Message;

public class SignModel {

    private SignViewModel signViewModel;
    private Connector connector;

    public SignModel(SignViewModel signViewModel){
        connector = new Connector();
        this.signViewModel = signViewModel;
    }

    private void sendMsg(Message msg) {
        connector.sendData(msg);
    }

    private void sendEvent() {

    }

    private class ReadMsg extends Thread {

        public ReadMsg() {
            start();
        }

        @Override
        public void run() {
            while (true){
                connector.getData();
            }
        }
    }



}

package message.messages;

import message.Message;

public class SingInMsg implements Message {
    private String login;
    private String password;

    public SingInMsg(String login, String password){
        this.login = login;
        this.password = password;
    }
}

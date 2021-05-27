package message.messages;

import message.Message;

public class SignUpMsg implements Message {
    private String login;
    private String password;
    private String nickName;

    public SignUpMsg(String nickName, String login, String password){
        this.nickName = nickName;
        this.login = login;
        this.password = password;
    }
}

package Message;

public class SignUpMsg implements Message {
    private String login;
    private String password;

    SignUpMsg(String login, String password){
        this.login = login;
        this.password = password;
    }
}

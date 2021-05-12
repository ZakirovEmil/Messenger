package Message;

public class OnlineStatusMsg implements Message {
    private Enum<Messages> codeMsg;
    private Enum<OnlineStatus> status;


    OnlineStatusMsg(Enum<OnlineStatus> status) {
        this.status = status;
        codeMsg = Messages.Service;
    }
}

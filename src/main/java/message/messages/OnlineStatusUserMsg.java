package message.messages;

import message.Message;

public class OnlineStatusUserMsg implements Message {
    private Enum<OnlineStatus> status;


    public OnlineStatusUserMsg(Enum<OnlineStatus> status) {
        this.status = status;
    }
}

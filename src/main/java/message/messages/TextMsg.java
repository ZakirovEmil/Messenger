package message.messages;

import message.Message;

public class TextMsg implements Message {
    private String textMsg;

    public TextMsg(String textMsg){
        this.textMsg = textMsg;
    }
}

package client.new_gui.controllers;

public class MessengerViewModel {

    private String state;
//    private String

    public MessengerViewModel(){
        setDefault();
    }

    private void setDefault() {
        state = "Sign";
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (state.equals("close")) {
            System.exit(0);
        }
        this.state = state;
    }
}
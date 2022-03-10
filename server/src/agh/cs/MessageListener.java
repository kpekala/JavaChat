package agh.cs;

public interface MessageListener {
    void onNewMessage(String message, int clientID);
}

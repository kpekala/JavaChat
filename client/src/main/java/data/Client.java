package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class Client {
    private final String serverIP = "localhost";
    private SocketManager socketManager;
    private final int portNumber = 12345;
    private String nick = "";

    public void connect(ResponseListener listener) {
        new Thread(() -> {
            try {
                socketManager = new SocketManager(new Socket(serverIP, portNumber));
                listener.success(null);
            } catch (IOException e) {
                e.printStackTrace();
                listener.error(e.getMessage());
            }
        }).start();
    }
    public void disconnect(){
        try {
            if (socketManager != null)
                socketManager.disconnect();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
    public void sendMessage(String message, ResponseListener listener){
        new Thread(() -> {
            try {
                //socketManager.printLine("new_msg");
                socketManager.printLine(message);
                listener.success(null);
            } catch (IOException exception) {
                exception.printStackTrace();
                listener.error("println error");
            }
        }).start();
    }
    public void startListening(ResponseListener listener){
        new Thread(() -> {
            while (true){
                try {
                    String message = socketManager.readMessage();
                    HashMap<String, Object> response = new HashMap<>();
                    response.put("message",message);
                    listener.success(response);
                } catch (IOException exception) {
                    exception.printStackTrace();
                    listener.error(exception.getMessage());
                }
            }
        }).start();
    }
}

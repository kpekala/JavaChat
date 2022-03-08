package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final String serverIP = "localhost";
    private SocketManager socketManager;
    private final int portNumber = 12345;
    private Socket socket;
    private String nick = "";

    private PrintWriter out;
    private BufferedReader in;
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
        if(socket != null){
            try {
                socket.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
    public void sendMessage(SendMessageListener messageListener){

    }
    public void startListening(ReceiveMessageListener messageListener){

    }
}

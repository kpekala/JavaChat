package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Client {
    private final String serverIP = "localhost";
    private final int portNumber = 12345;
    private Socket socket;
    private String nick = "";
    public void connect() {
        new Thread(() -> {
            try {
                socket = new Socket(serverIP, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(socket.getInputStream()));
                out.println("new_user");
                String response = in.readLine();
                System.out.println("received msg: " + response);
            } catch (IOException e) {
                e.printStackTrace();
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

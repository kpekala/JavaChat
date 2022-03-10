package agh.cs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Connection {
    private final ServerSocket serverSocket;
    private final Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader in;
    private final MessageListener messageListener;

    private int id = new Random().nextInt(100000);

    public Connection(ServerSocket serverSocket, Socket clientSocket, MessageListener messageListener) throws IOException {
        this.serverSocket = serverSocket;
        this.clientSocket = clientSocket;

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.messageListener = messageListener;
    }
    
    public void listen(){
        try {
            while (true){
                String message = in.readLine();
                messageListener.onNewMessage(message, id);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("Closing connection with client");
    }

    public void sendMessage(String message){
        out.println(message);
    }
}

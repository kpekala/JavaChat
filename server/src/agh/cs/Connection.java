package agh.cs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    private final ServerSocket serverSocket;
    private final Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader in;

    public Connection(ServerSocket serverSocket, Socket clientSocket) throws IOException {
        this.serverSocket = serverSocket;
        this.clientSocket = clientSocket;

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    
    public void listen(){
        try {
            String message = in.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void sendMessage(String message){
        out.println(message);
    }
}

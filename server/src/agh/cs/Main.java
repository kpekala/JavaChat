package agh.cs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Main {

    private final int portNumber = 12345;
    private Server server;

    public Main(){
        try {
            server = new Server(new ServerSocket(portNumber));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void connectNewUser(Socket clientSocket) throws IOException {
        System.out.println("client connected");
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String msg = in.readLine();
        if (msg.equals("new_user")){
            System.out.println("received msg: " + msg);
            out.println(new Random().nextInt(1024));
        }
    }

    public static void main(String[] args){
        new Main();
    }
}

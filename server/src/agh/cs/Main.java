package agh.cs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        Server server;
        int port = 12345;
        try {
            server = new Server(new ServerSocket(port));
            System.out.println("Chat server is running on port " + port);
            server.listen();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

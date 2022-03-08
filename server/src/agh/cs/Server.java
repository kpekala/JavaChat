package agh.cs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private final ArrayList<Connection> connections = new ArrayList<>();
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        listen();
    }

    private void listen() {
        while (true){
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                startCommunication(clientSocket);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void startCommunication(Socket clientSocket) throws IOException {
        Connection connection = new Connection(serverSocket, clientSocket);
        connections.add(connection);
        new Thread(connection::listen).start();
    }
}

package agh.cs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements MessageListener {
    private final ArrayList<Connection> connections = new ArrayList<>();
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void listen() {
        while (true){
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
                System.out.println("New client accepted");
                startCommunication(clientSocket);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void startCommunication(Socket clientSocket) throws IOException {
        Connection connection = new Connection(serverSocket, clientSocket, this);
        connections.add(connection);
        new Thread(connection::listen).start();
    }

    @Override
    public void onNewMessage(String message, int clientID) {
        sendBroadcast(message,clientID);
    }

    private synchronized void sendBroadcast(String message, int clientID) {
        for (Connection connection: connections){
            connection.sendMessage(clientID + " said: \n" + message);
        }
    }
}

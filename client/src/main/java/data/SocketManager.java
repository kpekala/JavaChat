package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketManager {
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;

    public SocketManager(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void printLine(String message) throws IOException{
        out.println(message);
        if (out.checkError()){
            throw new IOException();
        }
    }

    public String readMessage() throws IOException {
        return in.readLine();
    }

    public void disconnect() throws IOException {
        socket.close();
    }
}

package ui;

import data.Client;
import data.ResponseListener;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import ui.utils.Style;

import java.util.HashMap;

public class MainController {
    public ListView<String> messagesView;
    public Button sendButton;
    public TextArea messageField;
    public Circle connectionMarker;

    private final Client client = new Client();

    public void onStart(){
        messagesView.getItems().removeAll();
        client.connect(new ResponseListener() {
            @Override
            public void success(HashMap<String, Object> response) {
                connectionMarker.setFill(Style.success);
                startListening();
            }
            @Override
            public void error(String message) {
                connectionMarker.setFill(Style.error);
                System.out.println("Connection error: " + message);
            }
        });
    }

    private void startListening() {
        client.startListening(new ResponseListener() {
            @Override
            public void success(HashMap<String, Object> response) {
                String message = (String) response.get("message");
                Platform.runLater(() -> {
                    messagesView.getItems().add(message);
                });
            }

            @Override
            public void error(String message) {
                System.out.println(message);
            }
        });
    }

    public void onSendClicked(MouseEvent mouseEvent) {
        String message = messageField.getText();
        client.sendMessage(message, new ResponseListener() {
            @Override
            public void success(HashMap<String, Object> response) {
                System.out.println("message sent");
            }
            @Override
            public void error(String message) {
                System.out.println(message);
            }
        });
    }
}

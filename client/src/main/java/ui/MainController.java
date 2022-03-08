package ui;

import data.Client;
import data.ResponseListener;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import ui.utils.Style;

import java.util.HashMap;

public class MainController {
    public ListView<String> messagesView;
    public Button sendButton;
    public TextArea messageField;
    public Circle connectionMarker;

    private Client client = new Client();

    public void onStart(){
        messagesView.getItems().removeAll();
        client.connect(new ResponseListener() {
            @Override
            public void success(HashMap<String, Object> response) {
                connectionMarker.setFill(Style.success);
            }
            @Override
            public void error(String message) {
                connectionMarker.setFill(Style.error);
                System.out.println("Connection error: " + message);
            }
        });
    }
}

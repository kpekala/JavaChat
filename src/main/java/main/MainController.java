package main;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class MainController {
    public ListView<String> messagesView;
    public Button sendButton;
    public TextArea messageField;

    public void onStart(){
        messagesView.getItems().removeAll();
    }
}

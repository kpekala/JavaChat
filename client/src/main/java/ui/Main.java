package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Scene mainScene;
    private MainController mainController;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        mainScene = loadScene("/scene.fxml", loader);
        mainController = loader.getController();
        mainController.onStart();

        stage.setScene(mainScene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

    private Scene loadScene(String fileName, FXMLLoader loader) throws IOException {
        loader.setLocation(getClass().getResource(fileName));
        Parent content = loader.load();

        return new Scene(content);
    }
}

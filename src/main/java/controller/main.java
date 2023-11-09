package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.FileEncrypt;

import java.io.File;
import java.io.IOException;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FileEncrypt fe = new FileEncrypt();
        fe.encryptFile();

        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("log-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Identifiez-vous");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
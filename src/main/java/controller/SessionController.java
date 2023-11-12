package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SessionController implements Initializable {
    @FXML
    private ListView<String> ueList;

    @FXML
    private Button returnButton;

    List<String> listeDesUes = new ArrayList<>();

    SessionSingleton listeSession = SessionSingleton.getInstance();

    //on va cree une array avec la lsite des utilisateurs dans lequelle on va ajouter les utilisateurs déjà cree
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            listeSession.lecture("/datas/session.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Session elem : listeSession.getListSession()){
            ueList.getItems().add(elem.toString());
        }
        //on recupere les listes des ues depuis un fichier
        // dashboardLabel.setText("Connecté en tant que " + data.getStatut() + " :)");

    }

    @FXML
    protected void onCreateButtonClick() throws IOException {
        //on change de scene une fois qu'on a defini le statut de l'utilisateur
        Stage stage = (Stage) returnButton.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("session-create-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(root));
    }

    @FXML
    protected void onSupprimeButtonClick() throws IOException {
        final int selectedIdx = ueList.getSelectionModel().getSelectedIndex();

        if (selectedIdx != -1) {
            listeSession.setLastSessionTouched(listeSession.getListSession().get(selectedIdx));
            ueList.getItems().remove(selectedIdx);
            listeSession.getListSession().remove(selectedIdx);
            listeSession.suppression("src/main/resources/datas/session.txt");
        }
    }
    @FXML
    protected void onReturnBouttonClick() throws IOException {
        //on change de scene une fois qu'on a defini le statut de l'utilisateur
        Stage stage = (Stage) returnButton.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("dashboard-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(root));


    }
}
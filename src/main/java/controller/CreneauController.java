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

public class CreneauController implements Initializable {
    @FXML
    private ListView<String> CreneauListView;

    CreneauSingleton listeCreneau = CreneauSingleton.getInstance();

    @FXML
    private Button returnButton;

    DataSingleton data = DataSingleton.getInstance();
    //on va cree une array avec la lsite des utilisateurs dans lequelle on va ajouter les utilisateurs déjà cree
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            listeCreneau.lecture("/datas/creneau.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Creneau elem : listeCreneau.getListeCreneau()){
            CreneauListView.getItems().add(elem.toString());
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
            root = FXMLLoader.load(getClass().getResource("creneau-create-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(root));
    }

    @FXML
    protected void onSupprimeButtonClick() throws IOException {
        final int selectedIdx = CreneauListView.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            listeCreneau.setLastCreneauTouched(listeCreneau.getListeCreneau().get(selectedIdx));
            CreneauListView.getItems().remove(selectedIdx);
            listeCreneau.getListeCreneau().remove(selectedIdx);
            listeCreneau.suppression("src/main/resources/datas/creneau.txt");
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
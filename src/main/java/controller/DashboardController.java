package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataSingleton;
import model.Enseignant;
import model.Etudiant;
import model.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;
public class DashboardController implements Initializable {
    @FXML
    private Label dashboardLabel;

    @FXML
    private Button ueButton;

    DataSingleton data = DataSingleton.getInstance();
    //on va cree une array avec la lsite des utilisateurs dans lequelle on va ajouter les utilisateurs déjà cree
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardLabel.setText("Connecté en tant que " + data.getStatut() + " :)");
    }

    @FXML
    protected void onUeButtonClick() throws IOException {

        //on change de scene une fois qu'on a defini le statut de l'utilisateur
        Stage stage = (Stage) ueButton.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass ().getResource("ue-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Gestion des UEs");
        stage.setScene(new Scene(root));



    }

    @FXML
    protected void onPromoButtonClick() throws IOException {

        //on change de scene une fois qu'on a defini le statut de l'utilisateur
        Stage stage = (Stage) ueButton.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("promotion-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Gestion des promos");
        stage.setScene(new Scene(root));

    }


    @FXML
    protected void onCreneauButtonClick() throws IOException {

        //on change de scene une fois qu'on a defini le statut de l'utilisateur
        Stage stage = (Stage) ueButton.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass ().getResource("creneau-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Gestion des UEs");
        stage.setScene(new Scene(root));



    }


}
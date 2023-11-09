package controller;

import javafx.fxml.Initializable;
import model.DataSingleton;
import model.Enseignant;
import model.Etudiant;
import model.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;
public class DashboardController implements Initializable {
    @FXML
    private Label dashboardLabel;


    DataSingleton data = DataSingleton.getInstance();
    //on va cree une array avec la lsite des utilisateurs dans lequelle on va ajouter les utilisateurs déjà cree
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardLabel.setText("Connecté en tant que " + data.getStatut() + " :)");
    }
}
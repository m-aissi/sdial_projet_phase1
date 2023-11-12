package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.UniteEnseignement;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SessionCreateController implements Initializable {


    @FXML
    private Label errorCreneau;

    @FXML
    private Label errorListUe;

    @FXML
    private Label errorPromo;

    @FXML
    private Button returnButton;

    @FXML
    private ListView<String> creneauList;
    @FXML
    private ListView<String> selectedCreneauList;
    @FXML
    private ListView<String> promoList;
    @FXML
    private ListView<String> ueList;

    List<String> listeNomResponsable = new ArrayList<>();
    List<UniteEnseignement> listeUe = new ArrayList<>();

    //on va cree une array avec la lsite des utilisateurs dans lequelle on va ajouter les utilisateurs déjà cree
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorCreneau.setText("");
        errorListUe.setText("");
        errorPromo.setText("");
        //on va afficher la liste des creneaux disponibles dans la liste des creneaux
        //on va afficher les promotions dans la liste des promotions
        //on va afficher les ues dans la liste des ues

    }

    @FXML
    protected void onReturnButtonClick() throws IOException {
        //on change de scene une fois qu'on a defini le statut de l'utilisateur
        Stage stage = (Stage) returnButton.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("session-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Creneaux");
        stage.setScene(new Scene(root));


    }

    @FXML
    protected void onValidateButtonClick() throws IOException {

        //on verifie qu'au moins un element à été choisi dans chaque lsit sinon on demande un element


        if (ueList.getSelectionModel().getSelectedItem() == null) {
            errorListUe.setText("Veuillez selectionner une salle");
        }
        else {
            errorListUe.setText("");
        }

        if (promoList.getSelectionModel().getSelectedItem() == null) {
            errorPromo.setText("Veuillez selectionner une salle");
        }
        else {
            errorPromo.setText("");
        }

        if (creneauList.getSelectionModel().getSelectedItem() == null) {
            errorCreneau.setText("Veuillez selectionner une salle");
        }
        else {
            errorCreneau.setText("");
        }
    //on verifie que les creneaux ne se chevauchent pas
        //on va verifier si
    }

    @FXML
    protected void onAddCreneauButtonClick() throws IOException {
        //on va verif que chaque champ est rempli correctement sinon on met a jour les messages d'erreur

    }

    @FXML
    protected void onDelCreneauButtonClick() throws IOException {

    }

}
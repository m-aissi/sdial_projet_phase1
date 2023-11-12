package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

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

    CreneauSingleton creneau = CreneauSingleton.getInstance();
    UEsSingleton ues = UEsSingleton.getInstance();
    PromotionSingleton promotions = PromotionSingleton.getInstance();

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
        try {
            creneau.lecture("/datas/creneau.txt");
            ues.lecture("/datas/ue.txt");
            promotions.lecture("/datas/promo.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (UniteEnseignement ue : ues.getListeUe()) {
            ueList.getItems().add(ue.toString());
        }
        for (Creneau creneau : creneau.getListeCreneau()) {
            creneauList.getItems().add(creneau.toString());
        }
        for (Promotion promo : promotions.getListePromotions()) {
            promoList.getItems().add(promo.getNameProm());
        }
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
        if (nombreErreur() == 0){
            String nomUe = ueList.getSelectionModel().getSelectedItem();
            String nomPromo = promoList.getSelectionModel().getSelectedItem();
            List<String> listeCreneau = new ArrayList<>();
            for (String creneau : selectedCreneauList.getItems()) {
                listeCreneau.add(creneau);
            }
           // Session session = new Session(nomUe, nomPromo, listeCreneau);

        }
        //on va verifier si
    }

    @FXML
    protected void onAddCreneauButtonClick() throws IOException {
        //on va verif que chaque champ est rempli correctement sinon on met a jour les messages d'erreur
        if(creneauList.getSelectionModel().getSelectedItem() == null){
            errorCreneau.setText("Veuillez selectionner un creneau");
        }
        else {
            errorCreneau.setText("");
            selectedCreneauList.getItems().add(creneauList.getSelectionModel().getSelectedItem());
            creneauList.getItems().remove(creneauList.getSelectionModel().getSelectedItem());
        }

    }

    @FXML
    protected void onDelCreneauButtonClick() throws IOException {
        if(selectedCreneauList.getSelectionModel().getSelectedItem() == null){
            errorCreneau.setText("Veuillez selectionner un creneau");
        }
        else {
            errorCreneau.setText("");
            creneauList.getItems().add(selectedCreneauList.getSelectionModel().getSelectedItem());
            selectedCreneauList.getItems().remove(selectedCreneauList.getSelectionModel().getSelectedItem());
        }
    }

    private int nombreErreur() {
        int nbErreur = 0;
        if (ueList.getSelectionModel().getSelectedItem() == null) {
            errorListUe.setText("Veuillez selectionner une salle");
            nbErreur++;
        }
        else {
            errorListUe.setText("");
        }

        if (promoList.getSelectionModel().getSelectedItem() == null) {
            errorPromo.setText("Veuillez selectionner une salle");
            nbErreur++;
        }
        else {
            errorPromo.setText("");
        }

        if (creneauList.getSelectionModel().getSelectedItem() == null) {
            errorCreneau.setText("Veuillez selectionner une salle");
            nbErreur++;
        }
        else {
            errorCreneau.setText("");
        }

        return nbErreur;
    }
}
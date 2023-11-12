package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UeCreateController implements Initializable {


    @FXML
    private Button returnButton;

    @FXML
    private ListView<String> responsableList;

    @FXML
    private TextField ectUe;
    @FXML
    private TextField heureUe;
    @FXML
    private TextField nomUe;
    @FXML
    private TextField codeUe;

    @FXML
    private Label errorNom;
    @FXML
    private Label errorHeure;
    @FXML
    private Label errorResponsable;
    @FXML
    private Label errorECTS;
    @FXML
    private Label errorCode;

    UEsSingleton listeUes = UEsSingleton.getInstance();
    UsersSingleton listeUsers = UsersSingleton.getInstance();
    List<Enseignant> listeNomResponsable = new ArrayList<>();
    List<UniteEnseignement> listeUe = new ArrayList<>();

    //on va cree une array avec la lsite des utilisateurs dans lequelle on va ajouter les utilisateurs déjà cree
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Etudiant etudiantTmp = new Etudiant();
        //on recupere la liste des utilisateurs
        for (Utilisateur elem : listeUsers.getListeUsers()) {
            if (!(etudiantTmp.getClass().equals(elem.getClass()))) {
                listeNomResponsable.add((Enseignant) elem);
                responsableList.getItems().add(elem.getNom());
            }
        }

        clearErrorMessages();

        Enseignant prof1 = new Enseignant("1","p","p","p","p");
        UniteEnseignement ue1 = new UniteEnseignement("243","ue1", 10, prof1, 5);
        UniteEnseignement ue2 = new UniteEnseignement("244","ue1", 10, prof1, 5);
        UniteEnseignement ue3 = new UniteEnseignement("245","ue1", 10, prof1, 5);

        listeUe.add(ue1);
        listeUe.add(ue2);
        listeUe.add(ue3);

        //on recupere les listes des ues depuis un fichier
        // dashboardLabel.setText("Connecté en tant que " + data.getStatut() + " :)");

    }

    @FXML
    protected void onReturnButtonClick() throws IOException {
        //on change de scene une fois qu'on a defini le statut de l'utilisateur
        Stage stage = (Stage) returnButton.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ue-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(root));


    }

    @FXML
    protected void onValidateButtonClick() throws IOException {

        if(numberOfErrors() == 0){
            //on init les variables
            String code = codeUe.getText();
            String nom = nomUe.getText();
            int nbHeures = Integer.parseInt(heureUe.getText());
            int creditECTS = Integer.parseInt(ectUe.getText());
            int indiceSelected = responsableList.getSelectionModel().getSelectedIndex();
            Enseignant responsable = listeNomResponsable.get(indiceSelected);

            //on clear la view
            clearErrorMessages();
            clearFields();

            //on cree l'ue
            errorECTS.setText("UE creee avec succes");
            listeUes.setLastUeTouched(new UniteEnseignement(code, nom, nbHeures, responsable, creditECTS));
            listeUes.creation("src/main/resources/datas/ue.txt");

        }
        //on verifie que le code n'est pas deja utilise


    }

    private int numberOfErrors(){
        String code = codeUe.getText();
        String nom = nomUe.getText();
        String nbHeures = heureUe.getText();
        String responsable = responsableList.getSelectionModel().getSelectedItem();
        String creditECTS = ectUe.getText();

        int nbErreur = 0;

        //on verifie si chaque champ est rempli
        if (code.isEmpty()) {
            errorCode.setText("Veuillez remplir ce champ");
            nbErreur++;
        }
        else {
            errorCode.setText("");
        }
        if (nom.isEmpty()) {
            errorNom.setText("Veuillez remplir ce champ");
            nbErreur++;
        }
        else {
            errorNom.setText("");
        }

        if (nbHeures.isEmpty()) {
            errorHeure.setText("Veuillez remplir ce champ");
            nbErreur++;
        }
        else {
            errorHeure.setText("");
        }
        //on verifie que le responsable est bien selectionne
        if (responsable == null) {
            errorResponsable.setText("Veuillez selectionner un responsable");
            nbErreur++;
        }
        else {
            errorResponsable.setText("");
        }

        if (creditECTS.isEmpty()) {
            errorECTS.setText("Veuillez remplir ce champ");
            nbErreur++;
        }
        else {
            errorECTS.setText("");
        }

        //on verifie que creditECTS et nbHeures sont des nombres
        try {
            Float.parseFloat(nbHeures);
        } catch (NumberFormatException e) {
            errorHeure.setText("Veuillez entrer un nombre");
            nbErreur++;
        }
        try {
            Float.parseFloat(creditECTS);
        } catch (NumberFormatException e) {
            errorECTS.setText("Veuillez entrer un nombre");
            nbErreur++;
        }

        for (UniteEnseignement elem : listeUes.getListeUe()) {
            if (elem.getCode().equals(code)) {
                errorCode.setText("Ce code est deja utilise");
                nbErreur++;
            }
        }

        return nbErreur;

    }

    private void clearErrorMessages() {
        errorNom.setText("");
        errorHeure.setText("");
        errorResponsable.setText("");
        errorECTS.setText("");
        errorCode.setText("");
    }

    private void clearFields() {
        codeUe.setText("");
        nomUe.setText("");
        heureUe.setText("");
        ectUe.setText("");
        responsableList.getSelectionModel().clearSelection();
    }
}
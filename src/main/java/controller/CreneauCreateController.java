package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Creneau;
import model.CreneauSingleton;
import model.Enseignant;
import model.UniteEnseignement;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class CreneauCreateController implements Initializable {

    @FXML
    private Button validateButton;

    @FXML
    private Label errorDate;

    @FXML
    private Label errorMinuteFin;

    @FXML
    private Label errorMinuteDebut;
    @FXML
    private Label errorHeureDebut;
    @FXML
    private Label errorHeureFin;
    @FXML
    private Label errorHeureDebutVide;
    @FXML
    private Label errorHeureFinVide;
    @FXML
    private Label errorSalle;

    @FXML
    private Button returnButton;

    @FXML
    private ListView<String> sallesList;

    @FXML
    private DatePicker dateDebut;

    @FXML
    private TextField minuteDebut;

    @FXML
    private TextField minuteFin;
    @FXML
    private TextField heureDebut;
    @FXML
    private TextField heureFin;

    CreneauSingleton listeCreneau = CreneauSingleton.getInstance();
    List<String> listeNomResponsable = new ArrayList<>();
    List<UniteEnseignement> listeUe = new ArrayList<>();

    //on va cree une array avec la lsite des utilisateurs dans lequelle on va ajouter les utilisateurs déjà cree
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            clearErrorMessages();

        sallesList.getItems().add("salle1");
        sallesList.getItems().add("salle2");
        sallesList.getItems().add("salle3");
        //on recupere les listes des ues depuis un fichier
        // dashboardLabel.setText("Connecté en tant que " + data.getStatut() + " :)");

    }

    @FXML
    protected void onReturnButtonClick() throws IOException {
        //on change de scene une fois qu'on a defini le statut de l'utilisateur
        Stage stage = (Stage) returnButton.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("creneau-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Creneaux");
        stage.setScene(new Scene(root));


    }

    @FXML
    protected void onValidateButtonClick() throws IOException {

        //on verifie que les creneaux ne se chevauchent pas


        if(nombreErreur() == 0){
            //on init les variables
            String salle = sallesList.getSelectionModel().getSelectedItem();
            LocalDate date = dateDebut.getValue();
            int minuteDebuts = Integer.parseInt(heureDebut.getText()) * 60 + Integer.parseInt(minuteDebut.getText());
            int minuteFins = Integer.parseInt(heureFin.getText()) * 60 + Integer.parseInt(minuteFin.getText());

            //on clear la view
            clearErrorMessages();
            clearFields();

            //on cree l'ue
            errorSalle.setText("Creneau cree avec succes");
            listeCreneau.setLastCreneauTouched(new Creneau(salle,date, minuteDebuts, minuteFins));
            listeCreneau.creation("src/main/resources/datas/creneau.txt");
        }
    }

    private int nombreErreur(){
        int nbErreur = 0;
        String heureDebutString = this.heureDebut.getText();
        String heureFinString = this.heureFin.getText();
        String minuteDebutString = this.minuteDebut.getText();
        String minuteFinString = this.minuteFin.getText();

        if (dateDebut.getValue() == null) {
            errorDate.setText("Veuillez renseigner une date");
            nbErreur++;
        }
        else {
            errorDate.setText("");
        }


        if (heureDebutString.isEmpty()) {
            errorHeureDebutVide.setText("Veuillez renseigner une heure");
            nbErreur++;
        }
        else {
            errorHeureDebutVide.setText("");
            //on verifie si c'est un entier
            try {
                Integer.parseInt(heureDebut.getText());
                if (Integer.parseInt(heureDebut.getText()) < 0 || Integer.parseInt(heureDebut.getText()) > 23) {
                    errorHeureDebut.setText(">0&<23");
                    nbErreur++;
                }
                else{
                    errorHeureDebut.setText("");
                }
            }
            catch (NumberFormatException e) {
                errorHeureDebut.setText("Veuillez renseigner un entier");
                nbErreur++;

            }
        }

        if (heureFinString.isEmpty()) {
            errorHeureFinVide.setText("Veuillez renseigner une heure");
        }
        else {
            errorHeureFinVide.setText("");
            //on verifie si c'est un entier
            try {
                Integer.parseInt(heureFin.getText());
                if (Integer.parseInt(heureFin.getText()) < 0 || Integer.parseInt(heureFin.getText()) > 23) {
                    errorHeureFin.setText(">0&<23");
                }
                else{
                    errorHeureFin.setText("");
                }
            }
            catch (NumberFormatException e) {
                errorHeureFin.setText("Veuillez renseigner un entier");
                nbErreur++;
            }
        }

        if (minuteDebutString.isEmpty()) {
            errorMinuteDebut.setText("Veuillez renseigner une minute");
            nbErreur++;
        }
        else {
            errorMinuteDebut.setText("");
            //on verifie si c'est un entier
            try {
                Integer.parseInt(minuteDebut.getText());
                if (Integer.parseInt(minuteDebut.getText()) < 0 || Integer.parseInt(minuteDebut.getText()) > 59) {
                    errorMinuteDebut.setText(">0&<59");
                    nbErreur++;
                }
                else{
                    errorMinuteDebut.setText("");
                }
            }
            catch (NumberFormatException e) {
                errorMinuteDebut.setText("Veuillez renseigner un entier");
                nbErreur++;
            }
        }

        if (minuteFinString.isEmpty()) {
            errorMinuteFin.setText("Veuillez renseigner une minute");
            nbErreur++;
        }
        else {
            errorMinuteFin.setText("");
            //on verifie si c'est un entier
            try {
                Integer.parseInt(minuteFin.getText());
                if (Integer.parseInt(minuteFin.getText()) < 0 || Integer.parseInt(minuteFin.getText()) > 59) {
                    errorMinuteFin.setText(">0&<59");
                    nbErreur++;
                }
                else{
                    errorMinuteFin.setText("");
                }
            }
            catch (NumberFormatException e) {
                errorMinuteFin.setText("Veuillez renseigner un entier");
                nbErreur++;
            }
        }

        if (sallesList.getSelectionModel().getSelectedItem() == null) {
            errorSalle.setText("Veuillez selectionner une salle");
            nbErreur++;
        }
        else {
            errorSalle.setText("");
        }


        //on verifie que les crenaux ne se chevauchent pas
        if (!heureDebutString.isEmpty() && !heureFinString.isEmpty() && !minuteDebutString.isEmpty() && !minuteFinString.isEmpty() && dateDebut.getValue() != null && sallesList.getSelectionModel().getSelectedItem() != null) {

            int heureDebutInt = Integer.parseInt(heureDebutString) * 60 + Integer.parseInt(minuteDebutString);
            int heureFinInt = Integer.parseInt(heureFinString) * 60 + Integer.parseInt(minuteFinString);
            if (heureDebutInt >= heureFinInt) {
                errorHeureDebut.setText("L'heure de debut doit etre inferieur a l'heure de fin");
                nbErreur++;
            } else {
                errorHeureDebut.setText("");
                for (Creneau elem : listeCreneau.getListeCreneau()){
                    if (elem.getJour().equals(dateDebut.getValue()) && elem.getSalle().equals(sallesList.getSelectionModel().getSelectedItem())){
                        //on verifie que les creneaux ne se chevauchent pas
                        if(heureDebutInt >= elem.getMinuteDebut() && heureDebutInt <= elem.getMinuteFin()){
                            errorSalle.setText("Il y a deja un creneau dans cette salle a cette date");
                            nbErreur++;
                        }
                        else{
                            errorSalle.setText("");
                        }
                    }
                    else{
                        errorSalle.setText("");
                    }
                }

            }
        }
        //on va verifier que pour la date selectionner il n'y a pas deja un creneau avec la meme salle
        //on va recuperer la liste des creneaux
/*
        List<Creneau> listCreneau = listeCreneau.getListeCreneau();
*/

        //on boucle sur la liste des creneaux jusqu'a trouver un creneau avec la meme date et la meme salle

        return nbErreur;
    }

    private void clearFields(){
        dateDebut.setValue(null);
        sallesList.getSelectionModel().clearSelection();
        heureDebut.clear();
        heureFin.clear();
        minuteDebut.clear();
        minuteFin.clear();
    }

    private void clearErrorMessages(){
        errorDate.setText("");
        errorMinuteFin.setText("");
        errorMinuteDebut.setText("");
        errorHeureDebut.setText("");
        errorHeureFin.setText("");
        errorSalle.setText("");
        errorHeureDebutVide.setText("");
        errorHeureFinVide.setText("");
    }
}
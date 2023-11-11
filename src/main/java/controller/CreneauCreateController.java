package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
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


    List<String> listeNomResponsable = new ArrayList<>();
    List<UniteEnseignement> listeUe = new ArrayList<>();

    //on va cree une array avec la lsite des utilisateurs dans lequelle on va ajouter les utilisateurs déjà cree
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorDate.setText("");
        errorMinuteFin.setText("");
        errorMinuteDebut.setText("");
        errorHeureDebut.setText("");
        errorHeureFin.setText("");
        errorSalle.setText("");
        errorHeureDebutVide.setText("");
        errorHeureFinVide.setText("");

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
        //on va verif que chaque champ est rempli correctement sinon on met a jour les messages d'erreur

        if (dateDebut.getValue() == null) {
            errorDate.setText("Veuillez renseigner une date");
        }
        else {
            LocalDate ld = dateDebut.getValue();
            System.out.println(ld);
            Calendar c =  Calendar.getInstance();
            c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
            Date date = c.getTime();
            System.out.println(date);
        }
        String heureDebutString = this.heureDebut.getText();
        String heureFinString = this.heureFin.getText();
        String minuteDebutString = this.minuteDebut.getText();
        String minuteFinString = this.minuteFin.getText();

        if (heureDebutString.isEmpty()) {
            errorHeureDebutVide.setText("Veuillez renseigner une heure");
        }
        else {
            //on verifie si c'est un entier
            try {
                Integer.parseInt(heureDebut.getText());
                if (Integer.parseInt(heureDebut.getText()) < 0 || Integer.parseInt(heureDebut.getText()) > 23) {
                    errorHeureDebut.setText(">0&<23");
                }
            }
            catch (NumberFormatException e) {
                errorHeureDebut.setText("Veuillez renseigner un entier");
            }
        }

        if (heureFinString.isEmpty()) {
            errorHeureFinVide.setText("Veuillez renseigner une heure");
        }
        else {
            //on verifie si c'est un entier
            try {
                Integer.parseInt(heureFin.getText());
                if (Integer.parseInt(heureFin.getText()) < 0 || Integer.parseInt(heureFin.getText()) > 23) {
                    errorHeureFin.setText(">0&<23");
                }
            }
            catch (NumberFormatException e) {
                errorHeureFin.setText("Veuillez renseigner un entier");
            }
        }

        if (minuteDebutString.isEmpty()) {
            errorMinuteDebut.setText("Veuillez renseigner une minute");
        }
        else {
            //on verifie si c'est un entier
            try {
                Integer.parseInt(minuteDebut.getText());
                if (Integer.parseInt(minuteDebut.getText()) < 0 || Integer.parseInt(minuteDebut.getText()) > 59) {
                    errorMinuteDebut.setText(">0&<59");
                }
            }
            catch (NumberFormatException e) {
                errorMinuteDebut.setText("Veuillez renseigner un entier");
            }
        }

        if (minuteFinString.isEmpty()) {
            errorMinuteFin.setText("Veuillez renseigner une minute");
        }
        else {
            //on verifie si c'est un entier
            try {
                Integer.parseInt(minuteFin.getText());
                if (Integer.parseInt(minuteFin.getText()) < 0 || Integer.parseInt(minuteFin.getText()) > 59) {
                    errorMinuteFin.setText(">0&<59");
                }
            }
            catch (NumberFormatException e) {
                errorMinuteFin.setText("Veuillez renseigner un entier");
            }
        }

        if (sallesList.getSelectionModel().getSelectedItem() == null) {
            errorSalle.setText("Veuillez selectionner une salle");
        }
        else {
            errorSalle.setText("");
        }
    }

}
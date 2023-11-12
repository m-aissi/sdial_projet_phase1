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

    SessionSingleton listeSession = SessionSingleton.getInstance();
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
        for (Creneau creneauTest : creneau.getListeCreneau()) {
            Boolean creneauExiste = false;
            //on verifie que le creneau n'est pas deja utiliser dans une session
            for (Session session : listeSession.getListSession()) {
                for(Creneau creneauSession : session.getCreneau()){
                    System.out.println(creneauSession.toString());
                    System.out.println("salut" + creneauTest.toString());
                    if( creneauTest.toString().equals(creneauSession.toString())){
                        creneauExiste = true;
                    }
                }
            }
            if(!creneauExiste) {
                creneauList.getItems().add(creneauTest.toString());
            }
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

            //on init les valeurs pour cree session
            UniteEnseignement ueTmp = new UniteEnseignement();
            Promotion promoTmp = new Promotion();
            List<Creneau> listeCreneauTmp = new ArrayList<>();

            //on boucle sur la liste des Ue la liste des promo et enfin la liste des creau pour que eds que un to string soit egale on ajoute l'objet
           for(UniteEnseignement ue : ues.getListeUe()){
               if(ue.toString().equals(nomUe)){
                   ueTmp = ue;
                   break;
               }
           }

           for(Promotion promo : promotions.getListePromotions()) {
               if (promo.getNameProm().equals(nomPromo)) {
                   promoTmp = promo;
                   break;
               }
           }

           for(Creneau creneau : creneau.getListeCreneau()) {
               for (String creneauString : listeCreneau) {
                   if (creneau.toString().equals(creneauString)) {
                       listeCreneauTmp.add(creneau);
                       break;
                   }
               }
           }
            //on cree la session
            listeSession.setLastSessionTouched(new Session(promoTmp, ueTmp, listeCreneauTmp));
            listeSession.creation("src/main/resources/datas/session.txt");
            onReturnButtonClick();
        }
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
            errorListUe.setText("Veuillez selectionner une ue");
            nbErreur++;
        }
        else {
            errorListUe.setText("");
        }

        if (promoList.getSelectionModel().getSelectedItem() == null) {
            errorPromo.setText("Veuillez selectionner une promo");
            nbErreur++;
        }
        else {
            errorPromo.setText("");
        }

        if (selectedCreneauList.getItems().isEmpty()) {
            errorCreneau.setText("Veuillez selectionner un creneau");
            nbErreur++;
        }
        else {
            errorCreneau.setText("");
        }

        return nbErreur;
    }
}
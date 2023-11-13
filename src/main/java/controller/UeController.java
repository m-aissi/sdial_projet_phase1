package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.UEsSingleton;
import model.UniteEnseignement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.ListView;
import java.util.List;
import java.util.ArrayList;
public class UeController implements Initializable {
    @FXML
    private ListView<String> ueList;

    @FXML
    private Button returnButton;

    UEsSingleton listeUe = UEsSingleton.getInstance();

    List<UniteEnseignement> listeDesUes = new ArrayList<>();

    //on va cree une array avec la lsite des utilisateurs dans lequelle on va ajouter les utilisateurs déjà cree
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            listeUe.lecture("/datas/ue.txt");
            System.out.println("lecture");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        listeDesUes = listeUe.getListeUe();

        for (UniteEnseignement elem : listeDesUes){
            System.out.println(elem.toString());
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
            root = FXMLLoader.load(getClass().getResource("ue-create-view.fxml"));
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
            //on recupere la string
            String selectedUe = ueList.getSelectionModel().getSelectedItem();
            //on recupere la chaine de caractere avant le premier espace
            String codeUe = selectedUe.substring(0, selectedUe.indexOf(" "));
            //on cherche dans la lsite des ues le ue qui à le meme code que codeUe afin de le supprimer
            for (UniteEnseignement elem : listeDesUes) {
                String codeTmp = elem.getCode().replaceAll("\\P{Print}", "");
                if (codeTmp.equals(codeUe)) {
                    listeUe.setLastUeTouched(elem);
                    break;
                }
            }

            ueList.getItems().remove(selectedIdx);
            listeUe.suppression("src/main/resources/datas/ue.txt");

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

    public void remplirListeUe() {
        //on va afficher la liste des ues dans la liste des ues
        //on va afficher les promotions dans la liste des promotions
        //on va afficher les ues dans la liste des ues
    }
}
package controller;

import model.DataSingleton;
import model.Enseignant;
import model.Etudiant;
import model.FileEncrypt;

import model.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogController {
    @FXML
    private Label welcomeText;


    @FXML
    private Button logButton;
    @FXML
    private TextField loginBox;
    @FXML
    private PasswordField passwordBox;
    //on cree une array vide prete a recevoir les utilisateurs
    //on cree un etudiant
    public static Etudiant ed = new Etudiant("bobby", "bob", "bobglocc", "hacked", "email@email.com", 123456);
    //on cree un professeur
       private final Enseignant prof1 = new Enseignant("nom", "prenom", "2smart", "4u", "prof@prof.prof");
    //on cree une liste d'utilisateurs
    List<Utilisateur> scList = new ArrayList<Utilisateur>();

    DataSingleton data = DataSingleton.getInstance();

    //on va cree une array avec la lsite des utilisateurs dans lequelle on va ajouter les utilisateurs déjà cree
    public void loadUsers() {
        scList.add(ed);
        scList.add(prof1);


    }

    @FXML
    protected void onLogButtonClick() throws IOException {

        for (Utilisateur elem : scList){

            //on recup les login et password de la liste en retirat les caracteres non printable
            String logTmp = elem.getLogin().replaceAll("\\P{Print}", "");
            String passwdTmp = elem.getPassword().replaceAll("\\P{Print}", "");
            String loginEntered = loginBox.getText().replaceAll("\\P{Print}", "");
            String passwordEntered = passwordBox.getText().replaceAll("\\P{Print}", "");

                if (logTmp.equals(loginEntered) && passwdTmp.equals(passwordEntered)) {
                //La combinaison log/password est valide on va donc stocker le statut de l'utilisateur dans le singleton
                //on cree un utilisateur temporaire pour pouvoir le comparer avec le type de l'utilisateur
                Etudiant tmp2 = new Etudiant();
                String statut;
                //si l'utilisateur à la meme classe que l'utilisateur temporaire alors c'est un etudiant
                if (tmp2.getClass().getName() == elem.getClass().getName()) {
                    statut = "etudiant";
                } else {
                    statut = "professeur";
                }
                //on met a jour le statut de l'utilisateur dans le singleton
                data.setStatut(statut);
                //on change de scene une fois qu'on a defini le statut de l'utilisateur
                Stage stage = (Stage) logButton.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass ().getResource("dashboard-view.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(root));
                break;
            } else {
                welcomeText.setText("login ou mot de passe incorrecte");
            }
        }
    }
}
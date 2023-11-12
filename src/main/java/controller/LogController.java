package controller;

import model.*;

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


    DataSingleton data = DataSingleton.getInstance();

    UsersSingleton users = UsersSingleton.getInstance();

    List<Utilisateur> usersList = new ArrayList<>();

    @FXML
    protected void onLogButtonClick() throws IOException {
        users.lecture("/datas/users.txt");
        usersList = users.getListeUsers();

        for (Utilisateur elem : usersList){

            String logTmp = elem.getLogin().replaceAll("\\P{Print}", "");
            String passwdTmp = elem.getPassword().replaceAll("\\P{Print}", "");
            String loginEntered = loginBox.getText().replaceAll("\\P{Print}", "");
            String passwordEntered = passwordBox.getText().replaceAll("\\P{Print}", "");

                if (logTmp.equals(loginEntered) && passwdTmp.equals(passwordEntered)) {
                //on cree un utilisateur temporaire pour pouvoir le comparer avec le type de l'utilisateur
                Etudiant tmp2 = new Etudiant();
                String statut;
                //si l'utilisateur à la meme classe que l'utilisateur temporaire alors c'est un etudiant il ne peut aps se co
                if (tmp2.getClass().getName() == elem.getClass().getName()) {
                    statut = "etudiant";
                } else {
                    statut = "professeur";


                    //on met a jour le statut de l'utilisateur dans le singleton
                    data.setStatut(statut);
                    //on change de scene une fois qu'on a defini le statut de l'utilisateur
                    Stage stage = (Stage) logButton.getScene().getWindow();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("dashboard-view.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage.setTitle("Dashboard");
                    stage.setScene(new Scene(root));
                    break;
                }
            } else {
                welcomeText.setText("login ou mot de passe incorrecte");
            }
        }
    }


}
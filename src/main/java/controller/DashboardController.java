package controller;

import model.DataSingleton;
import model.Enseignant;
import model.Etudiant;
import model.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class DashboardController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField loginBox;
    @FXML
    private PasswordField  passwordBox;
    //on cree une array vide prete a recevoir les utilisateurs
    //on cree un etudiant
    public static Etudiant ed = new Etudiant("bobby", "bob", "bobglocc", "hacked", "email@email.com", 123456);
    //on cree un professeur
    private final Enseignant prof1 = new Enseignant("nom", "prenom", "2smart", "4u", "prof@prof.prof");
    //on cree une liste d'utilisateurs
    List<Utilisateur> scList = new ArrayList<Utilisateur>();


    DataSingleton data = DataSingleton.getInstance();
    //on va cree une array avec la lsite des utilisateurs dans lequelle on va ajouter les utilisateurs déjà cree
    public void loadUsers   () {
        scList.add(ed);
        scList.add(prof1);
    }

    @FXML
    protected void onLogButtonClick() {
        loadUsers();
        scList.forEach(elem -> {
            String logTmp = elem.getLogin().replaceAll("\\P{Print}","");
            String passwdTmp = elem.getPassword().replaceAll("\\P{Print}","");
            String loginEntered = loginBox.getText().replaceAll("\\P{Print}","");
            String passwordEntered = passwordBox.getText().replaceAll("\\P{Print}","");

            System.out.println("login entered: " + loginEntered);
            System.out.println("password entered: " + passwordEntered);
            System.out.println("login: " + logTmp);
            System.out.println("password: " + passwdTmp);
            System.out.println("zebi zebo log et log :" +  logTmp.compareTo(loginEntered));
            if (logTmp.equals(loginEntered) && passwdTmp.equals(passwordEntered)) {
                System.out.println("login et mot de passe correcte");
                Enseignant tmp = new Enseignant();
                Etudiant tmp2 =  new Etudiant();
                String statut;
                if(tmp2.getClass().getName() == elem.getClass().getName()) {
                    statut = "etudiant";
                }
                else {
                    statut = "professeur";
                }
                welcomeText.setText("login ou mot de passe correcte. Vous etes : " + statut);


            }
            else {
                welcomeText.setText("login ou mot de passe incorrecte");

                System.out.println("login ou mot de passe incorrecte zebiiiiiiiiiiiii");
            }
        });


    }
}
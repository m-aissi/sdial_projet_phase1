package edt.sdialprojet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    //on cree une array vide prete a recevoir les utilisateurs
    private Utilisateur[] utilisateurs = new Utilisateur[10];
    //on cree un etudiant
    private Etudiant etudiant1 = new Etudiant("bobby", "bob", "bobglocc", "hacked", "email@email.com", 123456);
    //on cree un professeur

    @FXML
    protected void onHelloButtonClick() {

        welcomeText.setText("login ou mot de passe incorrecte");
    }
}
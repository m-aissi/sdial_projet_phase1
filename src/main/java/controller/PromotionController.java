package controller;

import model.DataSingleton;
import model.Etudiant;
import model.Promotion;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
// import model.PromotionSingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;


public class PromotionController implements Initializable {

    //initialisation des variable JAVAFX
    @FXML
    private TextField addPBox;

    @FXML
    private ListView<String> listPromo;

    @FXML
    private ListView<String> listEleve;

    @FXML
    private ListView<String> listDisp;

    @FXML
    private Button modButton;

    @FXML
    private Button supprPButton;

    @FXML
    private Button addPButton;

    @FXML
    private Button supprButton;

    @FXML
    private Button addButton;

    @FXML
    private Button returnButton;

    //Variables de tests
    public static Etudiant etudiant1 = new Etudiant("Michael", "Dupont", "a", "a", "a", 0);
    public static Etudiant etudiant2 = new Etudiant("Roger", "Franc", "a", "a", "a", 1);
    public static Etudiant etudiant3 = new Etudiant("Fabrice", "Blabla", "a", "a", "a", 2);
    public static Etudiant etudiant4 = new Etudiant("Pascal", "Ehhh", "a", "a", "a", 3);
    public static Etudiant etudiant5 = new Etudiant("Charles", "Uhhh", "a", "a", "a", 4);
    public static Promotion IATIC3 = new Promotion("IATIC3", new ArrayList<Etudiant>(Arrays.asList(etudiant1)));
    public static Promotion IATIC4 = new Promotion("IATIC4", new ArrayList<Etudiant>(Arrays.asList(etudiant2)));
    public static Promotion IATIC5 = new Promotion("IATIC5", new ArrayList<Etudiant>(Arrays.asList(etudiant3)));
    public static Promotion noProm = new Promotion( "NoProm", new ArrayList<Etudiant>(Arrays.asList(new Etudiant[]{etudiant4,etudiant5})));
    public static List<Promotion> Promotions = new ArrayList<Promotion>(Arrays.asList(new Promotion[]{IATIC3,IATIC4,IATIC5}));
    public static boolean isStartedEdit = false;


    // PromotionSingleton listePromo = PromotionSingleton.getInstance();

    //varibles des Promo/etudiants sélectionnées pour modif
    private String currentPromoName;
    public static Promotion currentPromo;
    private String currentEtudAName;
    public static Etudiant currentEtudA;
    private String currentEtudSName;
    public static Etudiant currentEtudS;

    DataSingleton data = DataSingleton.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        // try {
        //     listePromo.lecture("/datas/promo.txt");
        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // }

        //on refresh la liste des promo
        refreshViewPromoList();

        //desactive tout les bouton
        modButton.setDisable(true);
        supprPButton.setDisable(true);
        addPButton.setDisable(true);
        supprButton.setDisable(true);
        addButton.setDisable(true);

        //refresh la liste des eleve dispo
        refreshViewPromo(noProm);

        //fonction listener de la textbox
        addPBox.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("")){
                //si rien bouton desactivé
                addPButton.setDisable(true);
            }else{
                //bouton activé
                addPButton.setDisable(false);
            }
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
        });

        //listener de la liste des promo
        listPromo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                //on recupe le nom de la promo + active les bouton mod et supr de la promo
                currentPromoName = listPromo.getSelectionModel().getSelectedItem();
                System.out.println(currentPromoName+" selected");
                supprPButton.setDisable(false);
                modButton.setDisable(false);
            }
        });

        //listener de la liste des étudiant de la promo
        listEleve.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                //on recup le nom de l'élève + active le bouton supr
                currentEtudSName = listEleve.getSelectionModel().getSelectedItem();
                System.out.println(currentEtudSName+" selected");
                supprButton.setDisable(false);
            }
        });

        //listener de la liste des étudiant dispo
        listDisp.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                //on recup le nom de l'élève + active le bouton add
                currentEtudAName = listDisp.getSelectionModel().getSelectedItem();
                System.out.println(currentEtudAName+" selected");
                if(!(noProm.getEtudiants() == null) && isStartedEdit){
                    if(!(noProm.getEtudiants().isEmpty())){
                        // if(!(listePromo.getListePromotions().isEmpty())){
                        if(!(Promotions.isEmpty())){
                            addButton.setDisable(false);
                        }
                    }
                }

            }
        });

    }

    //fonction bouton mod
    @FXML
    protected void onModButtonClick() {
        //si la liste des étudiant libre est pas vide on active le bouton ajout
        if(!(noProm.getEtudiants() == null)){
            if(!(noProm.getEtudiants().isEmpty())){
                addButton.setDisable(false);
                isStartedEdit = true ;
            }
        }
        System.out.println("My current promo is :"+currentPromoName);
        //on cherche la promo dans la liste et on la met en tant que promo selectionée
        // for (Promotion promotion : listePromo.getListePromotions()) {
        for (Promotion promotion : Promotions) {
            if(currentPromoName.equals(promotion.getNameProm())){
                currentPromo = promotion;
            }
        }

        //affichage du contenu de la promo
        System.out.println("try to show for :"+currentPromoName);
        refreshViewPromo(currentPromo);
    }
    //fonction bouton add
    @FXML
    protected void onAddButtonClick() {
        //on cherche l'étudiant dans les étudiant libre et on le met étudiant selectionné
        System.out.println("My etudiant promo is :"+currentEtudAName);
        for (Etudiant etudiant : noProm.getEtudiants()) {
            if(currentEtudAName.equals(etudiant.getNomPrenom())){
                currentEtudA = etudiant;
            }
        }
        //on l'ajoute a la promo
        currentPromo.addEtudiants(currentEtudA);
        //refresh des etudiant de la promo
        refreshViewPromo(currentPromo);
        //on l'enleve des etudiant libre
        noProm.getEtudiants().remove(currentEtudA);
        //refresh dess etudiant libres
        refreshViewPromo(noProm);
        //on re-desac le bouton add au cas ou
        addButton.setDisable(true);
    }
    //fonction bouton suppr
    @FXML
    protected void onSupprButtonClick() {
        //on cherche l'étudiant dans la promo et on le met étudiant selectionné
        System.out.println("My etudiant promo is :"+currentEtudSName);
        for (Etudiant etudiant : currentPromo.getEtudiants()) {
            if(currentEtudSName.equals(etudiant.getNomPrenom())){
                currentEtudS = etudiant;
            }
        }
        //on l'ajoute aux étudiant libre
        noProm.addEtudiants(currentEtudS);
        //refresh des etudiant libres
        refreshViewPromo(noProm);
        //on l'enleve de la promo
        currentPromo.getEtudiants().remove(currentEtudS);
        //refresh dess etudiant de la promo
        refreshViewPromo(currentPromo);
        //on re-desac le bouton suppr au cas ou
        supprButton.setDisable(true);
    }
    //fonction bouton add (Promotion)
    @FXML
    protected void onAddPButtonClick() {
        //recup de la boite texte
        String AddPromName = addPBox.getText();
        System.out.println(AddPromName);
        //on crée une new promo vide avec le nom
        // listePromo.getListePromotions().add(new Promotion(AddPromName, null));
        Promotions.add(new Promotion(AddPromName, null));
        //on vide la box
        addPBox.clear();
        //refresh de la liste promo
        refreshViewPromoList();
    }
    //function bouton retour
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
    //fonction bouton suppr (Promotion)
    @FXML
    protected void onSupprPButtonClick() {
        System.out.println("My current promo is :"+currentPromoName);
        System.out.println("try to suprr for :"+currentPromoName);
        //on cherche la promo dans la liste des promo
        // for (Promotion promotion : listePromo.getListePromotions()) {
        for (Promotion promotion : Promotions) {
            if(currentPromoName.equals(promotion.getNameProm())){
                currentPromo = promotion;
            }
        }
        //si la promo elle est pas vide
        if(!(currentPromo.getEtudiants() == null)){
            System.out.println("mettre un dispo les etudiant");
            //on prends tout les étudiant de la promo
            for (Etudiant etudiant : currentPromo.getEtudiants()) {
                //on les met en dispo
                noProm.addEtudiants(etudiant);
            }
            //on vide la promo
            currentPromo.setEtudiants(null);
        }
        //refresh des etudiants dispo
        refreshViewPromo(noProm);

        //on enleve la promo de la liste des promo
        // listePromo.getListePromotions().remove(currentPromo);
        Promotions.remove(currentPromo);
        //on vide le visu de la liste des eleve de la promo (ya pu la promo dcp)
        listEleve.getItems().clear();
        //desac le bouton
        supprButton.setDisable(true);
        //refresh de la liste des promo
        refreshViewPromoList();
        //re-desac le bouton mod et suppr
        modButton.setDisable(true);
        supprPButton.setDisable(true);

    }
    //fonction refresh de la vue des élèves d'une promo
    private void refreshViewPromo(Promotion promo){
        //si c'est pas null
        if(!(promo.getEtudiants() == null)){
            System.err.println("reafficher");
            //on créer une array liste de string ou on va mettre les noms
            List<String> names = new ArrayList<String>();
            for (Etudiant etudiant : promo.getEtudiants() ){
                //on y met tout les nomprenom des etudiant de la promo
                names.add(etudiant.getNomPrenom());
            }
            //transforme en Observable list
            ObservableList<String> promosObs = FXCollections.observableArrayList(names);
            //si c'est la liste des eleve dispo ou pas, on va pas afficher au meme endroit
            if (promo.getNameProm().equals("NoProm")) {
                listDisp.getItems().clear();
                listDisp.setItems(promosObs);
            }else{
                listEleve.getItems().clear();
                listEleve.setItems(promosObs);
            }
        }else{
            //sinon on clear la vue
            listEleve.getItems().clear();
        }
    }
    //fonction refresh de la vue des promos
    private void refreshViewPromoList(){
        //array list des nom de promo
        List<String> promos = new ArrayList<String>();
        // for (Promotion promotion : listePromo.getListePromotions()) {
        for (Promotion promotion : Promotions) {
            promos.add(promotion.getNameProm());
        }
        ObservableList<String> promosObs = FXCollections.observableArrayList(promos);
        //affiche dans la liste promo
        listPromo.setItems(promosObs);
    }


}

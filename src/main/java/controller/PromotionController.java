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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;


public class PromotionController implements Initializable {

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

    private String currentPromoName;
    public static Promotion currentPromo;
    private String currentEtudAName;
    public static Etudiant currentEtudA;
    private String currentEtudSName;
    public static Etudiant currentEtudS;
    
    DataSingleton data = DataSingleton.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        refreshViewPromoList();
        
        modButton.setDisable(true);
        supprPButton.setDisable(true);
        addPButton.setDisable(true);
        supprButton.setDisable(true);
        addButton.setDisable(true);

        refreshViewPromo(noProm);

        addPBox.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("")){
                addPButton.setDisable(true);
            }else{
                addPButton.setDisable(false);
            }
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
        });

        listPromo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				currentPromoName = listPromo.getSelectionModel().getSelectedItem();
                System.out.println(currentPromoName+" selected");
                supprPButton.setDisable(false);
                modButton.setDisable(false);
			}	
		});

        listEleve.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                currentEtudSName = listEleve.getSelectionModel().getSelectedItem();
                System.out.println(currentEtudSName+" selected");
                supprButton.setDisable(false);
			}	
		});

        listDisp.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				currentEtudAName = listDisp.getSelectionModel().getSelectedItem();
				System.out.println(currentEtudAName+" selected");
                if(!(noProm.getEtudiants() == null) && isStartedEdit){
                    if(!(noProm.getEtudiants().isEmpty())){
                        addButton.setDisable(false);
                    }
                }

			}	
		});
        
    }

    @FXML
    protected void onModButtonClick() {
        if(!(noProm.getEtudiants() == null)){
            if(!(noProm.getEtudiants().isEmpty())){
                addButton.setDisable(false);
                isStartedEdit = true ;
            }
        }
		System.out.println("My current promo is :"+currentPromoName);
        for (Promotion promotion : Promotions) {
            if(currentPromoName.equals(promotion.getNameProm())){
                currentPromo = promotion;
            }
        }

        System.out.println("try to show for :"+currentPromoName);
        refreshViewPromo(currentPromo);
    }

    @FXML
    protected void onAddButtonClick() {
        System.out.println("My etudiant promo is :"+currentEtudAName);
        for (Etudiant etudiant : noProm.getEtudiants()) {
            if(currentEtudAName.equals(etudiant.getNomPrenom())){
                currentEtudA = etudiant;
            }
        }
        currentPromo.addEtudiants(currentEtudA);

        refreshViewPromo(currentPromo);

        noProm.getEtudiants().remove(currentEtudA);
        
        refreshViewPromo(noProm);

        addButton.setDisable(true);
    }

    @FXML
    protected void onSupprButtonClick() {
        System.out.println("My etudiant promo is :"+currentEtudSName);
        for (Etudiant etudiant : currentPromo.getEtudiants()) {
            if(currentEtudSName.equals(etudiant.getNomPrenom())){
                currentEtudS = etudiant;
            }
        }
        noProm.addEtudiants(currentEtudS);

        refreshViewPromo(noProm);

        currentPromo.getEtudiants().remove(currentEtudS);

        refreshViewPromo(currentPromo);

        supprButton.setDisable(true);
    }

    @FXML
    protected void onAddPButtonClick() {
        String AddPromName = addPBox.getText();
        System.out.println(AddPromName);
        Promotions.add(new Promotion(AddPromName, null));
        addPBox.clear();

        refreshViewPromoList();
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

    @FXML
    protected void onSupprPButtonClick() {
        System.out.println("My current promo is :"+currentPromoName);
        System.out.println("try to suprr for :"+currentPromoName);
        for (Promotion promotion : Promotions) {
            if(currentPromoName.equals(promotion.getNameProm())){
                currentPromo = promotion;
            }
        }

        if(!(currentPromo.getEtudiants() == null)){
            System.out.println("mettre un dispo les etudiant");
            for (Etudiant etudiant : currentPromo.getEtudiants()) {
                noProm.addEtudiants(etudiant);
            }
        }

        refreshViewPromo(noProm);

        Promotions.remove(currentPromo);
        listEleve.getItems().clear();
        supprButton.setDisable(true);
        
        refreshViewPromoList();
        
        modButton.setDisable(true);
        supprPButton.setDisable(true);

    }

    private void refreshViewPromo(Promotion promo){
        if(!(noProm.getEtudiants() == null)){
            System.err.println("reafficher");
            List<String> names = new ArrayList<String>();
            for (Etudiant etudiant : promo.getEtudiants() ){
                names.add(etudiant.getNomPrenom());
            }
            ObservableList<String> promosObs = FXCollections.observableArrayList(names);

            if (promo.getNameProm().equals("NoProm")) {
                listDisp.getItems().clear();
                listDisp.setItems(promosObs);
            }else{
                listEleve.getItems().clear();
                listEleve.setItems(promosObs);
            }
        }
    }

    private void refreshViewPromoList(){
        List<String> promos = new ArrayList<String>();
        for (Promotion promotion : Promotions) {
            promos.add(promotion.getNameProm());
        }
        ObservableList<String> promosObs = FXCollections.observableArrayList(promos);
        listPromo.setItems(promosObs);
    }

    
}

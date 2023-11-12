package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PromotionSingleton implements FileHandler{
    private List<Promotion> listePromotions = new ArrayList<>();
    private static final PromotionSingleton instance = new PromotionSingleton();
    private Promotion lastPromotionTouched;
    private Boolean isInit = false;
    UsersSingleton listeUsers = UsersSingleton.getInstance();
    private PromotionSingleton() {}

    public static PromotionSingleton getInstance() {
        return instance;
    }

    @Override
    public void lecture(String nomFichier) throws IOException {
        if (isInit) {
            return;
        }
        isInit = true;

        String filePath = nomFichier;

        // Open the file located in the resource and read it
        InputStream is = PromotionSingleton.class.getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        List<Etudiant> listeEtudiant = new ArrayList<>();
        // Iterate through each line of the file
        while ((line = br.readLine()) != null) {
            // Split the line into elements
            String[] elements = line.split(":");

            //pour chaque element on va chercher dans la liste des utilisateurs si un user avec le meme login existe
            //pour chaque elements faire
            int cpt= 0;
            for (String elem : elements){
                if(cpt == 0){
                    cpt++;
                    continue;
                }

                //on va chercher dans la liste des utilisateurs si un user avec le meme login existe
                for (Utilisateur user : listeUsers.getListeUsers()){
                    if (user.getLogin().equals(elem)){
                        listeEtudiant.add((Etudiant) user);
                    }
                }
            }
            //on ajoute la liste etudiante à la promo
            listePromotions.add(new Promotion(elements[0], listeEtudiant));
            // You can add additional else-if conditions for different cases if needed
        }
        br.close(); // Close the BufferedReader when done
    }


    @Override
    public void creation(String nomFichier) {
       /** try (FileWriter fw = new FileWriter(nomFichier, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            // Write the lastUserTouched object to the end of the file
            out.println(lastUserTouched.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }**/
    }


    public List<Promotion> getListePromotions() {
        return listePromotions;
    }

    public void setListePromotions(List<Promotion> listePromotions) {
        this.listePromotions = listePromotions;
    }

    public Promotion getLastPromotionTouched() {
        return lastPromotionTouched;
    }

    public void setLastPromotionTouched(Promotion lastPromotionTouched) {
        this.lastPromotionTouched = lastPromotionTouched;
    }

    public Boolean getInit() {
        return isInit;
    }

    public void setInit(Boolean init) {
        isInit = init;
    }

    public UsersSingleton getListeUsers() {
        return listeUsers;
    }

    public void setListeUsers(UsersSingleton listeUsers) {
        this.listeUsers = listeUsers;
    }

    @Override
    public void modification(String nomFichier) {

    }

    @Override
    public void suppression(String nomFichier) {

    }
}

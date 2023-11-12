package model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

 public class SessionSingleton implements FileHandler {
     private List<Creneau> listeCreneau = new ArrayList<>();
     private static final SessionSingleton instance = new SessionSingleton();
     private Creneau lastCreneauTouched;

     private Boolean isInit = false;
     private SessionSingleton() {
     }

     public static SessionSingleton getInstance() {
         return instance;
     }

     @Override
     public void lecture(String nomFichier) throws IOException {
         if (isInit) {
             return;
         }
         isInit = true;
         //on init la liste des creneaux
         listeCreneau =  new ArrayList<>();

         // on ouvre le fichier en lecure
         InputStream is = SessionSingleton.class.getResourceAsStream(nomFichier);
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         String line;

         // Iterate through each line of the file
         while ((line = br.readLine()) != null) {
             // Split the line into elements
             String[] elements = line.split(":");
             // on cree un creneau avec les elements
             Creneau creneau = new Creneau(elements[0], LocalDate.parse(elements[1]), Integer.parseInt(elements[2]), Integer.parseInt(elements[3]));
             listeCreneau.add(creneau);
         }
         br.close(); // Close the BufferedReader when done
     }

     @Override
     public void creation(String nomFichier) {
         // on va ajouter lastUeTouched de la liste des UE
         listeCreneau.add(lastCreneauTouched);

         // et on reecri dans le fichier nomfichier la liste avec lastUeTouched
         try (FileWriter fw = new FileWriter(nomFichier, false);
              BufferedWriter bw = new BufferedWriter(fw);
              PrintWriter out = new PrintWriter(bw)) {

             //on ecrit chaque ligne dans le fichier et si c'est la derniere ligne on fait print ou lieu de println
             for (Creneau elem : listeCreneau){
                 if (listeCreneau.indexOf(elem) == listeCreneau.size() - 1){
                     out.print(elem.toFile());
                 } else {
                     out.println(elem.toFile());
                 }
             }

         } catch (IOException e) {
             e.printStackTrace();
         }
     }


     @Override
     public void modification(String nomFichier) {

     }

     @Override
     public void suppression(String nomFichier) {
         // on va retirer lastUeTouched de la liste des UE
         listeCreneau.remove(lastCreneauTouched);

         // et on reecri dans le fichier nomfichier la liste sans lastUeTouched
         try (FileWriter fw = new FileWriter(nomFichier, false);
              BufferedWriter bw = new BufferedWriter(fw);
              PrintWriter out = new PrintWriter(bw)) {

             //on ecrit chaque ligne dans le fichier et si c'est la derniere ligne on fait print ou lieu de println
            for (Creneau elem : listeCreneau){
                if (listeCreneau.indexOf(elem) == listeCreneau.size() - 1){
                    out.print(elem.toFile());
                } else {
                    out.println(elem.toFile());
                }
            }

         } catch (IOException e) {
             e.printStackTrace();
         }

     }

     public List<Creneau> getListeCreneau() {
         return listeCreneau;
     }

     public void setListeCreneau(List<Creneau> listeCreneau) {
         this.listeCreneau = listeCreneau;
     }

     public Creneau getLastCreneauTouched() {
         return lastCreneauTouched;
     }

     public void setLastCreneauTouched(Creneau lastCreneauTouched) {
         this.lastCreneauTouched = lastCreneauTouched;
     }

     public Boolean getInit() {
         return isInit;
     }

     public void setInit(Boolean init) {
         isInit = init;
     }
 }

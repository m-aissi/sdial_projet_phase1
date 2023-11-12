package model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

 public class SessionSingleton implements FileHandler {
     private List<Session> listSession = new ArrayList<>();
     private static final SessionSingleton instance = new SessionSingleton();
     private Session lastSessionTouched;
     UsersSingleton listeUsers = UsersSingleton.getInstance();
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
         listSession =  new ArrayList<>();

         // on ouvre le fichier en lecure
         InputStream is = SessionSingleton.class.getResourceAsStream(nomFichier);
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         String line;

         // Iterate through each line of the file
         while ((line = br.readLine()) != null) {
             UniteEnseignement nouvelleUe = new UniteEnseignement();
             Promotion nouvellePromo = new Promotion();
             List<Creneau> listeCreneau = new ArrayList<>();

             // Split the line into elements
             String[] elements = line.split(";");
             int cpt = 0;
             for (String elem : elements){
                 if(cpt == 0){
                     cpt++;
                     List<Etudiant> listeEtudiant = new ArrayList<>();
                     String[] promo = elem.split(":");
                     int cpt2= 0;
                     for (String promoElem : promo){
                         if(cpt2 == 0){
                             cpt2++;
                             continue;
                         }
                         //on va chercher dans la liste des utilisateurs si un user avec le meme login existe
                         for (Utilisateur user : listeUsers.getListeUsers()){
                             if (user.getLogin().equals(promoElem)){
                                 listeEtudiant.add((Etudiant) user);
                             }
                         }
                     }
                     //on ajoute la liste etudiante à la promo
                     nouvellePromo= new Promotion(promo[0], listeEtudiant);
                 }
                 else if(cpt == 1){
                     cpt++;
                     //on cree l'ue
                     String[] ue = elem.split(":");
                     //on cherche dans la liste des users le user qui à le meme login que elements[3] afin de l'ajouter à un nouveau enseignat
                     UsersSingleton users = UsersSingleton.getInstance();
                     List<Utilisateur> usersList = users.getListeUsers();
                     //on boucle jusqu'à trouver le bon user
                     for (Utilisateur elem2 : usersList) {
                         String logTmp = elem2.getLogin().replaceAll("\\P{Print}", "");
                         if (logTmp.equals(ue[3])) {
                             Enseignant prof = new Enseignant(elem2.getNom(), elem2.getPrenom(), elem2.getLogin(), elem2.getPassword(), elem2.getEmail());
                             nouvelleUe = new UniteEnseignement(ue[0], ue[1], Integer.parseInt(ue[2]), prof, Integer.parseInt(ue[4]));
                             break;
                         }
                     }

                 }
                 else{
                     String[] cren = elem.split(":");
                     // on cree un creneau avec les elements
                     Creneau creneau = new Creneau(cren[0], LocalDate.parse(cren[1]), Integer.parseInt(cren[2]), Integer.parseInt(cren[3]));
                     listeCreneau.add(creneau);
                 }
                 //on verifie si c'est le premier element
             }
            listSession.add(new Session(nouvellePromo,nouvelleUe , listeCreneau));
         }
         br.close(); // Close the BufferedReader when done
     }

     @Override
     public void creation(String nomFichier) {
         // on va ajouter lastUeTouched de la liste des UE
         listSession.add(lastSessionTouched);

         // et on reecri dans le fichier nomfichier la liste avec lastUeTouched
         try (FileWriter fw = new FileWriter(nomFichier, false);
              BufferedWriter bw = new BufferedWriter(fw);
              PrintWriter out = new PrintWriter(bw)) {

             //on ecrit chaque ligne dans le fichier et si c'est la derniere ligne on fait print ou lieu de println
             for (Session elem : listSession){

                 String promTmp = "";
                 promTmp += elem.getPromo().getNameProm() + ":";
                 for(Etudiant etudiant :  elem.getPromo().getEtudiants()){
                     //si c'est le dernier element on ne print pas :
                        if(elem.getPromo().getEtudiants().indexOf(etudiant) == elem.getPromo().getEtudiants().size() - 1){
                            promTmp += etudiant.getLogin();
                        } else {
                            promTmp += etudiant.getLogin() + ":";
                        }
                 }


                 String ueTmp = elem.getUE().toFile();
                 String creneauTmp = "";

                 for(Creneau creneau : elem.getCreneau()){
                     creneauTmp += creneau.toFile() + ";";
                 }

                 String res = promTmp + ";" + ueTmp + ";" + creneauTmp;

                 if (listSession.indexOf(elem) == listSession.size() - 1){
                     out.print(res);
                 } else {
                     out.println(res);
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

         try (FileWriter fw = new FileWriter(nomFichier, false);
              BufferedWriter bw = new BufferedWriter(fw);
              PrintWriter out = new PrintWriter(bw)) {

             //on ecrit chaque ligne dans le fichier et si c'est la derniere ligne on fait print ou lieu de println
             for (Session elem : listSession){

                 String promTmp = "";
                 promTmp += elem.getPromo().getNameProm() + ":";
                 for(Etudiant etudiant :  elem.getPromo().getEtudiants()){
                     //si c'est le dernier element on ne print pas :
                     if(elem.getPromo().getEtudiants().indexOf(etudiant) == elem.getPromo().getEtudiants().size() - 1){
                         promTmp += etudiant.getLogin();
                     } else {
                         promTmp += etudiant.getLogin() + ":";
                     }
                 }


                 String ueTmp = elem.getUE().toFile();
                 String creneauTmp = "";

                 for(Creneau creneau : elem.getCreneau()){
                     creneauTmp += creneau.toFile() + ";";
                 }

                 String res = promTmp + ";" + ueTmp + ";" + creneauTmp;

                 if (listSession.indexOf(elem) == listSession.size() - 1){
                     out.print(res);
                 } else {
                     out.println(res);
                 }
             }

         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public List<Session> getListSession() {
         return listSession;
     }

     public void setListSession(List<Session> listSession) {
         this.listSession = listSession;
     }

     public Session getLastSessionTouched() {
         return lastSessionTouched;
     }

     public void setLastSessionTouched(Session lastSessionTouched) {
         this.lastSessionTouched = lastSessionTouched;
     }

     public UsersSingleton getListeUsers() {
         return listeUsers;
     }

     public void setListeUsers(UsersSingleton listeUsers) {
         this.listeUsers = listeUsers;
     }

     public Boolean getInit() {
         return isInit;
     }

     public void setInit(Boolean init) {
         isInit = init;
     }
 }

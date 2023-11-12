package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UEsSingleton implements FileHandler {
    private List<UniteEnseignement> listeUe = new ArrayList<>();
    private static final UEsSingleton instance = new UEsSingleton();
    private UniteEnseignement lastUeTouched;

    private Boolean isInit = false;
    private UEsSingleton() {
    }

    public static UEsSingleton getInstance() {
        return instance;
    }

    @Override
    public void lecture(String nomFichier) throws IOException {
        if (isInit) {
            return;
        }
        isInit = true;
        String filePath = nomFichier;
        listeUe =  new ArrayList<>();
        // Open the file located in the resource and read it
        InputStream is = FileEncrypt.class.getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;

        // Iterate through each line of the file
        while ((line = br.readLine()) != null) {
            // Split the line into elements
            String[] elements = line.split(":");

            //on cherche dans la liste des users le user qui à le meme login que elements[3] afin de l'ajouter à un nouveau enseignat
            UsersSingleton users = UsersSingleton.getInstance();
            List<Utilisateur> usersList = users.getListeUsers();
            //on boucle jusqu'à trouver le bon user
            for (Utilisateur elem : usersList) {
                String logTmp = elem.getLogin().replaceAll("\\P{Print}", "");
                if (logTmp.equals(elements[3])) {
                    Enseignant prof = new Enseignant(elem.getNom(), elem.getPrenom(), elem.getLogin(), elem.getPassword(), elem.getEmail());
                    UniteEnseignement ue = new UniteEnseignement(elements[0], elements[1], Integer.parseInt(elements[2]), prof, Integer.parseInt(elements[4]));
                    listeUe.add(ue);
                    break;
                }
            }
        }
        br.close(); // Close the BufferedReader when done
    }

    @Override
    public void creation(String nomFichier) {
        // on va ajouter lastUeTouched de la liste des UE
        listeUe.add(lastUeTouched);

        // et on reecri dans le fichier nomfichier la liste avec lastUeTouched
        try (FileWriter fw = new FileWriter(nomFichier, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            //on ecrit chaque ligne dans le fichier et si c'est la derniere ligne on fait print ou lieu de println
            for (UniteEnseignement elem : listeUe){
                if (listeUe.indexOf(elem) == listeUe.size() - 1){
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
        listeUe.remove(lastUeTouched);
        //open the file in filepath and write "lol" and erase everything

        // et on reecri dans le fichier nomfichier la liste sans lastUeTouched
        try (FileWriter fw = new FileWriter(nomFichier, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            //on ecrit chaque ligne dans le fichier et si c'est la derniere ligne on fait print ou lieu de println
           for (UniteEnseignement elem : listeUe){
               if (listeUe.indexOf(elem) == listeUe.size() - 1){
                   out.print(elem.toFile());
               } else {
                   out.println(elem.toFile());
               }
           }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<UniteEnseignement> getListeUe() {
        return listeUe;
    }

    public void setListeUe(List<UniteEnseignement> listeUe) {
        this.listeUe = listeUe;
    }

    public UniteEnseignement getLastUeTouched() {
        return lastUeTouched;
    }

    public void setLastUeTouched(UniteEnseignement lastUeTouched) {
        this.lastUeTouched = lastUeTouched;
    }
}

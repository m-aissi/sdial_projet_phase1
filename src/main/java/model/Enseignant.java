package model;

public class Enseignant extends Utilisateur{


    //on cree le constructeur
    public Enseignant(String nom, String prenom, String login, String password, String email) {
        super(nom, prenom, login, password, email);
    }

    public Enseignant() {

    }


    public void supprimerUE(UniteEnseignement[] listeUE, UniteEnseignement ue) {
        for (int i = 0; i < listeUE.length; i++) {
            if (listeUE[i] == ue) {
                listeUE[i] = null;
            }
        }
    }

    /*
    public void modifierUE(UniteEnseignement ue, String nom, int nbHeures, String nomResponsable, int creditECTS) {
        ue.setNom(nom);
        ue.setNbHeures(nbHeures);
        ue.setNomResponsable(nomResponsable);
        ue.setCreditECTS(creditECTS);
    }*/
}
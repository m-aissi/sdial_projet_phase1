package edt.sdialprojet;

import java.util.List;

public class Enseignant extends Utilisateur{

    //on cree le constructeur
    public Enseignant(String nom, String prenom, String login, String password, String email) {
        super(nom, prenom, login, password, email);
    }
    public UniteEnseignement creerUE(List<UniteEnseignement> liste, String code, String nom, int nbHeures, String nomResponsable, int creditECTS) {
        return new UniteEnseignement(liste, code, nom, nbHeures, nomResponsable, creditECTS);
    }

    public boolean doesExist(String nom, String prenom, List<Enseignant> listeEnseignant){
        boolean reply = false;
        
        if(listeEnseignant != null){
            for (Enseignant enseignant : listeEnseignant) {
                if (nom != enseignant.getNom() && prenom != enseignant.getPrenom()) {
                    reply = true;
                    return reply;
                }
            }
        }
    
        return reply;
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
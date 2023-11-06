package edt.sdialprojet;

public class Enseignant extends Utilisateur{

    public UniteEnseignement creerUE(String nom, int nbHeures, String nomResponsable, int creditECTS) {
        return new UniteEnseignement(nom, nbHeures, nomResponsable, creditECTS);
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
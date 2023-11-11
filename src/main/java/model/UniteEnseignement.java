package model;

import java.util.List;

public class UniteEnseignement {
    //on déclare les attributs de la classe
    private String code;
    private String nom;
    private int nbHeures;
    private Enseignant responsable;
    private int creditECTS;
    //ajouter d'autre attributs ?


    //on déclare le constructeur de la classe
    public UniteEnseignement(String code, String nom, int nbHeures, Enseignant responsable, int creditECTS) {
        this.code = code;
        this.nom = nom;
        this.nbHeures = nbHeures;
        this.responsable = responsable;
        this.creditECTS = creditECTS;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean doesExist(String code, List<UniteEnseignement> listeUe){

        if(listeUe != null){
            for (UniteEnseignement uniteEnseignement : listeUe) {

                if (code.replaceAll("\\P{Print}", "") == uniteEnseignement.getCode().replaceAll("\\P{Print}", "")) {
                    return true;
                }
            }
        }

        return false;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbHeures() {
        return nbHeures;
    }

    public void setNbHeures(int nbHeures) {
        this.nbHeures = nbHeures;
    }

    public Enseignant getNomResponsable() {
        return responsable;
    }

    public void setNomResponsable(Enseignant nomResponsable) {
        this.responsable = nomResponsable;
    }

    public int getCreditECTS() {
        return creditECTS;
    }

    public void setCreditECTS(int creditECTS) {
        this.creditECTS = creditECTS;
    }

    //on cree le to string
    @Override
    public String toString() {
        return
                 code + " " +
                 nom + " - " +
                 nbHeures +'h' + " | "+
                 responsable.getNom() + " | " +
                 creditECTS + " ECTS" + '\n';
    }
}

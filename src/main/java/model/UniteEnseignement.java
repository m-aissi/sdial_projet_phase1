package model;


public class UniteEnseignement {
    //on déclare les attributs de la classe
    private String nom;
    private int nbHeures;
    private String nomResponsable;
    private int creditECTS;
    //ajouter d'autre attributs ?


    //on déclare le constructeur de la classe
    public UniteEnseignement(String nom, int nbHeures, String nomResponsable, int creditECTS) {
        this.nom = nom;
        this.nbHeures = nbHeures;
        this.nomResponsable = nomResponsable;
        this.creditECTS = creditECTS;
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

    public String getNomResponsable() {
        return nomResponsable;
    }

    public void setNomResponsable(String nomResponsable) {
        this.nomResponsable = nomResponsable;
    }

    public int getCreditECTS() {
        return creditECTS;
    }

    public void setCreditECTS(int creditECTS) {
        this.creditECTS = creditECTS;
    }
}

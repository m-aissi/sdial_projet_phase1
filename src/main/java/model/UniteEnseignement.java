package model;

import java.util.List;

public class UniteEnseignement {
    //on déclare les attributs de la classe
    private String code;
    private String nom;
    private int nbHeures;
    private String nomResponsable;
    private int creditECTS;
    //ajouter d'autre attributs ?


    //on déclare le constructeur de la classe
    public UniteEnseignement(String code, String nom, int nbHeures, String nomResponsable, int creditECTS) {
        this.code = code;
        this.nom = nom;
        this.nbHeures = nbHeures;
        this.nomResponsable = nomResponsable;
        this.creditECTS = creditECTS;
    }

    public boolean doesExist(String code, List<UniteEnseignement> listeUe){
        boolean reply = false;
        
        if(listeUe != null){
            for (UniteEnseignement uniteEnseignement : listeUe) {
                if (code == uniteEnseignement.code) {
                    reply = true;
                    return reply;
                }
            }
        }
        
        return reply;
    }

    public boolean modifyUE(UniteEnseignement UE, String code, String nom, int nbHeures, String nomResponsable, int creditECTS){
        //ne pas oublier de mettre un doesExist avant pour tester les modifs
        boolean reply = false;
        
        if(UE != null){
            UE.setCode(code);
            UE.setNom(nom);
            UE.setNbHeures(nbHeures);
            UE.setNomResponsable(nomResponsable);
            UE.setCreditECTS(creditECTS);
            reply = true;
            return reply;
        }
        
        return reply;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
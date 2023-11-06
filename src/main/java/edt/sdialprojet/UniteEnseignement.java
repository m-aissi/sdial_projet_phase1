package edt.sdialprojet;
import java.util.*;

public class UniteEnseignement {
    //on déclare les attributs de la classe
    private String code;
    private String nom;
    private int nbHeures;
    private String nomResponsable;
    private int creditECTS;
    //ajouter d'autre attributs ?


    //on déclare le constructeur de la classe
    public UniteEnseignement(List<UniteEnseignement> liste, String code, String nom, int nbHeures, String nomResponsable, int creditECTS) {
        if(!doesExist(code, liste)){
            this.code = code;
            this.nom = nom;
            this.nbHeures = nbHeures;
            this.nomResponsable = nomResponsable;
            this.creditECTS = creditECTS;
        }else{
            System.out.println("existe déjà");
        }
    }

    public boolean doesExist(String code, List<UniteEnseignement> listeUe){
        boolean reply = false;
        
        if(listeUe != null){
            for (UniteEnseignement uniteEnseignement : listeUe) {
                if (code == uniteEnseignement.code) {
                    reply = true;
                }
            }
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


    // public static void main(String[] args) {
    //     //UniteEnseignement[] liste = new UniteEnseignement[UniteEnseignement.size()];
    //     List<UniteEnseignement> liste = new ArrayList<UniteEnseignement>();
    //     UniteEnseignement UE1 = new UniteEnseignement(liste,"AHH","bHH",2, "A",2);
    //     System.out.println(UE1.getCode());
    //     System.out.println(UE1.getNom());
    //     System.out.println(UE1.getNomResponsable());

    //     liste.add(UE1);
    //     UniteEnseignement UE2 = new UniteEnseignement(liste,"AHH","bHH",2, "A",2);
    //     System.out.println(UE2.getCode());
    //     System.out.println(UE2.getNom());
    //     System.out.println(UE2.getNomResponsable());

    //     UniteEnseignement UE3 = new UniteEnseignement(liste,"AHH2","beeHH",2, "A",2);
    //     System.out.println(UE3.getCode());
    //     System.out.println(UE3.getNom());
    //     System.out.println(UE3.getNomResponsable());
    // }
}


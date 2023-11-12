package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Promotion{
    private String nameProm;
    List<Etudiant> etudiants;

    public Promotion() {
    }

    public Promotion(String nameProm, List<Etudiant> etudiants) {
        this.nameProm = nameProm;
        this.etudiants = etudiants;
    }
    public String getNameProm() {
        return nameProm;
    }
    public void setNameProm(String nameProm) {
        this.nameProm = nameProm;
    }
    public List<Etudiant> getEtudiants() {
        return etudiants;
    }
    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
    public void addEtudiants(Etudiant etudiant){
        if(this.etudiants == null){
            this.etudiants = new ArrayList<Etudiant>(Arrays.asList(etudiant));
        }else{
            this.etudiants.add(etudiant);
        }
    }
    

    
}

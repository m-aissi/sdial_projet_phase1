package model;
import java.util.*;

public class Creneau{
    Date dateDebut;
    Date dateFin;
    int dureeMinute;
    UniteEnseignement Matiere;
    Enseignant Prof;
    List<Etudiant> Promo;
    int salleNum;
    
    public Creneau(Date dateDebut, Date dateFin, int dureeMinute, UniteEnseignement matiere, Enseignant prof,
            List<Etudiant> promo, int salleNum) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dureeMinute = dureeMinute;
        Matiere = matiere;
        Prof = prof;
        Promo = promo;
        this.salleNum = salleNum;
    }

    public boolean doesExist(int salleNum, Date dateDebut, Date dateFin, List<Creneau> listeCreneau){
        boolean reply = false;
        
        if(listeCreneau != null){
            for (Creneau creneau : listeCreneau) {
                if (salleNum != creneau.salleNum && (dateDebut.after(creneau.dateFin) || dateFin.before(creneau.dateDebut))) {
                    reply = true;
                    return reply;
                }
            }
        }
        
        return reply;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getDureeMinute() {
        return dureeMinute;
    }

    public void setDureeMinute(int dureeMinute) {
        this.dureeMinute = dureeMinute;
    }

    public UniteEnseignement getMatiere() {
        return Matiere;
    }

    public void setMatiere(UniteEnseignement matiere) {
        Matiere = matiere;
    }

    public Enseignant getProf() {
        return Prof;
    }

    public void setProf(Enseignant prof) {
        Prof = prof;
    }

    public List<Etudiant> getPromo() {
        return Promo;
    }

    public void setPromo(List<Etudiant> promo) {
        Promo = promo;
    }

    public int getSalleNum() {
        return salleNum;
    }

    public void setSalleNum(int salleNum) {
        this.salleNum = salleNum;
    }

    

}

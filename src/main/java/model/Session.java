package model;

import java.time.LocalDate;
import java.util.List;

public class Session {

    private UniteEnseignement UE;
    private Promotion promo;
    private List<Creneau> creneau;

    public Session( Promotion promo, UniteEnseignement UE, List<Creneau> creneau) {
        this.UE = UE;
        this.promo = promo;
        this.creneau = creneau;
    }

    public UniteEnseignement getUE() {
        return UE;
    }

    public void setUE(UniteEnseignement UE) {
        this.UE = UE;
    }

    public Promotion getPromo() {
        return promo;
    }

    public void setPromo(Promotion promo) {
        this.promo = promo;
    }

    public  List<Creneau> getCreneau() {
        return creneau;
    }

    public void setCreneau( List<Creneau> creneau) {
        this.creneau = creneau;
    }

    public String toFile(){
/*        String res = "";
        res += promo.getNom() + ":";
        res += UE.getNom() + ":";
        res += UE.getNbHeures() + ":";
        res += UE.getResponsable().getLogin() + ":";
        res += UE.getNbGroupes() + ":";
        for (Creneau elem : creneau){
            res += elem.toFile() + "|";
        }
        res += "\n";
        return res;*/
        return "";
    }

    @Override
    public String toString(){
        String res = "";
        res += "Promotion : " + promo.getNameProm();
        res += "UE : " + UE.getNom();
        res += "Liste des creneaux : ";
        for (Creneau elem : creneau){
            res += elem.toString() + "&";
        }
        return res;
    }



}

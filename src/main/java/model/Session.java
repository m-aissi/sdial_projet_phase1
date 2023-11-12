package model;

import java.time.LocalDate;

public class Session {

    private UniteEnseignement UE;
    private Promotion promo;
    private Creneau creneau;

    public Session(UniteEnseignement UE, Promotion promo, Creneau creneau) {
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

    public Creneau getCreneau() {
        return creneau;
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }
}

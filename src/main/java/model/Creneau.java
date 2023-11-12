package model;

import java.time.LocalDate;

public class Creneau {

    private String Salle;
    private LocalDate Jour;
    private int minuteDebut;
    private int minuteFin;

    public Creneau(String salle, LocalDate jour, int minuteDebut, int minuteFin) {
        Salle = salle;
        Jour = jour;
        this.minuteDebut = minuteDebut;
        this.minuteFin = minuteFin;
    }

    public String getSalle() {
        return Salle;
    }

    public void setSalle(String salle) {
        Salle = salle;
    }

    public LocalDate getJour() {
        return Jour;
    }

    public void setJour(LocalDate jour) {
        Jour = jour;
    }

    public int getMinuteDebut() {
        return minuteDebut;
    }

    public void setMinuteDebut(int minuteDebut) {
        this.minuteDebut = minuteDebut;
    }

    public int getMinuteFin() {
        return minuteFin;
    }

    public void setMinuteFin(int minuteFin) {
        this.minuteFin = minuteFin;
    }

    @Override
    public String toString(){
        int heureDebut = minuteDebut/60;
        int minuteDebuts = minuteDebut%60;
        int heureFin = minuteFin/60;
        int minuteFins = minuteFin%60;

        return Salle + " - " + Jour + " " + heureDebut + ":" + minuteDebuts + " - " + heureFin + ":" + minuteFins;
    }

    public String toFile(){
        return Salle + ":" + Jour + ":" + minuteDebut + ":" + minuteFin;
    }
}

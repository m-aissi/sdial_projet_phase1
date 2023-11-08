package model;

import java.util.List;

public class Etudiant extends Utilisateur{

    private int numeroEtudiant;

    public Etudiant() {

    }

    public boolean doesExist(String login, List<Etudiant> listeEtudiant){
        boolean reply = false;
        
        if(listeEtudiant != null){
            for (Etudiant etudiant : listeEtudiant) {
                if (login == etudiant.getLogin()) {
                    reply = true;
                    return reply;
                }
            }
        }
    
        return reply;
    }

    public int getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(int numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public Etudiant(int numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public Etudiant(String nom, String prenom, String login, String password, String email, int numeroEtudiant) {
        super(nom, prenom, login, password, email);
        this.numeroEtudiant = numeroEtudiant;
    }
}

package model;

public class Etudiant extends Utilisateur{

    private int numeroEtudiant;

    public Etudiant() {

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

    /**
     *
     * @param nom
     * @param prenom
     * @param login
     * @param password
     * @param email
     * @param numeroEtudiant
     */
    public Etudiant(String nom, String prenom, String login, String password, String email, int numeroEtudiant) {
        super(nom, prenom, login, password, email);
        this.numeroEtudiant = numeroEtudiant;
    }
}

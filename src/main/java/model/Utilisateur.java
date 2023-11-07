package model;

public abstract class Utilisateur {
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private String email;

    //constructeur vide

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String login, String password, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void seConnecter() {
        System.out.println("Connexion de " + this.login);
    }
    public void seDeconnecter() {
        System.out.println("Déconnexion de " + this.login);
    }
}

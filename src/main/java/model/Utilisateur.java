package model;

import java.util.List;

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

    public boolean doesExist(String nom, String prenom, String login, String email, List<Utilisateur> listeUtilisateur){
        boolean reply = false;
        
        if(listeUtilisateur != null){
            for (Utilisateur utilisateur : listeUtilisateur) {
                if ((nom == utilisateur.getNom() && prenom == utilisateur.getPrenom()) || login == utilisateur.getLogin() || email == utilisateur.getLogin()) {
                    reply = true;
                    return reply;
                }
            }
        }
    
        return reply;
    }

    public boolean modifyUser(Utilisateur user, String nom, String prenom, String login, String password, String email){
        //ne pas oublier de mettre un doesExist avant pour tester les modifs
        boolean reply = false;
        
        if(user != null){
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            reply = true;
            return reply;
        }
        
        return reply;
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

    public String getNomPrenom(){
        return nom+" "+prenom;
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

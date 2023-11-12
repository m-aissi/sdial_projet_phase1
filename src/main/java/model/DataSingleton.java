package model;

public class DataSingleton {

    private String statut;
    private static final DataSingleton instance = new DataSingleton();

    private DataSingleton() {}

    public static DataSingleton getInstance() {
        return instance;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

}

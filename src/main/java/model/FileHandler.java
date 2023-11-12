package model;

import java.io.IOException;

public interface FileHandler {
    public void lecture(String nomFichier) throws IOException;
    public void creation(String nomFichier);
    public void modification(String nomFichier);
    public void suppression(String nomFichier);
}

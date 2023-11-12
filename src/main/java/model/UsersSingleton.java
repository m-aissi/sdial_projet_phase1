package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsersSingleton implements FileHandler{
    private List<Utilisateur> listeUsers = new ArrayList<>();
    private static final UsersSingleton instance = new UsersSingleton();
    private Utilisateur lastUserTouched;

    private UsersSingleton() {}

    public static UsersSingleton getInstance() {
        return instance;
    }

    @Override
    public void lecture(String nomFichier) throws IOException {
        String filePath = nomFichier;

        // Open the file located in the resource and read it
        InputStream is = FileEncrypt.class.getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;

        // Iterate through each line of the file
        while ((line = br.readLine()) != null) {
            // Split the line into elements
            String[] elements = line.split(":");

            // Check if the line has 6 elements (Etudiant)
            if (elements.length == 6) {
                // Create an Etudiant object and add it to the list
                Etudiant etudiant = new Etudiant(elements[0], elements[1], elements[2], elements[3], elements[4], Integer.parseInt(elements[5]));
                listeUsers.add(etudiant);
            } else if (elements.length == 5) {
                // Create an Enseignant object and add it to the list
                Enseignant enseignant = new Enseignant(elements[0], elements[1], elements[2], elements[3], elements[4]);
                listeUsers.add(enseignant);
            }
            // You can add additional else-if conditions for different cases if needed
        }
        br.close(); // Close the BufferedReader when done
    }


    public List<Utilisateur> getListeUsers() {
        return listeUsers;
    }

    public void setListeUsers(List<Utilisateur> listeUsers) {
        this.listeUsers = listeUsers;
    }

    @Override
    public void creation(String nomFichier) {
       /** try (FileWriter fw = new FileWriter(nomFichier, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            // Write the lastUserTouched object to the end of the file
            out.println(lastUserTouched.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }**/
    }


    @Override
    public void modification(String nomFichier) {

    }

    @Override
    public void suppression(String nomFichier) {

    }
}

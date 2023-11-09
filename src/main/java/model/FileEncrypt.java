package model;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.Key;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
public class FileEncrypt {

    public FileEncrypt() {
    }

    public static void encryptFile() {
        try {
            // Check if the encryption key file exists; if not, generate and save it
            if (!keyFileExists()) {
                generateAndSaveKey();
            }

            SecretKey key = loadKey(); // Load the encryption key

            Cipher aesCipher = Cipher.getInstance("AES");


            String filePath = "/datas/users-coy.txt";

            // on ouvre le fichier se trouvant dans ressource et on le lit
            InputStream is = FileEncrypt.class.getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }


            byte[] input = sb.toString().getBytes("UTF-8");

            aesCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] textEncrypted = aesCipher.doFinal(input);

            String s = new String(textEncrypted);
            System.out.println(s);

            aesCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] textDecrypted = aesCipher.doFinal(textEncrypted);

            s = new String(textDecrypted);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateAndSaveKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecretKey key = keyGenerator.generateKey();

            // Save the key to a file
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("encryption_key.dat"))) {
                oos.writeObject(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SecretKey loadKey() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("encryption_key.dat"))) {
            return (SecretKey) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean keyFileExists() {
        File file = new File("encryption_key.dat");
        return file.exists();
    }
}

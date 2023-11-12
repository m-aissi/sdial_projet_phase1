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


            String filePath = "/datas/users.txt";

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
            /**
            // Save the encrypted text to a file
                String pathh = "/datas/users-encrypted.dat";
                File file = new File(FileEncrypt.class.getResourceAsStream(pathh).toString());
                if(!file.exists())
                {
                    boolean created = file.createNewFile();
                }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/datas/users-encrypted.dat"))) {
                oos.writeObject(textEncrypted);
            }

            String filePathh = "/datas/users-encrypted.dat";


            FileInputStream fis = new FileInputStream(filePathh);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object object = ois.readObject();
            **/
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
    private static boolean EncryptedTextExists() {
        File file = new File("/datas/users-encrypted.dat");
        return file.exists();
    }
}

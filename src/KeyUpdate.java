import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.security.KeyStore;

public class KeyUpdate {
    public static void main(String[] args){
        try {
            KeyStore keyStore = KeyStore.getInstance("JCKES");
            keyStore.load(null, null);

            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey newsecretKey = keyGen.generateKey();

            KeyStore.SecretKeyEntry newSecretKey = new KeyStore.SecretKeyEntry(newsecretKey);
            KeyStore.PasswordProtection keyPassword = new KeyStore.PasswordProtection("keyPassword".toCharArray());
            keyStore.setEntry("mySecretKey", newSecretKey, keyPassword);

            try (FileOutputStream fos = new FileOutputStream("keystore.jceks")) {
                keyStore.store(fos, "storePassword".toCharArray());
            }

            System.out.println("Key updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.security.KeyStore;
public class KeyStore_ {
    public static void main(String[] args){
        try {
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, null);

            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();

            KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
            KeyStore.PasswordProtection keyPassword = new KeyStore.PasswordProtection("keyPassword".toCharArray());

            keyStore.setEntry("mysecretkey", secretKeyEntry, keyPassword);

            try (FileOutputStream fos = new FileOutputStream("keystore,jceks")){
                keyStore.store(fos, "storePassword".toCharArray());
            }

            System.out.println("Key stored successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

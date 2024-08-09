import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.security.KeyStore;

public class KeyUsage {
    public static void main(String[] args){
        try{
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            try (FileInputStream fis = new FileInputStream("keystore.jceks")) {
                keyStore.load(fis, "storePassword".toCharArray());
            }

            KeyStore.SecretKeyEntry keyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry("mySecretKey", new KeyStore.PasswordProtection("keyPasswoed".toCharArray()));
            SecretKey secretKey = keyEntry.getSecretKey();

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] plaintext = "Hello,world".getBytes();
            byte[] ciphertext = cipher.doFinal(plaintext);

            System.out.println("Ciphertext (base64):" + java.util.Base64.getEncoder().encodeToString(ciphertext));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

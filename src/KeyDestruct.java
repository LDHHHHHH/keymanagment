import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;

/**
 * @Author zhaozhenlin
 * @Date: 2024/08/09
 * @description
 */
public class KeyDestruct {
    public static void main(String[] args) {
        try {
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            try (FileInputStream fis = new FileInputStream("keystore.jceks")) {
                keyStore.load(fis, "storePassword".toCharArray());
            }

            keyStore.deleteEntry("mySecretkey");

            try (FileOutputStream fos = new FileOutputStream("keystore.jceks")) {
                keyStore.store(fos, "storePassword".toCharArray());
            }

            System.out.println("Key deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

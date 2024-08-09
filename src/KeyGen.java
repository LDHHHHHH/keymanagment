import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class KeyGen {
    public static void main(String[] args){
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);

            SecretKey secretKey = keyGen.generateKey();

            System.out.println("Generated key:" + secretKey);

        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


    }
}

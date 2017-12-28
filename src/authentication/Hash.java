package authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de manejar el Hash de datos.
 *
 * @author emanuel
 */
public class Hash {

    private static final Logger logger = Logger.getLogger(Hash.class.getName());

    private Hash() {

    }

    /**
     * Hashea una contraseña
     *
     * @param password
     * @return
     */
    public String toHash(String password, String salt) {
        try {
            password.concat(salt);
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes());
            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuilder hashCodeBuffer = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                hashCodeBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            return hashCodeBuffer.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    /**
     * Retorna una instancia del Hash.
     */
    public static Hash getInstance() {
        return HashHolder.INSTANCE;
    }

    private static class HashHolder {

        private static final Hash INSTANCE = new Hash();
    }
}

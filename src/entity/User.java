package entity;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * This class contains the login details of the User
 */
public class User implements Serializable {
    /**
     * The serialisation version number
     */
    private static final long serialVersionUID = 651561618416161L;
    /**
     * The username of the User
     */
    private String username;
    /**
     * The hashed password of the User
     */
    private String passwordHashString;

    /**
     * This constructor creates a {@code User} object with username and password provided
     * 
     * @param username the username of the User
     * @param password the password of the User
     */
    public User(String username, String password) {
        this.username = username;
        this.passwordHashString = toHash(password, username);
    }

    /**
     * This method hashes passwords using PBKDF2 algorithm
     * 
     * @param password the password to be hashed
     * @param username the username of the User
     * @return the hashed password 
     */
    public static String toHash(String password, String username) {
        byte[] saltBytes = hashPassword(password.toCharArray(), username.getBytes(), 10000, 512);
        return Hex.encodeHexString(saltBytes);
    }

    /**
     * This method hashes passwords
     * 
     * @param password   the password to be hashed
     * @param salt       the salt to be used
     * @param iterations the number of iterations to be used
     * @param keyLength  the length of the key to be used
     * @return the hashed password
     */
    public static byte[] hashPassword(final char[] password, final byte[] salt, final int iterations,
            final int keyLength) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            return res;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method gets the username of the User
     * 
     * @return the username of the User
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * This method gets the hashed password of the User
     * 
     * @return the hashed password of the User
     */
    public String getPasswordHashString() {
        return this.passwordHashString;
    }

    /**
     * This method is for the user to change password
     * 
     * @param password the new password of the User
     */
    public void setPasswordHashString(String password) {
        this.passwordHashString = toHash(password, this.username);
    }

}

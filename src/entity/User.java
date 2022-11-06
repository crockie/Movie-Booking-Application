package entity;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;

public class User implements Serializable{
    /**
     * The serialisation version number
     */
    private static final long serialVersionUID = 651561618416161L;
    private String username;
    private String passwordHashString;

    public User(String username, String password) {
        this.username = username;
        this.passwordHashString = toHash(password, username);
    }

    public static String toHash(String password, String username) {
        byte[] saltBytes = hashPassword(password.toCharArray(), username.getBytes(), 10000, 512);
        return Hex.encodeHexString(saltBytes);
    }

    public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

    public String getUsername(){
        return this.username;
    }

    public String getPasswordHashString(){
        return this.passwordHashString;
    }

    public void setPasswordHashString(String password){
        this.passwordHashString = toHash(password, this.username);
    }

    public void setUsername(String username){
        this.username = username;
    }

}

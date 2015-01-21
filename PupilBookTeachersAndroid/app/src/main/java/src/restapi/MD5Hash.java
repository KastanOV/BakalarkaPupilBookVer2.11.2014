package src.restapi;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Topr on 1/21/2015.
 */
public class MD5Hash {
    public static String md5Hash(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] hash = md.digest(message.getBytes("UTF-8"));
            //converting byte array to Hexadecimal
            StringBuilder sb = new StringBuilder(2*hash.length);
            for(byte b : hash){ sb.append(String.format("%02x", b&0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException ex) {
            System.out.printf(ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            System.out.printf(ex.getMessage());
        }
        return digest; }

}

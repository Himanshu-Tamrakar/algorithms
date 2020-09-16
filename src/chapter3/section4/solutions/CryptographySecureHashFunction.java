package chapter3.section4.solutions;

import edu.princeton.cs.algs4.StdOut;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptographySecureHashFunction {

    /**
     * CryptographySecureHashFunction.main(new String[]{"The quick brown fox jumps over the lazy dog"});
     * @param args
     */
    public static void main(String[] args) {
        String password = args[0];
        MessageDigest sha1;
        byte[] bytes;
        try {
            sha1 = MessageDigest.getInstance("SHA1");
            bytes = sha1.digest(password.getBytes("ISO-8859-1"));
            System.out.printf("%s\n", bytes);

            // convert bytes to hex, careful to handle leading 0s and 2s complement
            String hex = "0123456789abcdef";
            for (int i = 0; i < bytes.length; i++) {
                if (i % 4 == 0) StdOut.print(" ");
                StdOut.print(hex.charAt((bytes[i] & 0xF0) >> 4));
                StdOut.print(hex.charAt((bytes[i] & 0x0F) >> 0));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }




    }
}

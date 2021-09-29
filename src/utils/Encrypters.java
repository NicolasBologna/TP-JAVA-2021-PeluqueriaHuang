package utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Encrypters {

	public static String encriptadoMD5(String pasword) {

        String passEncript = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] encBytes = md.digest(pasword.getBytes());
            BigInteger numero = new BigInteger(1, encBytes);
            passEncript = numero.toString();
            while (passEncript.length() < 32) {
                passEncript = "0" + passEncript;
            }
        } catch (Exception e) {
            System.out.println("ERROR en encriptado");
        }
        return passEncript;
    }
}

package logic;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.*;
import entities.*;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Login {
	private DataPersona dp;
	
	public Login() {
		dp=new DataPersona();
	}
	
	public Persona validate(Persona p) {
		p.setPassword(encriptadoMD5(p.getPassword()));
		return dp.getByUser(p);
	}

	public LinkedList<Persona> getAll(){
		return dp.getAll();
	}

	public Persona getByDocumento(Persona per) {
		return dp.getByDocumento(per);
		
	}
	
	private String encriptadoMD5(String pasword) {

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

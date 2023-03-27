package com.example.ioc;

import java.security.PublicKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorImpl implements Validador{

	public boolean validarDNI(String dni) {
        Pattern patron = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
        Matcher mat = patron.matcher(dni);
	        if(!mat.matches()) {
	        	return false;
	        }
	        else {
				return true;
			}
	   

		}
	

}

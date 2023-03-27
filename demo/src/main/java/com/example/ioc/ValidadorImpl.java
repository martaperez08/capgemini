package com.example.ioc;

import java.security.PublicKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorImpl implements Validador{

	public boolean validarDNI(String nif) {
		
        Pattern pattern=Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher matcher=pattern.matcher(nif);
        if(matcher.matches()){
            String letra=matcher.group(2);
            String letras="TRWAGMYFPDXBNJZSQVHLCKE";
            int index=Integer.parseInt(matcher.group(1));
            index=index%23;
            String reference=letras.substring(index,index+1);
            if(reference.equalsIgnoreCase(letra)){
                return true;
            }else{
            	return false;
            }
        }else{
             return false;
        }
      
	   

		}
	

}

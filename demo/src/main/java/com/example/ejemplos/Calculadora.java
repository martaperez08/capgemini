package com.example.ejemplos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculadora {
	
	public double suma(double a, double b) {
		BigDecimal rslt= BigDecimal.valueOf(a+b);
		return rslt.setScale(15, RoundingMode.HALF_DOWN).doubleValue();
		}
		
		
	
	
	public double dividir(double a, double b) {
		if(b== 0) throw new ArithmeticException("Divide by 0");
		return a/b;
	}
	
	public double dividir(int a, int b) {
		return a/b;
	}
	
	
	
	//lenguaje me proteje si meten letras
}

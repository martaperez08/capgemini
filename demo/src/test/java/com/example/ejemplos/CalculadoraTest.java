package com.example.ejemplos;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.core.test.Smoke;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import lombok.experimental.var;


@TestMethodOrder(value = MethodOrderer.DisplayName.class)
class CalculadoraTest {
	
	// para hacerlo mejor metemos los test en classes 
	Calculadora calc;

	@BeforeEach
	void setUp() throws Exception {
		//Reinstanciamos la calculadora antes de cada test
		calc = new Calculadora();
	}
	
	
	@Nested
	@DisplayName("Pruebas del método Suma")

	class Suma {
		
		@Nested
		class OK {
			
			@Test
			void test_Suma() {
				var rslt = calc.suma(2, 2);
				assertEquals(4, rslt);
			}
			@Smoke
			@Test
			void test_Suma_Positivo_Negativo() {
				var rslt = calc.suma(1, -4);
				assertEquals(-3, rslt);
			}
			
			@Test
			void test_Suma_Negativo_Positivo() {
				var rslt = calc.suma(-1,5);
				assertEquals(4, rslt);
			}
			
			@Test
			void test_Suma_Decimales() {
				var rslt = calc.suma(0.3,0.2);
				assertEquals(0.5, rslt);
			}
			@Test
			@Disabled //dejar de protestar ya lo miramemos mas adelante
			void testSumaMultiple() {
			
				assertEquals(3, calc.suma(1, 1));
				assertEquals(0, calc.suma(-1, 1));
				assertEquals(-2, calc.suma(-1, -1));
				assertEquals(4, calc.suma(1, 3));
				assumeTrue(false,"La tengo a media");// te sale que esta mal pero no rojo
				
			}
			//test parametrizado
			@ParameterizedTest(name= "{0} {1}={2}")
			@CsvSource(value ={"1,1,2","0.1,0.2,0.3","-1,0,-1"})
			void testSumaOK(double a, double b , double rslt) {
				
				assertEquals(rslt, calc.suma(a, b));
			}


			@Test
			void testSumaMock() {
				Calculadora calc= mock(Calculadora.class);//creamos un mock de calcu
				when(calc.suma(anyDouble(), anyDouble())).thenReturn(3.0);
				assertEquals(3, calc.suma(2, 2));
			}
			//por ejemplo aqui 2+2 son 4 pero le indicamos que devuelva 3 y pasa prueba
			
		}
		
		@Nested
		class KO {
			
			@Test
			//g
			void testSumaPositivoNegativo() {
				var rslt = calc.suma(1, -0.9);
				assertEquals(0.1, rslt);
			}
			
			@Test
			void testSumaDecimales() {
				var rslt = calc.suma(0.1,0.2);
				assertEquals(0.3, rslt);
			}
			
		}
		
	}
	
	@Nested
	class Divide {
		@Nested
		class OK {
			
			@Test
			void testDividir() {
				var rslt = calc.dividir(4,2);
				assertEquals(2, rslt);
			}
			
		}
		@Nested
		class KO {
			
			@Test
			void testDividir() {
				//var rslt = calc.divide(1,0);
				//assertEquals(Double.POSITIVE_INFINITY, rslt);
				
				// este seria para el metodo divide con enteros
				assertThrows(ArithmeticException.class, () -> calc.dividir(1, 0));
			}
			
		}
	}
	
}

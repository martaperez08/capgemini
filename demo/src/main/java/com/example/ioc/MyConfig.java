package com.example.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import jakarta.validation.constraints.Max;

@Configuration
public class MyConfig {

	/* como difenciamos cuando tenemos 50 contructores, la solcion es crear metodos que llaman
	 * la instannciacion*/
	@Bean
	
	@Profile("pre") // en apllicacions.propertie
	UnaTonteria unaTonteria() {
		return new UnaTonteria() {
			
			@Override
			public String dimeAlgo() {
				// TODO Auto-generated method stub
				return "Dice una tonteria PREPRODUCTION";
			}
		} ;
		
		
	}
	@Bean
	@Primary
	@Profile("prod")
	UnaTonteria otraTonteria() {
		return new UnaTonteria() {
			
			@Override
			public String dimeAlgo() {
				// TODO Auto-generated method stub
				return "Dice una tonteria Primaria PRODUCTION";
			}
		} ;
	}
	
		
	}
	



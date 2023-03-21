package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Primary

public class StringRepositoryImpl implements StringRepository {

	@Override
	public String load() {
		
		return "Soy StringRepositoruImp";
	}

	@Override
	public void save(String item) {
		System.out.println("Guardo el item"+ item);
	
	}

}

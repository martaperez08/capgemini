package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.exception.ArgumentoInvalidoException;
import com.example.exception.InvalidDataException;

@Repository
@Primary

public class StringRepositoryImpl implements StringRepository {

	@Override
	public String load() {
		
		return "Soy StringRepositoruImp";
	}

	@Override
	public void save(String item) throws InvalidDataException {
		if(item == "")
			//throw new ArgumentoInvalidoException();
			throw new InvalidDataException("La cadera no puede estar vacia");
		System.out.println("Guardo el item"+ item);
	
	}

}

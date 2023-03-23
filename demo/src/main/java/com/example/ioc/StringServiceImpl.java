package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.exception.InvalidDataException;
import com.example.exception.NotFoundException;

@Service
@Qualifier("Local")
public class StringServiceImpl implements StringService{

	//lamamos el repositorio
	private StringRepository dao;
	
	 public StringServiceImpl(StringRepository dao) {
		 
		 this.dao=dao;
	 }
	 
	
	 
	@Override
	public String get(Integer id) {
		return dao.load()+ "\nServicio local";
	}

	@Override
	public void add(String item) throws NotFoundException {
		// TODO Auto-generated method stub
		try {
			dao.save(item);
		} catch (InvalidDataException e ) {
			throw new NotFoundException("No encontrado",e);
		}

		
	}

	@Override
	public void modify(String item)  throws InvalidDataException{
		// TODO Auto-generated method stub
		dao.save(item);
		
	}

	@Override
	public void remove(Integer id)   throws InvalidDataException{
		// TODO Auto-generated method stub
		dao.save(id.toString());
		
	}

}

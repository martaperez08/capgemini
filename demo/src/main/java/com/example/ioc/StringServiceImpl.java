package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
	public void add(String item) {
		// TODO Auto-generated method stub
		dao.save(item);
		
	}

	@Override
	public void modify(String item) {
		// TODO Auto-generated method stub
		dao.save(item);
		
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		dao.save(id.toString());
		
	}

}

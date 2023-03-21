package com.example.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Remoto")
public class StringRemoteServiceImpl implements StringService {
// con eso no podmeo hacer el cotnructor no podremos insertar dataos manualmentr REVISAR
	@Autowired
	private StringRepository dao;

	

		 public StringRemoteServiceImpl(StringRepository dao) {
			 
			 this.dao=dao;
		 }
	@Override
	public String get(Integer id) {
		return dao.load()+ "\nServicio Remoto";
	
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

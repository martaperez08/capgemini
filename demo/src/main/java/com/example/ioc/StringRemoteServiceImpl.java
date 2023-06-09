package com.example.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.exception.InvalidDataException;

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
	public void add(String item) throws InvalidDataException {
		// TODO Auto-generated method stub
		dao.save(item);

	}

	@Override
	public void modify(String item) throws InvalidDataException  {
		// TODO Auto-generated method stub
		dao.save(item);

	}

	@Override
	public void remove(Integer id) throws InvalidDataException  {
		// TODO Auto-generated method stub
		dao.save(id.toString());

	}

}

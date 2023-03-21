package com.example.ioc;

import org.springframework.stereotype.Repository;

@Repository
public class StringRepositoryMockImp  implements StringRepository {

	@Override
	public String load() {
		// TODO Auto-generated method stub
		return "Soy StringRepositoryMockImp";	}

	@Override
	public void save(String item) {
		// TODO Auto-generated method stub
		
	}

}

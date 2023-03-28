package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domains.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	
	
	//cuidado con equivocarse nombre metodos
	
	List<Actor> findTop5ByFirstNameStartingWithOrderByLastName(String prefijo);
	

}

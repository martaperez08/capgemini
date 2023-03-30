package com.example.domains.contracts.service;


import java.sql.Timestamp;
import java.util.List;

import com.example.domains.core.services.contracts.ProjectionDomainService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;

public interface CategoryService extends ProjectionDomainService<Category, Integer> {
	List<Category> orderCategories (String sufijo);

}

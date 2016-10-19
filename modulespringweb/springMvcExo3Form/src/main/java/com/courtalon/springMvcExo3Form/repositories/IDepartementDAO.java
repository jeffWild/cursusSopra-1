package com.courtalon.springMvcExo3Form.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.springMvcExo3Form.metier.Departement;

public interface IDepartementDAO {

	List<Departement> findAll();

	Departement findById(int id);

}
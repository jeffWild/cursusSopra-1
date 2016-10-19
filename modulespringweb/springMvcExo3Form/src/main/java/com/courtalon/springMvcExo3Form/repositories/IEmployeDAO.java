package com.courtalon.springMvcExo3Form.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.springMvcExo3Form.metier.Employe;

public interface IEmployeDAO {

	List<Employe> findAll();
	
	List<Employe> findByDepartement(int did);
	
	Employe findByID(int id);

	Employe save(Employe e);

	boolean remove(int id);

}
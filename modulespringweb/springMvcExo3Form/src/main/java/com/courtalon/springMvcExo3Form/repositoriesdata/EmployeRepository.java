package com.courtalon.springMvcExo3Form.repositoriesdata;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.springMvcExo3Form.metier.Employe;

public interface EmployeRepository extends PagingAndSortingRepository<Employe, Integer>{
	
	Iterable<Employe> findByDepartement_Id(int id);
}

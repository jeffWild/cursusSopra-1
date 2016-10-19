package com.courtalon.springMvcExo3Form.repositoriesdata;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.springMvcExo3Form.metier.Departement;



public interface DepartementRepository
	extends PagingAndSortingRepository<Departement, Integer>,
			DepartementRepositoryCustom
{

}

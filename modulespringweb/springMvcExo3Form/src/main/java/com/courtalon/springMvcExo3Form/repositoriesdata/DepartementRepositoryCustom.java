package com.courtalon.springMvcExo3Form.repositoriesdata;

import com.courtalon.springMvcExo3Form.metier.Departement;

public interface DepartementRepositoryCustom {
	Iterable<Departement> findByNom(String nom);
}

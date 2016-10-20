package com.courtalon.springMvcExo3Form.repositoriesdata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

public class DepartementRepositoryImpl implements DepartementRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	public EntityManager getEm() {return em;}
	public void setEm(EntityManager em) {this.em = em;}


	@Override
	@Transactional(readOnly=true)
	public List<String> getAllDepartementNames() {
		return em.createQuery("select d.nom from Departement as d", String.class)
			     .getResultList();
	}

}

package com.courtalon.springMvcExo3Form.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.springMvcExo3Form.metier.Departement;

public class DepartementDAO implements IDepartementDAO {

	
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {return em;}
	public void setEm(EntityManager em) {this.em = em;}
	
	/* (non-Javadoc)
	 * @see com.courtalon.springMvcExo3Form.repositories.IDepartementDAO#findAll()
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Departement> findAll() {
		return em.createQuery("select d from Departement as d", Departement.class)
				.getResultList();
	}
	
	/* (non-Javadoc)
	 * @see com.courtalon.springMvcExo3Form.repositories.IDepartementDAO#findById(int)
	 */
	@Override
	@Transactional(readOnly=true)
	public Departement findById(int id) {
		return em.find(Departement.class, id);
	}
	
}

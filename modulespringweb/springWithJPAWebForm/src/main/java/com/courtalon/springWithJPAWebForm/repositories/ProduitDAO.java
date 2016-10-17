package com.courtalon.springWithJPAWebForm.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.springWithJPAWebForm.metier.Produit;

public class ProduitDAO implements IProduitDAO {

	// cette annotation indique au transactionManager
	// qu'il devra injecter ici l'entity manager a utiliser
	@PersistenceContext
	private EntityManager em;
	public EntityManager getEm() {return em;}
	public void setEm(EntityManager em) {this.em = em;}
	
	// cette annotation indique a spring d'ouvrir et gerer une transaction
	// jpa autour de cette méthode
	@Override
	@Transactional
	public List<Produit> findAll() {
		return em.createQuery("from Produit", Produit.class)
				 .getResultList();
	}
	
	@Override
	@Transactional
	public Produit findByID(int id) {
		return em.find(Produit.class, id);
	}
	@Override
	@Transactional
	public Produit save(Produit p) {
		if (p.getId() == 0) {
			// insertion
			em.persist(p);
			return p;
		}
		else {
			// update
			return em.merge(p);
		}
	}
	@Override
	@Transactional
	public void remove(int id) {
		Produit p = em.find(Produit.class, id);
		if (p != null)
			em.remove(p);
	}
	
	
}

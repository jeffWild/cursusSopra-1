package com.courtalon.springMVCProduitForm.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.springMVCProduitForm.metier.Produit;

public class ProduitRepositoryImpl implements ProduitRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	public EntityManager getEm() {return em;}
	public void setEm(EntityManager em) {this.em = em;}


	@Override
	@Transactional
	public Produit findOneWithImage(int id) {
		TypedQuery<Produit> q = em.createQuery(
				"select p from Produit as p left join fetch p.images where p.id=:pid",
				Produit.class);
		q.setParameter("pid", id);
		return q.getSingleResult();
	}

}

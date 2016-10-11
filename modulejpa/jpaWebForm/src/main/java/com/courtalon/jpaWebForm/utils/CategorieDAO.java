package com.courtalon.jpaWebForm.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.courtalon.jpaWebForm.metier.Categorie;


public class CategorieDAO {
	private EntityManagerFactory emf;
	
	public CategorieDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	
	public List<Categorie> findAll() {
		List<Categorie> categories = new ArrayList<>();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// le mot clé fetch ici demande a JPA de précharger
		// les objets associé de la collection posts de chaque categorie
		// EN GROS, equivalent a un fetchType.EAGER, mais uniquement pour cette requette
		categories = em.createQuery(
				"select c from Categorie as c left join fetch c.posts",
				Categorie.class)
						.getResultList();
		
		tx.commit();
		em.close();
		return categories;
	}
}

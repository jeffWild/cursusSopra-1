package com.courtalon.jpaWebForm.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.courtalon.jpaWebForm.metier.Post;


public class PostDAO {
	private EntityManagerFactory emf;
	
	public PostDAO(EntityManagerFactory emf) {
		this.emf = emf;
		
	}
	
	public List<Post> findAll() {
		List<Post> posts = new ArrayList<>();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		posts = em.createQuery("from Post", Post.class)
				  .getResultList();
		
		tx.commit();
		em.close();
		return posts;
	}
	
	
	
}

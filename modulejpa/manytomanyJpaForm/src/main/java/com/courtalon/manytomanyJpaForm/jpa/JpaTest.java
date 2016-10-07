package com.courtalon.manytomanyJpaForm.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.courtalon.manytomanyJpaForm.beans.*;

public class JpaTest {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testHibernate");
        Scanner input = new Scanner(System.in);

        input.nextLine();
        System.out.println("--------------------------------------");
		test1(emf);

        input.nextLine();
		System.out.println("--------------------------------------");
		test2(emf);

        input.nextLine();
		System.out.println("--------------------------------------");		

	    emf.close();
		System.out.println("done...");
	}




	public static void test1(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		em.persist(new Tag(0, "voyage"));
		em.persist(new Tag(0, "cuisine"));
		em.persist(new Tag(0, "opera"));
		em.persist(new Tag(0, "peche"));
		em.persist(new Tag(0, "java"));
		
		Random rd = new Random();
		
		// genere 50 posts
		for (int i =1; i <= 50; i++) {
			Post p = new Post(0, "titre" + i, "corps" + i, rd.nextInt(6));
			// une chance sur deux d'etre associé a chacun des tags pour ce post
			for (int j = 1; j<= 5; j++) {
				if (rd.nextBoolean())
					p.addTag(em.find(Tag.class, j));
			}
			em.persist(p);
		}
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}


	public static void test2(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}

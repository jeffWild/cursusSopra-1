package com.courtalon.jpaHeritageForm.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.courtalon.jpaHeritageForm.beans.*;

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
	
		em.persist(new Personne(0, "eponge", "bob"));
		em.persist(new Personne(0, "etoile", "patrick"));
		em.persist(new Personne(0, "squirrel", "sandy"));
		em.persist(new Personne(0, "poulpe", "polo"));
		em.persist(new Personne(0, "crab", "crusty"));
		
		em.persist(new Employe(0, "potter", "harry", "sorcier assistant", 15000));
		em.persist(new Employe(0, "granger", "hermione", "chercheuse en sorcellerie", 25000));
		em.persist(new Employe(0, "waislea", "ron", "homme de maison", 10000));
		em.persist(new Employe(0, "hagrid", "hagrid", "menagerie", 13500));
		em.persist(new Employe(0, "malfoy", "drago", "mechant de service", 22500));
		
		
		em.persist(new Client(0, "Kent", "clark", "superman@anonymous.com", 5000));
		em.persist(new Client(0, "Wayne", "bruce", "bruce@notbatman.com", 50000000));
		em.persist(new Client(0, "Stark", "tony", "tony@thatsme.com", 2000000));
		em.persist(new Client(0, "Parker", "Peter", "parker@jobhunting.com", 50));
		em.persist(new Client(0, "Prince", "diana", "diana@amazon.com", 10000));
		
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
	
		List<Client> clients = em.createQuery("select c from Client as c",
												Client.class)
								 .getResultList();
		for (Client c : clients)
			System.out.println(c);
		
		System.out.println("---------------------------");
		
		List<Personne> peoples = em.createQuery("select p from Personne as p",
												Personne.class)
								   .getResultList();
		for (Personne p : peoples) {
			System.out.println(p);
		}
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}

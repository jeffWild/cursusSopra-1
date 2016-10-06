package com.courtalon.simpleMappingForm.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.courtalon.simpleMappingForm.beans.*;

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
		test3(emf);
		
		input.nextLine();
		System.out.println("--------------------------------------");
		test4(emf);

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
		
		Fabriquant f1 = new Fabriquant(0, "bio forever", new Date());
		Fabriquant f2 = new Fabriquant(0, "paleo itique", new Date());
		Fabriquant f3 = new Fabriquant(0, "moulin des anciens", new Date());
		em.persist(f1);
		em.persist(f2);
		em.persist(f3);
		
		Produit p = new Produit(0, "biere oceania", 9.99, 0.5, 15, "boisson");
		p.setFabriquant(f1);
		em.persist(p);
		p = new Produit(0, "tofu tout fou", 19.99, 0.75, 39, "divers");
		p.setFabriquant(f2);
		em.persist(p);
		p = new Produit(0, "klug original", 15.99, 1.5, 25, "divers");
		p.setFabriquant(f3);
		em.persist(p);
		p = new Produit(0, "steak de lama", 49.99, 0.5, 4, "viande");
		p.setFabriquant(f3);
		em.persist(p);
		p = new Produit(0, "miel des carpathes", 29.99, 0.25, 85, "divers");
		p.setFabriquant(f2);
		em.persist(p);
		p = new Produit(0, "carpaccio de soja", 14.99, 0.35, 10, "viande");
		p.setFabriquant(f2);
		em.persist(p);
		p = new Produit(0, "graines de courge", 5.99, 0.25, 75, "cereale");
		p.setFabriquant(f1);
		em.persist(p);
		p = new Produit(0, "quinoa des andes", 34.99, 1.0, 32, "cereale");
		p.setFabriquant(f3);
		em.persist(p);
		
		
		
		//em.persist(f3);
		
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
		
		// attention pour les parametres nommé
		// préfixer avec ':', et coller le nom directement après
		TypedQuery<Produit> q1 = em.createQuery(
				"select p from Produit as p WHERE p.prix > :prixmin",
				Produit.class);
		// ne pas remettre ':' dans le nom du parametre
		q1.setParameter("prixmin", 25.0);
		
		List<Produit> produits = q1.getResultList();
		for (Produit p : produits) {
			System.out.println(p);
		}
		System.out.println("------------");
		Query q2 = em.createQuery(
				"select p.nom, p.prix / p.poids as pkg from Produit as p");
		List<Object[]> results = q2.getResultList();
		for (Object[] ligne : results) {
			System.out.println(Arrays.toString(ligne));
		}
		
		System.out.println("---------------------------");
		
		Query q3 = em.createQuery(
			"select p.categorie, count(p.id) from Produit as p group by p.categorie");
		results = q3.getResultList();
		for (Object[] ligne : results) {
			System.out.println(Arrays.toString(ligne));
		}
		
		System.out.println("---------------------------");
		Query q4 = em.createQuery(
			"select p.categorie, avg(p.prix) from Produit as p group by p.categorie "
			+ " having avg(p.prix) > :avgmin");
		q4.setParameter("avgmin", 20.0);
		results = q4.getResultList();
		for (Object[] ligne : results) {
			System.out.println(Arrays.toString(ligne));
		}
		
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

	public static void test3(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		Produit p = em.find(Produit.class, 1);
		System.out.println(p);
		System.out.println(p.getFabriquant());
		
		System.out.println("----------------------------");

		Fabriquant f = em.find(Fabriquant.class, 2);
		System.out.println(f);
		System.out.println("produits:");
		for (Produit pr : f.getProduits()) {
			System.out.println(pr);
		}
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

	
	public static void test4(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		Produit p = new Produit(0, "encens himalayen", 45.99, 0.1, 150, "divers");
		Fabriquant f = new Fabriquant(0, "dalai Bio", new Date());
		//p.setFabriquant(f);
		f.getProduits().add(p);
		
		em.persist(f);
		em.persist(p);
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}

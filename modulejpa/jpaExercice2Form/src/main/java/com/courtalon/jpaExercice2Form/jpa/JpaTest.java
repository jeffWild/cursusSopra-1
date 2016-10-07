package com.courtalon.jpaExercice2Form.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.courtalon.jpaExercice2Form.beans.*;

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
		em.persist(new Client(0, "bob l'eponge", "bob@bikinibottom.com"));
		em.persist(new Client(0, "patrick etoile", "patrick@bikinibottom.com"));
		em.persist(new Client(0, "carlos le calamar", "carlos@crustyCrab.com"));
		em.persist(new Client(0, "sandy ecureuil", "sandy@squirrel.com"));
		em.persist(new Client(0, "garry", "garry@theflash.com"));
		
		String[] noms = {"table ", "chaise ", "canape ", "pouf ", "lit "};
		String[] couleurs = {"rose bonbon ", "bleue electrique"
							, "vert pomme ", "jaune poussin ", "rouge carmin"};
		
		Random rd = new Random();
		
		for (int i = 0 ; i < 20;  i++) {
			Commande c = new Commande(0,
					noms[rd.nextInt(noms.length)] + couleurs[rd.nextInt(couleurs.length)],
					rd.nextDouble() * 500.0 + 50.0,
					new Date(rd.nextInt(10) + 100, rd.nextInt(12), 5));
			c.setClient(em.find(Client.class, rd.nextInt(5) + 1));
			em.persist(c);
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
		
		//  trouver un client et afficher ses commandes
		Client c = em.find(Client.class, 1);
		System.out.println(c + " -> commandes");
		for (Commande cmd : c.getCommandes()) {
			System.out.println(cmd);
		}
		
		System.out.println("---------------------");
		// lister les commandes avec le nom de leur client
		// from Commande -> version abrégée equivalente a : select c from Commande as c
		TypedQuery<Commande> q2 = em.createQuery("from Commande", Commande.class);
		List<Commande> commandes = q2.getResultList();
		for (Commande cmd : commandes) {
			System.out.println(cmd + " -> " + cmd.getClient().getNom());
		}
		
		System.out.println("---------------------");
		//  lister les clients et le nombre de leur commandes
		Query q3 = em.createQuery("select cl.nom, count(cmd.id) from Client as cl "
				+ " join cl.commandes as cmd group by cl.nom");
		// ATTENTION, si la requette ne renvoie qu'une colonne par ligne
		// ce n'est plus List<Object[]>, c'est List<Type_colonne>
		List<Object[]> results = q3.getResultList();
		for (Object[] ligne : results) {
			System.out.println(Arrays.toString(ligne));
		}
		
		System.out.println("---------------------");
		//  lister les clients et le nombre de leur commande > prix choisi
		// c'est le meme requete qu'au dessus, a laquel on rajoute une close where
		// pour exclure du calcul les commandes en dessous du prix seuil choisi
		Query q4 = em.createQuery("select cl.nom, count(cmd.id) from Client as cl "
				+ " join cl.commandes as cmd where cmd.prix > :pmin group by cl.nom");
		Scanner reader = new Scanner(System.in);
		System.out.println("prix minimum ? ");
		double pmin = Double.parseDouble(reader.nextLine());
		q4.setParameter("pmin", pmin);
		
		results = q4.getResultList();
		for (Object[] ligne : results) {
			System.out.println(Arrays.toString(ligne));
		}
	
		
		System.out.println("---------------------");
		// lister les clients et la moyenne de prix de leur commande
		// uniquement si la moyenne est supérieur a une valeur choisie	
		Query q5 = em.createQuery("select cl.nom, avg(cmd.prix) from Client as cl "
				+ " join cl.commandes as cmd "
				+ " group by cl.nom having avg(cmd.prix) > :avgmin");
		System.out.println("moyenne minimum ? ");
		double avgmin = Double.parseDouble(reader.nextLine());
		q5.setParameter("avgmin", avgmin);
		results = q5.getResultList();
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
		em.remove(em.find(Client.class, 3));
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}

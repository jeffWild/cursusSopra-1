package com.courtalon.firstJPAForm.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.courtalon.firstJPAForm.beans.Message;

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
		// demarrage de la transaction
		tx.begin();
		//----------------------------------------------------
		/*Scanner input = new Scanner(System.in);
		System.out.println("titre message ? ");
		String titre = input.nextLine();
		System.out.println("corps message ? ");
		String corps = input.nextLine();*/
		Message msg = new Message(0, "anniversaire", "un nouveau scooter");
		// La fonction persist permet de sauvegarde en base un nouvel objet
		//  --> insertion
		em.persist(msg);
		//----------------------------------------------------
		// fin de la transaction
		tx.commit();
		// fermeture de l'entityManager
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
		
		Scanner input = new Scanner(System.in);
		System.out.println("identifiant message? ");
		int id = Integer.parseInt(input.nextLine());
		// la fonction find permet de récupérer UN objet(entite)
		// depuis la base
		// le premier parametre est la classe de l'entité recherchée
		// le deuxieme parametre est la valeur de la clé primaire
		Message msg = em.find(Message.class, id);
		if (msg != null) {
		    System.out.println(msg.getTitre());
		    System.out.println(msg.getCorps());
		    msg.setTitre("un nouveau titre");
		    System.out.println("----------");
		    msg.setTitre("encore un autre");
		    
        }
        else {
		    System.out.println("message inconnu...");   
        }
		
		em.persist(new Message(0, "yolo en scooter", "il est surpuissant"));
		em.persist(new Message(0, "a l'hospital", "j'avais pas vu le panneau"));
		em.persist(new Message(0, "yolo en voiture", "je t'envoie un message a l'arrivé"));
		
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
		// ATTENTION , Message est LA CLASSE, et non la table
		TypedQuery<Message> q1 = em.createQuery(
				"select m from Message as m",
				Message.class);
		// execution de la requette
		List<Message> messages = q1.getResultList();
		Message msgoriginal = null;
		for (Message m : messages) {
			// je garde en mémoire le message 2 
			if (m.getId() == 2 )
				msgoriginal = m;
			System.out.println(m.getTitre());
		}
		System.out.println("apres liste --------------------");
		
		Message msg = em.find(Message.class, 2);
		// ici, le test marche, car find me renvoie la même instance
		// que msgoriginal qui avait été renvoye par la premiere query
		System.out.println("msg == msgoriginal -> " + (msg == msgoriginal));
		System.out.println(msg.getTitre());
		msg.setTitre("titre modifié");
		//----------------------------------------------------
		tx.commit();
		em.close();
		// mon objet msg est dans un état qu'on apelle détaché (detached)
		// quand un objet est détaché, il n'est plus relié a la base
		msg.setTitre("titre autre");
		System.out.println(msg.getTitre());
	}
	
	public static void test4(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		System.out.println("supression message 3");
		Message m = em.find(Message.class, 3);
		if (m != null)
			em.remove(m);
		System.out.println("effacement....");
		/*
		 * le remove sera délayé jusqu'au commit ou
		 * si une requete suivante peut etre dépendante de ce remove
		 * comme la liste ici
		 */
		TypedQuery<Message> q1 = em.createQuery(
				"select m from Message as m",
				Message.class);
		// execution de la requette
		List<Message> messages = q1.getResultList();
		System.out.println("liste récupérée");
		
		//----------------------------------------------------
		tx.commit();
		em.close();
		}
}

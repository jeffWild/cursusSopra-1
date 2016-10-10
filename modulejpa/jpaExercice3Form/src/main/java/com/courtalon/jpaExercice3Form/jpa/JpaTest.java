package com.courtalon.jpaExercice3Form.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.courtalon.jpaExercice3Form.beans.*;

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
		
		em.persist(new Matiere(0, "java"));
		em.persist(new Matiere(0, "operation commando"));
		em.persist(new Matiere(0, "opera"));
		em.persist(new Matiere(0, "peche"));
		
		em.persist(new Professeur(0, "steven seagal", "steven@cooking.com", 15000));
		em.persist(new Professeur(0, "wolfgang amadeus mozart", "mozart@dubstep.com", 12000));
		em.persist(new Professeur(0, "bear grylls", "bear@survive.com", 20000));
		
		Random rd = new Random();
		for (int i = 1; i <= 15; i++) {
			Cours c = new Cours(0, "cours " + i, new Date(), new Date(), rd.nextInt(16) + 30);
			c.setProfesseur(em.find(Professeur.class, rd.nextInt(3) + 1));
			c.setMatiere(em.find(Matiere.class, rd.nextInt(4) + 1));
			em.persist(c);
		}
		
		for (int i = 1; i <= 100; i++) {
			Etudiant et = new Etudiant(0, "etudiant " + i, "bob" + i + "@toto.com");
			for (int j = 1; j <= 15; j++) {
				if (rd.nextDouble() < 0.33) {
					et.addCours(em.find(Cours.class, j));
				}
			}
			em.persist(et);
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
		
		// lister tous les etudiants qui assistent a un cours choisi
		TypedQuery<Etudiant> q1 = em.createQuery(
				"select et from Etudiant as et join et.courses as c where c.id=:cid",
				Etudiant.class);
		q1.setParameter("cid", 1);
		List<Etudiant> etudiants = q1.getResultList();
		for (Etudiant et : etudiants) {
			System.out.println(et);
		}
		System.out.println("--------------------------------------");
		
		// lister les cours et le nombre d'etudiants qui y assistent
		Query q2 = em.createQuery(
				"select c.titre, count(et.id) from Cours as c join c.participants as et "
				+ " group by c.titre");
		List<Object[]> results = q2.getResultList();
		for (Object[] ligne : results)
			System.out.println(Arrays.toString(ligne));
		
		System.out.println("--------------------------------------");
		// lister les cours et leur taux de remplissage
		Query q3 = em.createQuery(
				"select c.titre, 100.0 * count(et.id) / c.capacite from Cours as c join c.participants as et "
				+ " group by c.titre");
		results = q3.getResultList();
		for (Object[] ligne : results)
			System.out.println(Arrays.toString(ligne));
		
		System.out.println("--------------------------------------");
		// lister les matieres et le nombre d'etudiants qui y assistent
		Query q4 = em.createQuery(
				"select m.libelle, count(distinct et.id) from Matiere as m join m.courses as c join c.participants as et "
				+ " group by m.libelle");
		results = q4.getResultList();
		for (Object[] ligne : results)
			System.out.println(Arrays.toString(ligne));
		
		System.out.println("--------------------------------------");
		// lister les etudiants qui assistent a au moins un cours d'un professeur donné
		TypedQuery<Etudiant> q5 = em.createQuery(
				"select distinct(et) from Etudiant as et join et.courses as c "
				+ " where c.professeur.id=:pid "
				, Etudiant.class);
		q5.setParameter("pid", 1);
		etudiants = q5.getResultList();
		for (Etudiant et : etudiants) {
			System.out.println(et);
		}
		// lister les etudiants qui assistent a aucun cours d'un 
		// professeur donné
		System.out.println("--------------------------------------");
		TypedQuery<Etudiant> q6 = em.createQuery(
				"select et from Etudiant as et where NOT EXISTS ("
				+ " select et2 from Etudiant as et2 join et2.courses as c "
				+ " where c.professeur.id=:pid AND et2.id = et.id "
				+ ")"
				, Etudiant.class);
		q6.setParameter("pid", 1);
		etudiants = q6.getResultList();
		for (Etudiant et : etudiants) {
			System.out.println(et);
		}
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}

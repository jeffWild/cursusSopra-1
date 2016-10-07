package com.courtalon.manytomanyJpaForm.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

     /*   input.nextLine();
		System.out.println("--------------------------------------");
		test2(emf);
*/
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
		
		em.persist(new Tag(0, "voyage"));
		em.persist(new Tag(0, "cuisine"));
		em.persist(new Tag(0, "opera"));
		em.persist(new Tag(0, "peche"));
		em.persist(new Tag(0, "java"));
		
		Random rd = new Random();
		
		// genere 50 posts
		for (int i =1; i <= 50; i++) {
			Post p = new Post(0, "titre" + i, "corps" + i, rd.nextInt(6));
			p.setCoordonnees(
					new GeoLocalisation(rd.nextDouble() * 50.0,
										rd.nextDouble() * 50.0));
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
		
		// lister les posts etiqueté par le tag no 1
		TypedQuery<Post> q1 = em.createQuery(
				"select p from Post as p join p.tags as t where t.id=:tid",
				Post.class);
		q1.setParameter("tid", 1);
		List<Post> posts = q1.getResultList();
		for (Post p : posts)
			System.out.println(p);
		
		System.out.println("---------------------------------");
		// lister les tags et le nombres de post qu'ils etiquettent
		Query q2 = em.createQuery("select t.libelle, count(p.id) from Tag as t "
				+ " join t.posts as p group by t.libelle");
		
		List<Object[]> results = q2.getResultList();
		for (Object[] ligne : results)
			System.out.println(Arrays.toString(ligne));
		
		
		// ici, on utilise une sous-requette
		// ce qui signifie au la sous-requette entre parenthese
		// sera executer pour chaque ligne renvoyée par la requette
		// principal
		// ici, par exemple pour le post no1 (premiere ligne renvoyée)
		// il executera la sous requette
		// select t from Tag as t join t.posts as p2 where p2.id = 1 AND t.id = 1
		// si cette sous requette renvoie au moins un ligne, NOT EXISTS renverra
		// false et ce post ne sera pas retenu
		// quand on arrive sur la deuxieme ligne, la sous requette sera
		// select t from Tag as t join t.posts as p2 where p2.id = 2 AND t.id = 1
		// et ainsi de suite
		System.out.println("-----------------------------");
		q1 = em.createQuery(
				"select p from Post as p where NOT EXISTS ("
				+ "select t from Tag as t join t.posts as p2 "
				+ " where p2.id = p.id AND t.id = :tid"
				+ ")",
				Post.class);
		q1.setParameter("tid", 1);
		posts = q1.getResultList();
		for (Post p : posts)
			System.out.println(p);
		
		// je veux retrouver tous les posts etiqueté avec le tag 1 et le tag2
		System.out.println("------------------------------");
		q1 = em.createQuery("select p from Post as p"
				+ " join p.tags as tA"
				+ " join p.tags as tB"
				+ " where tA.id=:tidA AND tB.id=:tidB", Post.class);
		q1.setParameter("tidA", 1);
		q1.setParameter("tidB", 2);
		posts = q1.getResultList();
		for (Post p : posts)
			System.out.println(p);
	
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
		
		em.remove(em.find(Post.class, 1));
		//em.remove(em.find(Tag.class, 1));
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
		

}

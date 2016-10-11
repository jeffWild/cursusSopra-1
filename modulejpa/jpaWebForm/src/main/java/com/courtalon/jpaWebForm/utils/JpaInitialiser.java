package com.courtalon.jpaWebForm.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class JpaInitialiser
 *
 */
public class JpaInitialiser implements ServletContextListener {

	// pour pouvoir ecrire des messages dans le log
	private static Logger log = LogManager.getLogger(JpaInitialiser.class);
	
	private EntityManagerFactory emf;
	
    public JpaInitialiser() {
        log.info("instanciation du JpaInitialiser");
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         log.info("destruction du JpaInitialiser");
         if (emf != null)
        	 emf.close();
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	log.info("initialisation contexte de persistence");
    	emf = Persistence.createEntityManagerFactory("testHibernate");
    	
    	// mise a disposition du postDAO aux servlets qui en auraient besoin
    	PostDAO postDAO = new PostDAO(emf);
    	arg0.getServletContext().setAttribute("postDAO", postDAO);
    	
    	// mise a disposition du categorieDAO aux servlets qui en auraient besoin
    	CategorieDAO categorieDAO = new CategorieDAO(emf);
    	arg0.getServletContext().setAttribute("categorieDAO", categorieDAO);
    	
    }
	
}

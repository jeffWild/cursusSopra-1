package com.courtalon.fistSecurityForm.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.courtalon.fistSecurityForm.metier.Utilisateur;
import com.courtalon.fistSecurityForm.repositories.UtilisateurRepository;

@Service
public class DatabaseInitialiser implements ApplicationListener<ContextRefreshedEvent> {
	
	private static Logger log = LogManager.getLogger(DatabaseInitialiser.class);
	
	@Autowired
	private UtilisateurRepository UtilisateurRepository;
	public UtilisateurRepository getUtilisateurRepository() {return UtilisateurRepository;}
	public void setUtilisateurRepository(UtilisateurRepository utilisateurRepository) {UtilisateurRepository = utilisateurRepository;}

	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// si admin n'existe pas
		if (getUtilisateurRepository().findByName("admin") == null) {
			log.info("creation d'admin");
			Utilisateur u = new Utilisateur(0,"admin", "", true);
			// on creer l'utilisateur
			
			// cryptage du mot de passe par defaut
			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
			u.setPassword(pe.encode("admin"));
			
			// sauvegarde de l'utilisateur
			getUtilisateurRepository().save(u);
		}
		
	}

}

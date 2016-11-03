package com.courtalon.fistSecurityForm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.courtalon.fistSecurityForm.metier.Utilisateur;
import com.courtalon.fistSecurityForm.repositories.UtilisateurRepository;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	public UtilisateurRepository getUtilisateurRepository() {return utilisateurRepository;}
	public void setUtilisateurRepository(UtilisateurRepository utilisateurRepository) {this.utilisateurRepository = utilisateurRepository;}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		// on retrouve l'utilisateur avec le nom demandé
		Utilisateur u = getUtilisateurRepository().findByName(name);
		
		// s'il n'existe pas, erreur
		if (u == null)
			throw new UsernameNotFoundException("utilisateur " + name + " inconnu");
		
		// sinon, on l'encaosule dans un UserDetails avant de le renvoyer a
		// spring security
		return new UtilisateurDetails(u);
	}

}

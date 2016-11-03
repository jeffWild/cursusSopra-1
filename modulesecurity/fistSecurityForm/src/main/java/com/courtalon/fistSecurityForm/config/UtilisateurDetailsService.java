package com.courtalon.fistSecurityForm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.courtalon.fistSecurityForm.repositories.UtilisateurRepository;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	public UtilisateurRepository getUtilisateurRepository() {return utilisateurRepository;}
	public void setUtilisateurRepository(UtilisateurRepository utilisateurRepository) {this.utilisateurRepository = utilisateurRepository;}

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}

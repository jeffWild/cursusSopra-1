package com.courtalon.fistSecurityForm.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.courtalon.fistSecurityForm.metier.DroitUtilisateur;
import com.courtalon.fistSecurityForm.metier.Utilisateur;

public class UtilisateurDetails implements UserDetails {

	private Utilisateur utilisateur;
	
	public UtilisateurDetails(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//SimpleGrantedAuthority sga = new SimpleGrantedAuthority("ADMIN");
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (DroitUtilisateur du : utilisateur.getDroits()){
			authorities.add(new SimpleGrantedAuthority(du.getDroit()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {return utilisateur.getPassword();}

	@Override
	public String getUsername() {return utilisateur.getName();}

	@Override
	public boolean isAccountNonExpired() {return true;}
	@Override
	public boolean isAccountNonLocked() {return true;}
	@Override
	public boolean isCredentialsNonExpired() {return true;}

	@Override
	public boolean isEnabled() {return utilisateur.isEnabled();}

}

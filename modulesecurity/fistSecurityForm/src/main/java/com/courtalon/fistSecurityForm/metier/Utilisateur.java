package com.courtalon.fistSecurityForm.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Utilisateur {
	private int id;
	private String name;
	private String password;
	private boolean enabled;
	private Set<DroitUtilisateur> droits;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Set<DroitUtilisateur> getDroits() {
		if (droits == null)
			droits = new HashSet<>();
		return droits;
	}
	public void setDroits(Set<DroitUtilisateur> droits) {this.droits = droits;}
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	@Column(unique=true)
	public String getName() {return name;}
	
	public void setName(String name) {this.name = name;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public boolean isEnabled() {return enabled;}
	public void setEnabled(boolean enabled) {this.enabled = enabled;}
	
	public Utilisateur() {this(0, "", "", true); }
	public Utilisateur(int id, String name, String password, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.enabled = enabled;
	}
	
	
	
}

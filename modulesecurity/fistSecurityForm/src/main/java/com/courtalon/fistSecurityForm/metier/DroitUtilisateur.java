package com.courtalon.fistSecurityForm.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DroitUtilisateur {
	private int id;
	private String droit;
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getDroit() {return droit;}
	public void setDroit(String droit) {this.droit = droit;}
	
	public DroitUtilisateur() { this(0, "");}
	public DroitUtilisateur(int id, String droit) {
		super();
		this.id = id;
		this.droit = droit;
	}
	
	
	
}

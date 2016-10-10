package com.courtalon.jpaHeritageSingleTableForm.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="3")
public class Client extends Personne
{
	private String email;
	private double soldeCompte;
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public double getSoldeCompte() {return soldeCompte;}
	public void setSoldeCompte(double soldeCompte) {this.soldeCompte = soldeCompte;}
	
	public Client() { this(0, "", "", "", 0.0);}
	public Client(int id, String nom, String prenom, String email, double soldeCompte) {
		super(id, nom, prenom);
		this.email = email;
		this.soldeCompte = soldeCompte;
	}
	
	@Override
	public String toString() {
		return "Client [email=" + email + ", soldeCompte=" + soldeCompte + ", getId()=" + getId() + ", getNom()="
				+ getNom() + ", getPrenom()=" + getPrenom() + "]";
	}
	
	
	
	
	
}

package com.courtalon.jpaExercice2Form.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

@Entity
public class Client {
	private int id;
	private String nom;
	private String email;
	private Set<Commande> commandes;
	
	@PreRemove
	public void clearAllCommandes() {
		for (Commande cmd : getCommandes())
			cmd.setClient(null);
	}
	
	
	@OneToMany(mappedBy="client")
	public Set<Commande> getCommandes() {
		if (commandes == null)
			commandes = new HashSet<>();
		return commandes;
	}
	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	public Client() {this(0, "", "");}
	public Client(int id, String nom, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", email=" + email + "]";
	}
	
	

}

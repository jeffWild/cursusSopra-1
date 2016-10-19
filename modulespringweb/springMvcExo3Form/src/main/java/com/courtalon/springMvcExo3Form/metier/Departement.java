package com.courtalon.springMvcExo3Form.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Departement {
	private int id;
	private String nom;
	private Set<Employe> employes;
	private Employe manager;
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	
	@OneToMany(mappedBy="departement")
	public Set<Employe> getEmployes() {
		if (employes == null)
			employes = new HashSet<>();
		return employes;
	}
	public void setEmployes(Set<Employe> employes) {this.employes = employes;}
	
	@ManyToOne
	public Employe getManager() {return manager;}
	public void setManager(Employe manager) {this.manager = manager;}
	
	
	public Departement() { this(0, "");}
	public Departement(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return "Departement [id=" + id + ", nom=" + nom + "]";
	}
	
	

}

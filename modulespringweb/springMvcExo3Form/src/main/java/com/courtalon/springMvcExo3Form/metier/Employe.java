package com.courtalon.springMvcExo3Form.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

@Entity
public class Employe {
	private int id;
	private String nom;
	private String email;
	private double salaire;
	private String poste;
	private Departement departement;
	private int departementID;
	
	/*
	@PreUpdate
	@PrePersist
	public void fillDepartementFormID() {
		if (getDepartementID() == 0)
			setDepartement(null);
		else
			setDepartement(departement);
	}*/
	
	// automatiquement renseigner le champ DepartementID
	// apres chargement d'un Employe
	@PostLoad
	public void fillDepartementID() {
		if (getDepartement() != null)
			setDepartementID(getDepartement().getId());
		else
			setDepartementID(0);
	}
	
	// indique a JPA de ne pas mapper cet attribut
	@Transient
	public int getDepartementID() {return departementID;}
	public void setDepartementID(int departementID) {this.departementID = departementID;}
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public double getSalaire() {return salaire;}
	public void setSalaire(double salaire) {this.salaire = salaire;}
	public String getPoste() {return poste;}
	public void setPoste(String poste) {this.poste = poste;}
	
	@ManyToOne
	public Departement getDepartement() {return departement;}
	public void setDepartement(Departement departement) {this.departement = departement;}
	
	public Employe() { this(0, "", "", 0.0, "");}
	public Employe(int id, String nom, String email, double salaire, String poste) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.salaire = salaire;
		this.poste = poste;
	}
	
	@Override
	public String toString() {
		return "Employe [id=" + id + ", nom=" + nom + ", email=" + email + ", salaire=" + salaire + ", poste=" + poste
				+ "]";
	}
	

}

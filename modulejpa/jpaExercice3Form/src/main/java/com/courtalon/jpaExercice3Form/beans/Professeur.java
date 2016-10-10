package com.courtalon.jpaExercice3Form.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Professeur {
	private int id;
	private String nom;
	private String email;
	private double salaire;
	
	private Set<Cours> courses;
	
	@OneToMany(mappedBy="professeur")
	public Set<Cours> getCourses() {
		if (courses == null)
			courses = new HashSet<>();
		return courses;
	}
	public void setCourses(Set<Cours> courses) {
		this.courses = courses;
	}
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public double getSalaire() {return salaire;}
	public void setSalaire(double salaire) {this.salaire = salaire;}
	
	public Professeur() { this(0, "", "", 0.0);}
	public Professeur(int id, String nom, String email, double salaire) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.salaire = salaire;
	}
	@Override
	public String toString() {
		return "Professeur [id=" + id + ", nom=" + nom + ", email=" + email + ", salaire=" + salaire + "]";
	}

}

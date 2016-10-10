package com.courtalon.jpaExercice3Form.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Etudiant {
	private int id;
	private String nom;
	private String email;
	private Set<Cours> courses;
	
	
	public void addCours(Cours c) {
		if (c != null)
			c.addEtudiant(this);
	}
	
	// coté mirroir d'une association existante
	// de l'autre coté (pointée par mappedBy)
	@ManyToMany(mappedBy="participants")
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
	
	public Etudiant() { this(0, "", "");}
	public Etudiant(int id, String nom, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", email=" + email + "]";
	}
	
}

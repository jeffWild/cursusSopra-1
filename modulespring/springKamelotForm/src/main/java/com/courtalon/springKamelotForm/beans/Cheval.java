package com.courtalon.springKamelotForm.beans;

public class Cheval {
	private String nom;

	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	
	public Cheval(String nom) {
		this.nom = nom;
		System.out.println("construction cheval " + nom);
	}
	
	public void preparer() {
		System.out.println("preparation du cheval " + getNom());
	}
	
}

package com.courtalon.springExpressionForm.beans;

public class Site {
	private String nom;
	private Adresse adresse;
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public Adresse getAdresse() {return adresse;}
	public void setAdresse(Adresse adresse) {this.adresse = adresse;}
	
	public Site() {this("", new Adresse()); }
	public Site(String nom, Adresse adresse) {
		super();
		this.nom = nom;
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Site [nom=" + nom + ", adresse=" + adresse + "]";
	}
	
}

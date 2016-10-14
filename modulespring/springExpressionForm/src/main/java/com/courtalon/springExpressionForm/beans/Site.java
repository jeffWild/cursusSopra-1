package com.courtalon.springExpressionForm.beans;

public class Site {
	private String nom;
	private Adresse adresse;
	private GeoLocalisation localisation;
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public Adresse getAdresse() {return adresse;}
	public void setAdresse(Adresse adresse) {this.adresse = adresse;}
	public GeoLocalisation getLocalisation() {return localisation;}
	public void setLocalisation(GeoLocalisation localisation) {this.localisation = localisation;}
	
	public Site() {this("", new Adresse(), new GeoLocalisation()); }
	public Site(String nom, Adresse adresse, GeoLocalisation localisation) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.localisation = localisation;
	}
	@Override
	public String toString() {
		return "Site [nom=" + nom + ", adresse=" + adresse + ", localisation=" + localisation + "]";
	}
	
	
}

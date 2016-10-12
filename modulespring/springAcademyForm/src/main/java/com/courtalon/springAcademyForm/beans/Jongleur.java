package com.courtalon.springAcademyForm.beans;

public class Jongleur implements IArtiste {
	private String nom;
	private int nbBalles;
	
	public int getNbBalles() {return nbBalles;}
	public void setNbBalles(int nbBalles) {this.nbBalles = nbBalles;}
	public void setNom(String nom) {this.nom = nom;}
	@Override
	public String getNom() {return nom;}

	@Override
	public void faireNumero() {
		System.out.println(getNom() + " vas vous eblouir");
		System.out.println("jongle..jongle avec " + getNbBalles() + " balles");
	}

}

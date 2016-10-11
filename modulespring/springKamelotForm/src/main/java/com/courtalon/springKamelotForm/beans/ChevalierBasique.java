package com.courtalon.springKamelotForm.beans;

public class ChevalierBasique implements IChevalier {
	
	private String nom;
	private IQuete quete;
	private double reussite;
	
	
	public double getReussite() {return reussite;}
	public void setReussite(double reussite) {this.reussite = reussite;}
	public IQuete getQuete() {return quete;}
	public void setQuete(IQuete quete) {this.quete = quete;}
	public void setNom(String nom) {this.nom = nom;}
	@Override
	public String getNom() {return nom;}

	public ChevalierBasique(String nom) {
		this.nom = nom;
	}
	
	@Override
	public void partirEnQuete() {
		System.out.println("moi, " + getNom() 
						 + " part en quete: "
						 + getQuete().getDescription());
		boolean succes = getQuete().realiserQuete(getReussite());
		if (succes)
			System.out.println("moi, " + getNom() + " reviens victorieusement de quete");
		else
			System.out.println("moi, " + getNom() + " aura plus de chance une autre fois");
	}

}

package com.courtalon.springKamelotForm.beans;

public class ChevalierTableRonde implements IChevalier {

	private String nom;
	private IQuete quete;
	private double reussite;
	private Cheval cheval;
	
	
	public Cheval getCheval() {return cheval;}
	public void setCheval(Cheval cheval) {this.cheval = cheval;}
	public double getReussite() {return reussite;}
	public void setReussite(double reussite) {this.reussite = reussite;}
	public IQuete getQuete() {return quete;}
	public void setQuete(IQuete quete) {this.quete = quete;}
	public void setNom(String nom) {this.nom = nom;}
	@Override
	public String getNom() {return nom;}
	
	public ChevalierTableRonde(String nom) {
		System.out.println("construction chevalier " + nom);
		this.nom = nom;
	}

	@Override
	public void partirEnQuete() {
		System.out.println("moi, Sire " + getNom() 
						+ " part glorieusement en quete "
						+ " sur mon fidele destrier " + getCheval().getNom()
						+ " : " + quete.getDescription());
		if (quete.realiserQuete(reussite * 2.0)) {
			System.out.println("moi, Sire " + getNom() 
						+ " rentre couvert de gloire de quete");
		}
		else {
			System.out.println("moi, Sire " + getNom() 
						+ " a eu un impomderable a gérer");
		}
	}

}

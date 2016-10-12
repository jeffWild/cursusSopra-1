package com.courtalon.springKamelotForm.beans;

public class Menestrel {
	
	public void chanterAvant(IChevalier chevalier) {
		System.out.println("Tralala, sire " + chevalier.getNom() 
						 + " part courageusement en quete");
	}

	public void chanterApres(IChevalier chevalier) {
		System.out.println("Tralala, sire " + chevalier.getNom() 
						 + " revient joyeusement de quete");
	}
	
}

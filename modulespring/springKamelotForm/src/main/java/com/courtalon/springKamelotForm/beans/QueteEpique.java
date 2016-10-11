package com.courtalon.springKamelotForm.beans;

import java.util.Random;

public class QueteEpique implements IQuete {

	private String description;
	private double difficulte;
	private Random rd;
	
	@Override
	public String getDescription() {
		return description;
	}
	
	public QueteEpique(String description, double difficulte) {
		this.description = description;
		this.difficulte = difficulte;
		this.rd = new Random();
	}
	
	@Override
	public boolean realiserQuete(double reussite) {
		return 	   (reussite * rd.nextDouble() > difficulte) 
				&& (reussite * rd.nextDouble() > difficulte);
	}

	@Override
	public String toString() {
		return "QueteEpique [description=" + description + ", difficulte=" + difficulte + "]";
	}

	
	
}

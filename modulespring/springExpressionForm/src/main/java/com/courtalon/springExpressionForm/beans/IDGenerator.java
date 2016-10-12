package com.courtalon.springExpressionForm.beans;

public class IDGenerator {
	
	private int compteur;

	public IDGenerator(int compteur) {
		this.compteur = compteur;
	}
	
	public int getNextID() {
		return compteur++;
	}

}

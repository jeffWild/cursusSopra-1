package com.courtalon.springAcademyForm.beans;

import java.util.Random;

public class Public {

	private Random rd;

	public Public() {
		this.rd = new Random();
	}
	
	public void applaudirAvant(IArtiste a) {
		System.out.println("BRAVO, clap clap, hourra " + a.getNom());
	}
	
	public void applaudirApres(IArtiste a) {
		if (rd.nextBoolean()) {
			System.out.println("BRAVO, clap clap, bis " + a.getNom());
		}
		else {
			System.out.println("BOOOUHH, c'est nul " + a.getNom() + " (bruit de tomate volante)");
		}
	}
	
	
}

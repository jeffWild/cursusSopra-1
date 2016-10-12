package com.courtalon.springAcademyForm.beans;

import org.springframework.beans.factory.BeanNameAware;

public class Violon implements IInstrument {

	private String nom;
	
	public Violon(String nom) {
		this.nom = nom;
	}
	@Override
	public String getNom() {
		return nom;
	}
	@Override
	public void jouerInstrument() {
		System.out.println("wiooooonnnn, wiiiiiuuuu");
	}

}

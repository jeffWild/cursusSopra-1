package com.courtalon.springAcademyForm.beans;

import org.springframework.beans.factory.BeanNameAware;

public class Guitare implements IInstrument, BeanNameAware 
{
	private String nom;
	
	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public void jouerInstrument() {
		System.out.println("grooinng, gratgrat, griuung");
	}

	@Override
	public void setBeanName(String arg0) {
		this.nom = arg0;
	}
	
	public void accorder() {
		System.out.println("j'accorde la guitare " + getNom());
	}

}

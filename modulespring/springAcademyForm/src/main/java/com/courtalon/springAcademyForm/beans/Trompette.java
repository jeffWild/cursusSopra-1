package com.courtalon.springAcademyForm.beans;

import org.springframework.beans.factory.BeanNameAware;

// BeanNameAware indique a spring de nous injecter le nom du bean (ID) via
// la méthode setBeanName
public class Trompette implements IInstrument, BeanNameAware
{
	private String nom;
	
	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public void jouerInstrument() {
		System.out.println("POOUUUEETT PWOOOOO....");
	}

	@Override
	public void setBeanName(String arg0) {
		this.nom = arg0;
	}

}

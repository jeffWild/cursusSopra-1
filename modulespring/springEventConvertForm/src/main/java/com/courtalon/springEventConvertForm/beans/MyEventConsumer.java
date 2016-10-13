package com.courtalon.springEventConvertForm.beans;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MyEventConsumer implements ApplicationListener<ApplicationEvent>
{
	
	private String nom;
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}


	// ecouteur d'evenement
	@Override
	public void onApplicationEvent(ApplicationEvent evt) {
		System.out.println(getNom() + " a recu l'evenement " + evt);
	}

}

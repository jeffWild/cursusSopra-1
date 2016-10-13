package com.courtalon.springEventConvertForm.beans;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

// ApplicationContextAware indique a spring de nous injecter dans ce bean
// le contexte si on en a besoin
public class MyEventProducer implements ApplicationContextAware
{

	private String nom;
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}

	public void fireEvent() {
		ctx.publishEvent(new CustomEvent(this, "hello from " + getNom()));
	}
	
	private ApplicationContext ctx;
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		ctx = arg0;
	}
	
}

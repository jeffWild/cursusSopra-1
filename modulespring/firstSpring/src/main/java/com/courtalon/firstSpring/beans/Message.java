package com.courtalon.firstSpring.beans;


public class Message {
	private String titre;
	private String corps;
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		System.out.println("injection de " + titre + " dans le titre");
		this.titre = titre;
	}
	public String getCorps() {
		return corps;
	}
	public void setCorps(String corps) {
		System.out.println("injection de " + corps + " dans le corps");
		this.corps = corps;
	}
	
	public Message() {
		System.out.println("construction d'un message");
	}
	
	@Override
	public String toString() {
		return "Message [titre=" + titre + ", corps=" + corps + "]";
	}
	
	
}

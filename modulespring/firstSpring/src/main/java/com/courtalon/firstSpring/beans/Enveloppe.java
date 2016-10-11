package com.courtalon.firstSpring.beans;

public class Enveloppe {
	private String destinataire;
	private Message message;
	
	public String getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(String destinataire) {
		System.out.println("injection de " + destinataire + " dans destinataire");
		this.destinataire = destinataire;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		System.out.println("injection de " + message + " dans message");
		this.message = message;
	}
	
	public Enveloppe() {
		System.out.println("construction d'une enveloppe");
	}
	
	@Override
	public String toString() {
		return "Enveloppe [destinataire=" + destinataire + ", message=" + message + "]";
	}

}

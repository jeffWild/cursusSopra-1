package com.courtalon.springExpressionForm.beans;

public class Client {
	private int id;
	private String nom;
	private String email;
	private double solde;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public double getSolde() {return solde;}
	public void setSolde(double solde) {this.solde = solde;}
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", email=" + email + ", solde=" + solde + "]";
	}
	
}

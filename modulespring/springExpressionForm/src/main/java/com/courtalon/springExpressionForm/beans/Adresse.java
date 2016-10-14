package com.courtalon.springExpressionForm.beans;

public class Adresse {
	private String rue;
	private String ville;
	private String codePostal;
	private String pays;
	
	public String getRue() {return rue;}
	public void setRue(String rue) {this.rue = rue;}
	public String getVille() {return ville;}
	public void setVille(String ville) {this.ville = ville;}
	public String getCodePostal() {return codePostal;}
	public void setCodePostal(String codePostal) {this.codePostal = codePostal;}
	public String getPays() {return pays;}
	public void setPays(String pays) {this.pays = pays;}
	
	public Adresse() { this("", "","","");}
	public Adresse(String rue, String ville, String codePostal, String pays) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.pays = pays;
	}
	@Override
	public String toString() {
		return "Adresse [rue=" + rue + ", ville=" + ville + ", codePostal=" + codePostal + ", pays=" + pays + "]";
	}

}

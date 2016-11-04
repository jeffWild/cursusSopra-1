package com.courtalon.firstSpringBatchForm.beans;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="vente")
public class Vente {
	private int id;
	private BigDecimal ventes;
	private int quantite;
	private String nom;
	private Date date;
	
	@XmlAttribute(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name="totalvente")
	public BigDecimal getVentes() {
		return ventes;
	}
	public void setVentes(BigDecimal ventes) {
		this.ventes = ventes;
	}
	
	@XmlElement(name="qte")
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	@XmlElement(name="nomStaff")
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Vente() {}
	public Vente(int id, BigDecimal ventes, int quantite, String nom, Date date) {
		super();
		this.id = id;
		this.ventes = ventes;
		this.quantite = quantite;
		this.nom = nom;
		this.date = date;
	}
	
	

}

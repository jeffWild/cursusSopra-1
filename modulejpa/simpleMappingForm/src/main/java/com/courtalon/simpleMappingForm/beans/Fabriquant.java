package com.courtalon.simpleMappingForm.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Fabriquant {
	private int id;
	private String denomination;
	private Date dateEnregistrement;
	private Set<Produit> produits;
	
	
	@OneToMany(mappedBy="fabriquant"/*, fetch=FetchType.EAGER*/)
	public Set<Produit> getProduits() {
		if (produits == null)
			produits = new HashSet<>();
		return produits;
	}
	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getDenomination() {return denomination;}
	public void setDenomination(String denomination) {this.denomination = denomination;}
	
	@Temporal(TemporalType.DATE)
	public Date getDateEnregistrement() {return dateEnregistrement;}
	public void setDateEnregistrement(Date dateEnregistrement) {this.dateEnregistrement = dateEnregistrement;}
	
	public Fabriquant() { this(0, "", null);}
	public Fabriquant(int id, String denomination, Date dateEnregistrement) {
		super();
		this.id = id;
		this.denomination = denomination;
		this.dateEnregistrement = dateEnregistrement;
	}
	@Override
	public String toString() {
		return "Fabriquant [id=" + id + ", denomination=" + denomination + ", dateEnregistrement=" + dateEnregistrement
				+ "]";
	}
	
	
	
	
	

}

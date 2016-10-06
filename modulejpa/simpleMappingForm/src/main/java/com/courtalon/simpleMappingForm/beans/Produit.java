package com.courtalon.simpleMappingForm.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PROD")
public class Produit {
	
	private int id;
	private String nom;
	private double prix;
	private double poids;
	private int stock;
	private String categorie;
	private Fabriquant fabriquant;
	
	// ATTENTION, FetchType.LAZY est une suggestion
	// hibernate la respecte autant que possible, m'est pas obligé de le faire
	@ManyToOne(/*fetch=FetchType.LAZY*/ cascade=CascadeType.PERSIST )
	public Fabriquant getFabriquant() {return fabriquant;}
	public void setFabriquant(Fabriquant fabriquant) {this.fabriquant = fabriquant;}
	
	// ATTENTION, c'est le getter qui est annoté, et pas le setter
	// sinon on peut annoter aussi l'attribut a la place
	// ATTENTION, on ne peut mixer les 2 dans une meme classe
	// celui qui determine le choix, c'est @Id
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	@Column(name="denomination", length=100)
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = prix;}
	public double getPoids() {return poids;}
	public void setPoids(double poids) {this.poids = poids;}
	public int getStock() {return stock;}
	public void setStock(int stock) {this.stock = stock;}
	public String getCategorie() {return categorie;}
	public void setCategorie(String categorie) {this.categorie = categorie;}
	
	public Produit() { this(0, "", 0.0, 0.0, 0, "");}
	public Produit(int id,
					String nom,
					double prix,
					double poids,
					int stock,
					String categorie) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.poids = poids;
		this.stock = stock;
		this.categorie = categorie;
	}
	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + ", poids=" + poids + ", stock=" + stock
				+ ", categorie=" + categorie + "]";
	}
	
	
	
}

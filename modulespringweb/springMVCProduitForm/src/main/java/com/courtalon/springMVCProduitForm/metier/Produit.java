package com.courtalon.springMVCProduitForm.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.courtalon.springMVCProduitForm.utils.JsonPageable;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Produit {
	
	public static class ProduitView extends JsonPageable.PaginatedResult {}
	public static class ProduitAndImageView extends ProduitView {}
	
	@JsonView(ProduitView.class)
	private int id;
	@JsonView(ProduitView.class)
	private String nom;
	@JsonView(ProduitView.class)
	private double prix;
	@JsonView(ProduitView.class)
	private double poids;
	@JsonView(ProduitAndImageView.class)
	private Set<Image> images;
	
	
	@OneToMany(mappedBy="produit")
	public Set<Image> getImages() {
		if (images == null)
			images = new HashSet<>();
		return images;
	}
	public void setImages(Set<Image> images) {this.images = images;}
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids = poids;
	}
	
	public Produit() { this(0, "", 0.0, 0.0);}
	public Produit(int id, String nom, double prix, double poids) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.poids = poids;
	}
	
	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + ", poids=" + poids + "]";
	}
	
	
}

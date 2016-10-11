package com.courtalon.jpaWebForm.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {
	private int id;
	private String titre;
	private String corps;
	private Categorie categorie;
	
	
	@ManyToOne
	public Categorie getCategorie() {return categorie;}
	public void setCategorie(Categorie categorie) {this.categorie = categorie;}
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getCorps() {return corps;}
	public void setCorps(String corps) {this.corps = corps;}
	
	public Post() { this(0, "", "");}
	public Post(int id, String titre, String corps) {
		super();
		this.id = id;
		this.titre = titre;
		this.corps = corps;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", titre=" + titre + ", corps=" + corps + "]";
	}
	
	
}

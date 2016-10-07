package com.courtalon.manytomanyJpaForm.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Post {
	private int id;
	private String titre;
	private String corps;
	private int rating;
	private Set<Tag> tags;
	
	private GeoLocalisation coordonnees;
	
	@Embedded
	public GeoLocalisation getCoordonnees() {return coordonnees;}
	public void setCoordonnees(GeoLocalisation coordonnees) {this.coordonnees = coordonnees;}

	// fonction utilitaire pour ajouter un tag au post
	public void addTag(Tag t) {
		// n'ajouter que si on a bien un tag en parametre
		// et qu'il n'est pas déjà associé au post
		if (t != null && !getTags().contains(t)) {
			getTags().add(t); // celle-ci sera propagée en base
			t.getPosts().add(this); // celle-ci n'a d'effet qu'en mémoire
		}
	}
	
	// association N to N vers les Tags
	@ManyToMany(/*cascade=CascadeType.REMOVE*/)
	@JoinTable(name="etiquetages",		// permet de renommer la table de jointure
			joinColumns={@JoinColumn(name="CLE_POST")},
			inverseJoinColumns={@JoinColumn(name="CLE_TAG")}) 
	public Set<Tag> getTags() {
		if (tags == null)
			tags = new HashSet<>();
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getCorps() {return corps;}
	public void setCorps(String corps) {this.corps = corps;}
	public int getRating() {return rating;}
	public void setRating(int rating) {this.rating = rating;}
	
	public Post() { this(0, "", "", 0);}
	public Post(int id, String titre, String corps, int rating) {
		super();
		this.id = id;
		this.titre = titre;
		this.corps = corps;
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", titre=" + titre + ", corps=" + corps + ", rating=" + rating + "]";
	}
	
}

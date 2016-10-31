package com.courtalon.superGallerie.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	private int id;
	private String libelle;
	private boolean system;
	private Set<Image> images;
	
	@ManyToMany(mappedBy="tags")
	public Set<Image> getImages() {
		if (images == null)
			images = new HashSet<>();
		return images;
	}
	public void setImages(Set<Image> images) {
		this.images = images;
	}
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	@Column(length=100)
	public String getLibelle() {return libelle;}
	public void setLibelle(String libelle) {this.libelle = libelle;}
	
	public boolean isSystem() {return system;}
	public void setSystem(boolean system) {this.system = system;}
	
	public Tag() {this(0, "", false); }
	public Tag(int id, String libelle, boolean system) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.system = system;
	}
	

}

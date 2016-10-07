package com.courtalon.manytomanyJpaForm.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;

@Entity
public class Tag {
	private int id;
	private String libelle;
	private Set<Post> posts;
	
	// detagger les post etiquetés par ce tag si on le supprime
	// avant la supression du tag proprement dite
	@PreRemove
	public void removeFromPosts() {
		for (Post p : getPosts())
			p.getTags().remove(this);
	}
	
	
	// appelle la fonction coté maitre de l'association
	public void addPost(Post p) {
		if (p != null)
			p.addTag(this);
	}
	
	
	// en NtoN, il n'y a pas de coté "maitre" naturel
	// vu que les cle etrangers sont dans une table de jointure
	// tierce. Il faut cependant, pour JPA, choisir un coté
	// maitre de l'association, sur lequels pointera le mappedBy
	// de l'association "mirroir"
	// c'est un choix purement laissé a l'appréciation du développeur
	@ManyToMany(mappedBy="tags")
	public Set<Post> getPosts() {
		if (posts == null)
			posts = new HashSet<>();
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getLibelle() {return libelle;}
	public void setLibelle(String libelle) {this.libelle = libelle;}
	
	public Tag() { this(0, "");}
	public Tag(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
	@Override
	public String toString() {
		return "Tag [id=" + id + ", libelle=" + libelle + "]";
	}
	

}

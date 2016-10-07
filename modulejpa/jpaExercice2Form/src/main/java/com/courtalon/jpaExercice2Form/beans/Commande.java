package com.courtalon.jpaExercice2Form.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Commande {
	private int id;
	private String description;
	private double prix;
	private Date dateCommande;
	private Client client;
	
	
	@ManyToOne
	public Client getClient() {return client;}
	public void setClient(Client client) {this.client = client;}
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = prix;}
	
	@Temporal(TemporalType.DATE)
	public Date getDateCommande() {return dateCommande;}
	public void setDateCommande(Date dateCommande) {this.dateCommande = dateCommande;}
	
	public Commande() { this(0,"", 0.0, null);}
	public Commande(int id, String description, double prix, Date dateCommande) {
		super();
		this.id = id;
		this.description = description;
		this.prix = prix;
		this.dateCommande = dateCommande;
	}
	
	@Override
	public String toString() {
		return "Commande [id=" + id + ", description=" + description + ", prix=" + prix + ", dateCommande="
				+ dateCommande + "]";
	}
	

}

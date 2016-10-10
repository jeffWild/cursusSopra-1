package com.courtalon.manytomanyJpaForm.beans;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Trajet {
	private int id;
	private String nom;
	private GeoLocalisation depart;
	private GeoLocalisation arrive;
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	
	// pour eviter un conflit entre les definitions de colonnes pour depart et arrive
	// j'utilise attributeOverride pour "surcharger" la définition des colonnes
	// dans l'embedded inclus dans ce trajet
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="longitude", column=@Column(name="DEP_LONG")),
		@AttributeOverride(name="latitude", column=@Column(name="DEP_LAT"))
	})
	public GeoLocalisation getDepart() {return depart;}
	public void setDepart(GeoLocalisation depart) {this.depart = depart;}
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="longitude", column=@Column(name="ARR_LONG")),
		@AttributeOverride(name="latitude", column=@Column(name="ARR_LAT"))
	})public GeoLocalisation getArrive() {return arrive;}
	public void setArrive(GeoLocalisation arrive) {this.arrive = arrive;}
	
	public Trajet() { this(0, "", new GeoLocalisation(), new GeoLocalisation());}
	public Trajet(int id, String nom, GeoLocalisation depart, GeoLocalisation arrive) {
		super();
		this.id = id;
		this.nom = nom;
		this.depart = depart;
		this.arrive = arrive;
	}
	@Override
	public String toString() {
		return "Trajet [id=" + id + ", nom=" + nom + ", depart=" + depart + ", arrive=" + arrive + "]";
	}

}

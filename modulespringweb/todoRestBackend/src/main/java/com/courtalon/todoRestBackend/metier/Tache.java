package com.courtalon.todoRestBackend.metier;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tache {
	private int id;
	private String titre;
	private String contexte;
	private int priorite;
	private boolean termine;
	private Date dateLimite;
	
	@Id  @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getContexte() {return contexte;}
	public void setContexte(String contexte) {this.contexte = contexte;}
	public int getPriorite() {return priorite;}
	public void setPriorite(int priorite) {this.priorite = priorite;}
	public boolean isTermine() {return termine;}
	public void setTermine(boolean termine) {this.termine = termine;}
	
	@Temporal(TemporalType.DATE)
	public Date getDateLimite() {return dateLimite;}
	public void setDateLimite(Date dateLimite) {this.dateLimite = dateLimite;}
	
	public Tache() { this(0, "", "", 0, false, new Date());}
	public Tache(int id, String titre, String contexte, int priorite, boolean termine, Date dateLimite) {
		super();
		this.id = id;
		this.titre = titre;
		this.contexte = contexte;
		this.priorite = priorite;
		this.termine = termine;
		this.dateLimite = dateLimite;
	}
	
	
	

}

package com.courtalon.jpaExercice3Form.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cours {
	private int id;
	private String titre;
	private int capacite;
	private Date dateDebut;
	private Date dateFin;
	
	private Set<Etudiant> participants;
	private Professeur professeur;
	private Matiere matiere;

	public void addEtudiant(Etudiant et) {
		if (et != null && !getParticipants().contains(et)) {
			getParticipants().add(et); // cette ligne la provoque l'update dans la base
			et.getCourses().add(this);
		}
	}
	
	@ManyToOne
	public Professeur getProfesseur() {return professeur;}
	public void setProfesseur(Professeur professeur) {this.professeur = professeur;}
	@ManyToOne
	public Matiere getMatiere() {return matiere;}
	public void setMatiere(Matiere matiere) {this.matiere = matiere;}
	
	// coté maitre de la relation (pas de mappedBy)
	@ManyToMany
	public Set<Etudiant> getParticipants() {
		if (participants == null)
			participants = new HashSet<>();
		return participants;
	}
	public void setParticipants(Set<Etudiant> participants) {
		this.participants = participants;
	}
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	@Temporal(TemporalType.DATE)
	public Date getDateDebut() {return dateDebut;}
	public void setDateDebut(Date dateDebut) {this.dateDebut = dateDebut;}
	@Temporal(TemporalType.DATE)
	public Date getDateFin() {return dateFin;}
	public void setDateFin(Date dateFin) {this.dateFin = dateFin;}
	public int getCapacite() {return capacite;}
	public void setCapacite(int capacite) {this.capacite = capacite;}

	public Cours() { this(0, "", null, null, 0);}
	public Cours(int id, String titre, Date dateDebut, Date dateFin, int capacite) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.capacite = capacite;
	}

	@Override
	public String toString() {
		return "Cours [id=" + id + ", titre=" + titre + ", capacite=" + capacite + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + "]";
	}
	
	
	
	
}

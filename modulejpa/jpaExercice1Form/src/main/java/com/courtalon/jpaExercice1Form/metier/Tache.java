package com.courtalon.jpaExercice1Form.metier;

public class Tache {
	private int id;
	private String description;
	private int priorite;
	private String contexte;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public int getPriorite() {return priorite;}
	public void setPriorite(int priorite) {this.priorite = priorite;}
	public String getContexte() {return contexte;}
	public void setContexte(String contexte) {this.contexte = contexte;}
	
	public Tache() { this(0, "", 0, "");}
	public Tache(int id, String description, int priorite, String contexte) {
		super();
		this.id = id;
		this.description = description;
		this.priorite = priorite;
		this.contexte = contexte;
	}
	@Override
	public String toString() {
		return "Tache [id=" + id + ", description=" + description + ", priorite=" + priorite + ", contexte=" + contexte
				+ "]";
	}

}

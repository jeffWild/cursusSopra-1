package com.courtalon.springweb1_exo1Form.metier;

import java.util.Date;

public class Intervention {
	private int id;
	private String intervenant;
	private String lieu;
	private String noMateriel;
	private String description;
	private Date dateProgrammee;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIntervenant() {
		return intervenant;
	}
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getNoMateriel() {
		return noMateriel;
	}
	public void setNoMateriel(String noMateriel) {
		this.noMateriel = noMateriel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateProgrammee() {
		return dateProgrammee;
	}
	public void setDateProgrammee(Date dateProgrammee) {
		this.dateProgrammee = dateProgrammee;
	}
	
	public Intervention() { this(0, "", "", "", "", new Date());}
	public Intervention(int id, String intervenant, String lieu, String noMateriel, String description,
			Date dateProgrammee) {
		super();
		this.id = id;
		this.intervenant = intervenant;
		this.lieu = lieu;
		this.noMateriel = noMateriel;
		this.description = description;
		this.dateProgrammee = dateProgrammee;
	}

	@Override
	public String toString() {
		return "Intervention [id=" + id + ", intervenant=" + intervenant + ", lieu=" + lieu + ", noMateriel="
				+ noMateriel + ", description=" + description + ", dateProgrammee=" + dateProgrammee + "]";
	}

}

package com.courtalon.firstSpringMvcForm.metier;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Message {
	private int id;
	private String titre;
	private String corps;
	private String email;
	private Date dateMessage;
	
	@Column(length=100)
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	@Temporal(TemporalType.DATE)
	public Date getDateMessage() {return dateMessage;}
	public void setDateMessage(Date dateMessage) {this.dateMessage = dateMessage;}
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	@Column(length=60)
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	
	@Column(length=500)
	public String getCorps() {return corps;}
	public void setCorps(String corps) {this.corps = corps;}
	
	public Message() { this(0, "", "", "", new Date());}
	public Message(int id, String titre, String corps, String email, Date dateMessage) {
		super();
		this.id = id;
		this.titre = titre;
		this.corps = corps;
		this.email = email;
		this.dateMessage = dateMessage;
	}

	
	
	

}

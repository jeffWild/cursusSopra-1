package com.courtalon.superGallerie.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Image {
	private int id;
	private String name; // nomage de l'image dans mon application
	private String fileName;
	private String contentType;
	private long fileSize;
	
	private Set<Tag> tags;
	
	@ManyToMany
	public Set<Tag> getTags() {
		if (tags == null)
			tags = new HashSet<>();
		return tags;
		
	}
	public void setTags(Set<Tag> tags) {this.tags = tags;}
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	@Column(length=100)
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	@Column(length=200)	
	public String getFileName() {return fileName;}
	public void setFileName(String fileName) {this.fileName = fileName;}
	
	@Column(length=80)
	public String getContentType() {return contentType;}
	public void setContentType(String contentType) {this.contentType = contentType;}
	
	public long getFileSize() {return fileSize;}
	public void setFileSize(long fileSize) {this.fileSize = fileSize;}
	
	public Image() { this(0, "", "", "", 0);}
	public Image(int id, String name, String fileName, String contentType, long fileSize) {
		super();
		this.id = id;
		this.name = name;
		this.fileName = fileName;
		this.contentType = contentType;
		this.fileSize = fileSize;
	}
	
	
	/*
	@Lob
	@Column(length=2*1024*1024)
	private byte[] binaryData;
	*/
	
	
	
}

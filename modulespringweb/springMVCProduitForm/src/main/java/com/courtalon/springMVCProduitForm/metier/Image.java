package com.courtalon.springMVCProduitForm.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.courtalon.springMVCProduitForm.metier.Produit.ProduitAndImageView;
import com.courtalon.springMVCProduitForm.utils.JsonPageable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Image {
	
	public  static class ImageView extends JsonPageable.PaginatedResult {
		// vue restreinte
	}
	public static class ImageVIewExtended extends ImageView {
		// vue complete
	}
	
	@JsonView({ImageView.class,ProduitAndImageView.class})
	private int id;
	@JsonView({ImageView.class,ProduitAndImageView.class})
	private String fileName;
	@JsonView(ImageView.class)
	private String contentType;
	@JsonView(ImageView.class)
	private long fileSize;
	@JsonView(ImageVIewExtended.class)
	private Produit produit;

	@ManyToOne
	public Produit getProduit() {return produit;}
	public void setProduit(Produit produit) {this.produit = produit;}
	
	@Id @GeneratedValue
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	@Column(length=200)	
	public String getFileName() {return fileName;}
	public void setFileName(String fileName) {this.fileName = fileName;}
	
	@Column(length=80)
	public String getContentType() {return contentType;}
	public void setContentType(String contentType) {this.contentType = contentType;}
	
	public long getFileSize() {return fileSize;}
	public void setFileSize(long fileSize) {this.fileSize = fileSize;}
	
	public Image() { this(0, "", "", 0);}
	public Image(int id, String fileName, String contentType, long fileSize) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.contentType = contentType;
		this.fileSize = fileSize;
	}
	
	
	
	
}

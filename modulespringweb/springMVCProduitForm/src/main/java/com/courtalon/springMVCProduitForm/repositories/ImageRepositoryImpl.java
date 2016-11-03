package com.courtalon.springMVCProduitForm.repositories;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.courtalon.springMVCProduitForm.utils.FileStorageManager;


public class ImageRepositoryImpl implements ImageRepositoryCustom {

	private static Logger log = LogManager.getLogger(ImageRepositoryImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	public EntityManager getEm() {	return em;}
	public void setEm(EntityManager em) {this.em = em;}


	@Autowired
	private FileStorageManager fileStorageManager;
	public FileStorageManager getFileStorageManager() {return fileStorageManager;}
	public void setFileStorageManager(FileStorageManager fileStorageManager) {this.fileStorageManager = fileStorageManager;}

	@Override
	public boolean saveImageFile(int id, File f) {
		return getFileStorageManager().saveFile("ProduitImage", id, f);
	}

	@Override
	public boolean saveImageFile(int id, InputStream s) {
		return getFileStorageManager().saveFile("ProduitImage", id, s);
	}

	@Override
	public File getImageFile(int id) {
		return getFileStorageManager().getFile("ProduitImage", id);
	}
	
	
}

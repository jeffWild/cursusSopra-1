package com.courtalon.springMVCProduitForm.repositories;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.courtalon.springMVCProduitForm.metier.Image;



public interface ImageRepositoryCustom {
	
	boolean saveImageFile(int id, File f);
	boolean saveImageFile(int id, InputStream s);
	
	File getImageFile(int id);
	
	
}

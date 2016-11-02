package com.courtalon.superGallerie.repositories;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.courtalon.superGallerie.metier.Image;

public interface ImageRepositoryCustom {
	
	boolean saveImageFile(int id, File f);
	boolean saveImageFile(int id, InputStream s);
	
	File getImageFile(int id);
	
	Page<Image> searchImageWithTags(Pageable p, List<Integer> tagsId);
	
}

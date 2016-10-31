package com.courtalon.superGallerie.repositories;

import java.io.File;
import java.io.InputStream;

public interface ImageRepositoryCustom {
	
	boolean saveImageFile(int id, File f);
	boolean saveImageFile(int id, InputStream s);
	
	File getImageFile(int id);
	
}

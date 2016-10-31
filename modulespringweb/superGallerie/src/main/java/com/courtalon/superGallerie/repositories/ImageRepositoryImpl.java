package com.courtalon.superGallerie.repositories;

import java.io.File;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.courtalon.superGallerie.utils.FileStorageManager;

public class ImageRepositoryImpl implements ImageRepositoryCustom {

	@Autowired
	private FileStorageManager fileStorageManager;
	public FileStorageManager getFileStorageManager() {return fileStorageManager;}
	public void setFileStorageManager(FileStorageManager fileStorageManager) {this.fileStorageManager = fileStorageManager;}

	@Override
	public boolean saveImageFile(int id, File f) {
		return getFileStorageManager().saveFile("Image", id, f);
	}

	@Override
	public boolean saveImageFile(int id, InputStream s) {
		return getFileStorageManager().saveFile("Image", id, s);
	}

	@Override
	public File getImageFile(int id) {
		return getFileStorageManager().getFile("Image", id);
	}

}

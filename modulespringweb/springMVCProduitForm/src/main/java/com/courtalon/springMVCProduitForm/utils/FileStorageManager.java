package com.courtalon.springMVCProduitForm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FileStorageManager {
	private static Logger log = LogManager.getLogger(FileStorageManager.class);
	
	private File storageRoot;
	public File getStorageRoot() {return storageRoot;}
	public void setStorageRoot(File storageRoot) {this.storageRoot = storageRoot;}

	
	public File getFile(String entityName, int id) {
		if (storageRoot == null
				|| !storageRoot.exists()
				|| !storageRoot.isDirectory()) {
				log.error("storageRoot not available for FileSTorageManager");
				return null;
		}
		String md5name = DigestUtils.md5Hex(entityName);
		File entityRep = new File(storageRoot.getAbsolutePath()
								  + File.separatorChar
								  + md5name);
		if (!entityRep.exists() || !entityRep.isDirectory()) {
			log.error("unkwown file in FileSTorageManager");
			return null;
		}
		File f = new File(entityRep.getAbsolutePath() 
						+ File.separatorChar
						+ String.format("file_%07d", id));
		if (f.exists() && f.isFile()) {
			return f;
		}
		else
			return null;
	}
	
	
	public boolean saveFile(String entityName, int id, File f) {
		try {
			return saveFile(entityName, id, new FileInputStream(f));
		} catch (FileNotFoundException e) {
			log.error(e);
			return false;
		}
	}
	
	public boolean saveFile(String entityName, int id, InputStream data) {
		if (storageRoot == null
			|| !storageRoot.exists()
			|| !storageRoot.isDirectory()) {
			log.error("storageRoot not available for FileSTorageManager");
			return false;
		}
		String md5name = DigestUtils.md5Hex(entityName);
		// File.separatorChar est le caractere de séparation dans les chemin de fichiers
		// c.a.d / sous linux, et \ sous windows
		File entityRep = new File(storageRoot.getAbsolutePath()
								  + File.separatorChar
								  + md5name);
		if (!entityRep.exists())
			entityRep.mkdirs();
		
		if (!entityRep.isDirectory()) {
			log.error("unable to create directory for entity in FileSTorageManager");
			return false;
		}
		
		try {
			// Files.copy permet de copier "efficacement" et simplement un fichier
			// Paths.get permet de construie un chemin, sans se soucier de la syntaxe
			// il prend une liste de chemin a concatener et a reduire en fonction
			// du systeme de fichier
			// le dernier parametre indique d'ecraser le fichier s'il existe déjà
			Files.copy(data,
					Paths.get(entityRep.getAbsolutePath(),
							String.format("file_%07d",  id)),
					java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			log.error(e);
			return false;
		}
		return true;
	}
	
}

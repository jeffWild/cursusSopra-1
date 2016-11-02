package com.courtalon.superGallerie.repositories;

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

import com.courtalon.superGallerie.metier.Image;
import com.courtalon.superGallerie.utils.FileStorageManager;
import com.courtalon.superGallerie.web.ImageController;

public class ImageRepositoryImpl implements ImageRepositoryCustom {

	private static Logger log = LogManager.getLogger(ImageRepositoryImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	public EntityManager getEm() {	return em;}
	public void setEm(EntityManager em) {this.em = em;}

	// attention, ne marche pas si sur getter dans ce cas???
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
	
	@Transactional
	@Override
	public Page<Image> searchImageWithTags(Pageable p, List<Integer> tagsId) {
		String countRequest = "select count(i.id) from Image as i";
		String dataRequest = "select DISTINCT i from Image as i";
		
		StringBuilder sb = new StringBuilder();
		if (tagsId.size() > 0) {
			int nb_params = tagsId.size();
			for (int i = 1; i <= nb_params; i++) {
				sb.append(", IN(i.tags) ta");
				sb.append(i);
			}
			sb.append(" WHERE ");
			for (int i = 1; i <= nb_params; i++) {
				// WHERE ta1.id = :tp1 AND ta2.id = :tp2 ...
				if (i > 1)
					sb.append(" AND ");
				sb.append(" ta");
				sb.append(i);
				sb.append(".id = :tp");
				sb.append(i);
			}
		}
		countRequest += sb.toString();
		dataRequest += sb.toString();
		log.info(dataRequest);
		log.info(countRequest);
		
		TypedQuery<Image> qData = getEm().createQuery(dataRequest, Image.class);
		TypedQuery<Long> qCount = getEm().createQuery(countRequest, Long.class);
		int index_param = 1;
		for (int tid : tagsId) {
			qData.setParameter("tp" + index_param, tid);
			qCount.setParameter("tp" + index_param, tid);
			index_param++;
		}
		// gestion de la pagination
		qData.setFirstResult(p.getOffset());
		qData.setMaxResults(p.getPageSize());
		// 3 parametres
		// le contenu de la page (les images)
		// l'objet pagination
		// le decompte total des images de la table
		return new PageImpl<Image>(qData.getResultList(), p, qCount.getSingleResult());
	}

}

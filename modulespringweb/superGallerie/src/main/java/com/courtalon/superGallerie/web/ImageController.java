package com.courtalon.superGallerie.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.courtalon.superGallerie.metier.Image;
import com.courtalon.superGallerie.repositories.ImageRepository;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping("/")
public class ImageController {
	private static Logger log = LogManager.getLogger(ImageController.class);
	
	private ImageRepository imageRepository;
	@Autowired
	public ImageRepository getImageRepository() {return imageRepository;}
	public void setImageRepository(ImageRepository imageRepository) {this.imageRepository = imageRepository;}

	@RequestMapping(value="images",
					method=RequestMethod.GET,
					produces="application/json")
	@ResponseBody
	public Page<Image> liste(@PageableDefault(page=0, size=12) Pageable pageRequest){
		return getImageRepository().findAll(pageRequest);	
	}
	@RequestMapping(value="images/upload",
					method=RequestMethod.POST,
					produces="application/json")
	@ResponseBody
	public Image upload(@RequestParam("fichier") MultipartFile fichier) {
		log.info("nom fichier: " + fichier.getOriginalFilename());
		log.info("taille fichier: " + fichier.getSize());
		log.info("type contenu: " + fichier.getContentType());

		Image img = new Image(0, fichier.getOriginalFilename(),
						fichier.getOriginalFilename(),
						fichier.getContentType(),
						fichier.getSize());
		
		img = getImageRepository().save(img);
		try {
			getImageRepository().saveImageFile(img.getId(), fichier.getInputStream());
		} catch (IOException e) {log.error(e);}
		
		return img;
	}
	
}

package com.courtalon.springMVCProduitForm.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.courtalon.springMVCProduitForm.metier.Image;
import com.courtalon.springMVCProduitForm.metier.Image.ImageVIewExtended;
import com.courtalon.springMVCProduitForm.metier.Image.ImageView;
import com.courtalon.springMVCProduitForm.metier.Produit;
import com.courtalon.springMVCProduitForm.repositories.ImageRepository;
import com.courtalon.springMVCProduitForm.repositories.ProduitRepository;
import com.courtalon.springMVCProduitForm.utils.JsonPageable;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping("/")
public class ImageController {
	private static Logger log = LogManager.getLogger(ImageController.class);

	@Autowired
	private ImageRepository imageRepository;
	public ImageRepository getImageRepository() {return imageRepository;}
	public void setImageRepository(ImageRepository imageRepository) {this.imageRepository = imageRepository;}

	@Autowired
	private ProduitRepository produitRepository;
	public ProduitRepository getProduitRepository() {return produitRepository;}
	public void setProduitRepository(ProduitRepository produitRepository) {this.produitRepository = produitRepository;}
	
	@RequestMapping(value="images",
					method=RequestMethod.GET,
					produces="application/json")
	@ResponseBody
	@JsonView(ImageView.class)
	public Page<Image> liste(@PageableDefault(page=0, size=12) Pageable pageRequest){
		JsonPageable<Image> p = JsonPageable.fromPage(getImageRepository().findAll(pageRequest));
		log.info("no page = " + p.getNumber());
		log.info("taille page = " + p.getSize());
		return 	p;
	}
	

	
	@RequestMapping(value="images/upload",
					method=RequestMethod.POST,
					produces="application/json")
	@ResponseBody
	@JsonView(ImageView.class)
	public Image upload(@RequestParam("fichier") MultipartFile fichier,
						@RequestParam("pid") int pid) {
		log.info("nom fichier: " + fichier.getOriginalFilename());
		log.info("taille fichier: " + fichier.getSize());
		log.info("type contenu: " + fichier.getContentType());
		log.info("id produit: " + pid);
		
		Image img = new Image(0,
						fichier.getOriginalFilename(),
						fichier.getContentType(),
						fichier.getSize());
		Produit p = getProduitRepository().findOne(pid);
		img.setProduit(p);
		
		img = getImageRepository().save(img);
		try {
			getImageRepository().saveImageFile(img.getId(), fichier.getInputStream());
		} catch (IOException e) {log.error(e);}
		
		return img;
	}
	
	@RequestMapping(value="images/{id:[0-9]+}",
			method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(ImageVIewExtended.class)
	public Image findImage(@PathVariable("id") int id) {
		log.info("id image: " + id);
		Image img = getImageRepository().findOne(id);
		return img;
	}
	
	
	@RequestMapping(value="images/data/{id:[0-9]+}",
					method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FileSystemResource> imageData(@PathVariable("id") int id) {
		log.info("id image: " + id);
		Image img = getImageRepository().findOne(id);
		log.info("image = " + img);
		// configure les meta-informations sur le fichier, nom, taille, type...
		HttpHeaders responseHeaders = new HttpHeaders();
		log.info("ct = " + MediaType.parseMediaType(img.getContentType()));
		responseHeaders.setContentType(MediaType.parseMediaType(img.getContentType()));
		responseHeaders.setContentLength(img.getFileSize());
		responseHeaders.add("Content-Disposition", "attachment; filename=\"" + img.getFileName() + "\"");
				//responseHeaders.setContentDispositionFormData("name=\"attachment\"", img.getFileName());
		
		File f = getImageRepository().getImageFile(img.getId());
		
		log.info("image file = " + f);
		
		if (f != null) {
			// renvoie le fichier, + les meta-information et le code de retour 200
			FileSystemResource fsr = new FileSystemResource(f);
			return new ResponseEntity<FileSystemResource>(fsr,
														  responseHeaders,
														  HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}

package com.courtalon.springMVCProduitForm.web;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.courtalon.springMVCProduitForm.metier.Produit;
import com.courtalon.springMVCProduitForm.repositories.ProduitRepository;

@Controller
@RequestMapping(value="/")
public class IndexController {

	private ProduitRepository produitRepository;
	
	@Autowired
	public ProduitRepository getProduitRepository() {return produitRepository;}
	public void setProduitRepository(ProduitRepository produitRepository) {this.produitRepository = produitRepository;}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirectToIndex() {
		return "redirect:/Index";
	}

	
	@RequestMapping(value = "/Index", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "bonjour depuis spring 3 mvc");
		return "bonjour";
	}
	
	
	@RequestMapping(value="/produit", method= RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Page<Produit> liste(
			@RequestParam(value= "noPage", required=false, defaultValue="0") int noPage,
			@RequestParam(value= "taillePage", required=false, defaultValue="5") int taillePage
			) {
		return getProduitRepository().findAll(new PageRequest(noPage, taillePage));
	}
	
	@RequestMapping(value="/produit/filtreNom/{nom}", method= RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Page<Produit> listeParNom(
			@PathVariable(value="nom") String nom,
			@RequestParam(value= "noPage", required=false, defaultValue="0") int noPage,
			@RequestParam(value= "taillePage", required=false, defaultValue="5") int taillePage
			) {
		return getProduitRepository().findByNomContaining(nom, new PageRequest(noPage, taillePage));
	}
	
	@RequestMapping(value="/produit/filtrePrix/{prix}", method= RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Page<Produit> listeParPrix(
			@PathVariable(value="prix") double prix,
			@RequestParam(value= "noPage", required=false, defaultValue="0") int noPage,
			@RequestParam(value= "taillePage", required=false, defaultValue="5") int taillePage
			) {
		return getProduitRepository().findByPrixGreaterThanEqual(prix, new PageRequest(noPage, taillePage));
	}
	
	@RequestMapping(value="/produit/filtreNomEtPrix/{nom}/{prix}", method= RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Page<Produit> listeParNomEtPrix(
			@PathVariable(value="nom") String nom,
			@PathVariable(value="prix") double prix,
			@RequestParam(value= "noPage", required=false, defaultValue="0") int noPage,
			@RequestParam(value= "taillePage", required=false, defaultValue="5") int taillePage
			) {
		return getProduitRepository().findByNomContainingAndPrixGreaterThanEqual(
				nom, prix, new PageRequest(noPage, taillePage));
	}
	
	@RequestMapping(value="/produit/save",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Produit saveProduit(@RequestBody Produit p) {
		// l'annotation @RequestBody va automatiquement
		// convertir l'objet json transmit dans le corp de la requette
		// en objet Produit grace à jackson
		return getProduitRepository().save(p);
	}

	@RequestMapping(value="/produit/find/{id}",
					method =RequestMethod.GET,
					produces="application/json")
	@ResponseBody
	public Produit findProduit(@PathVariable(value="id") int id) {
		return getProduitRepository().findOne(id);
	}

	@RequestMapping(value="/produit/remove/{id}",
			method =RequestMethod.POST,
			produces="application/json")
	@ResponseBody
	public Produit deleteProduit(@PathVariable(value="id") int id) {
		Produit p = getProduitRepository().findOne(id);
		getProduitRepository().delete(p);
		return p;
	}
	
}

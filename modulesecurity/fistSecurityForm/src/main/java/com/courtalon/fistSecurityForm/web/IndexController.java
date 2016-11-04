package com.courtalon.fistSecurityForm.web;

import java.security.Principal;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.courtalon.fistSecurityForm.metier.Utilisateur;
import com.courtalon.fistSecurityForm.repositories.DroitRepository;
import com.courtalon.fistSecurityForm.repositories.UtilisateurRepository;

@Controller
@RequestMapping(value="/")
public class IndexController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	public UtilisateurRepository getUtilisateurRepository() {return utilisateurRepository;}
	public void setUtilisateurRepository(UtilisateurRepository utilisateurRepository) {this.utilisateurRepository = utilisateurRepository;}

	@Autowired
	private DroitRepository droitRepository;
	public DroitRepository getDroitRepository() {return droitRepository;}
	public void setDroitRepository(DroitRepository droitRepository) {this.droitRepository = droitRepository;}
	
	@RequestMapping(value="/admin/", method=RequestMethod.GET)
	public ModelAndView adminArea(Principal utilisateur) {
		ModelAndView mv = new ModelAndView("welcome");
		mv.addObject("message", "bienvenue venerable admin : " + utilisateur.getName());
		
		return mv;
	}

	@RequestMapping(value="/user/", method=RequestMethod.GET)
	public ModelAndView userArea(Principal utilisateur) {
		ModelAndView mv = new ModelAndView("welcome");
		mv.addObject("message", "bienvenue cher utilisateur : " + utilisateur.getName());
		return mv;
	}
	
	@RequestMapping(value="/public/", method=RequestMethod.GET)
	public ModelAndView publicArea(Principal utilisateur) {
		ModelAndView mv = new ModelAndView("welcome");
		mv.addObject("utilisateur", new Utilisateur());
		if (utilisateur != null)
			mv.addObject("message", "ouuiiii, c'est pour quoi? : " + utilisateur.getName());
		else
			mv.addObject("message", "ouuiiii, c'est pour quoi?");
		return mv;
	}
	
	@RequestMapping(value="/public/register", method=RequestMethod.POST)
	public ModelAndView register(@RequestParam String name,
								@RequestParam String password) {
		PasswordEncoder pe = new BCryptPasswordEncoder();
		
		Utilisateur utilisateur = new Utilisateur(0, name, pe.encode(password),true); 
		utilisateur.getDroits().add(droitRepository.findByDroit("ROLE_USER"));
		
		utilisateurRepository.save(utilisateur);
		
		ModelAndView mv = new ModelAndView("welcome");
		mv.addObject("message", "utilisateur " + utilisateur.getName() + " cree");
		return mv;
	}

}
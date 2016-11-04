package com.courtalon.fistSecurityForm.web;

import java.security.Principal;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/")
public class IndexController {

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
		if (utilisateur != null)
			mv.addObject("message", "ouuiiii, c'est pour quoi? : " + utilisateur.getName());
		else
			mv.addObject("message", "ouuiiii, c'est pour quoi?");
		return mv;
	}

}
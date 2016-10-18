package com.courtalon.firstSpringMvcForm.web;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.courtalon.firstSpringMvcForm.metier.Message;

@Controller
@RequestMapping(value="/")
public class IndexController {

	@RequestMapping(value = "/Index", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "bonjour depuis spring 3 mvc");
		return "bonjour";

	}

	@RequestMapping(value = "/bonjour/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("bonjour");
		model.addObject("message", "bonjour " + name);

		return model;
	}
	
	@RequestMapping(value= "/add/{op1}/{op2}", method = RequestMethod.GET)
	public ModelAndView addition(@PathVariable("op1")int operande1,
								@PathVariable("op2")int operande2){
		ModelAndView model = new ModelAndView();
		model.setViewName("bonjour");
		model.addObject("message", "resultat = " + (operande1 + operande2));
		
		return model;
	}
	
	@RequestMapping(value= "/add", method= RequestMethod.POST)
	public ModelAndView addition2(@RequestParam("oper1") int operande1,
								  @RequestParam("oper2") int operande2) {
		ModelAndView model = new ModelAndView();
		model.setViewName("bonjour");
		model.addObject("message", "resultat = " + (operande1 + operande2));
		
		return model;
	}
	
	@RequestMapping(value="/saisie", method = RequestMethod.GET)
	public ModelAndView afficheForm() {
		ModelAndView model = new ModelAndView();
		model.setViewName("saisie");
		model.addObject("message", new Message(1, "bonjour", "bien belle journee"));
		model.addObject("infos", "rien de saisit");
		
		return model;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("message") Message message, Model model) {
		model.addAttribute("infos", "message saisie : " + message.getTitre());
		model.addAttribute("message", message);
		return "saisie";
	}
	

}
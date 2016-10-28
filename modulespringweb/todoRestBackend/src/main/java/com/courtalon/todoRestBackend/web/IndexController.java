package com.courtalon.todoRestBackend.web;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.courtalon.todoRestBackend.metier.Tache;
import com.courtalon.todoRestBackend.repositories.TacheRepository;

@Controller
@RequestMapping(value="/")
public class IndexController {

	private TacheRepository tacheRepository;
	@Autowired
	public TacheRepository getTacheRepository() {return tacheRepository;}
	public void setTacheRepository(TacheRepository tacheRepository) {this.tacheRepository = tacheRepository;}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirectToIndex() {
		return "redirect:/Index";
	}

	
	@RequestMapping(value = "/Index", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "bonjour depuis spring 3 mvc");
		return "bonjour";

	}

	@RequestMapping(value= "/taches", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Page<Tache> liste(@RequestParam(required=false, defaultValue="0") int pageNo,
							@RequestParam(required=false, defaultValue="5")	int taillePage){
		return getTacheRepository().findAll(new PageRequest(pageNo, taillePage));
	}
	

}
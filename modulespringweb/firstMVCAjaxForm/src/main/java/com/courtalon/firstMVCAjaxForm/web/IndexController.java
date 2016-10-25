package com.courtalon.firstMVCAjaxForm.web;

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

import com.courtalon.firstMVCAjaxForm.metier.Message;
import com.courtalon.firstMVCAjaxForm.repositories.MessageRepository;

@Controller
@RequestMapping(value="/")
public class IndexController {

	private MessageRepository MessageRepository;
	@Autowired
	public MessageRepository getMessageRepository() {return MessageRepository;}
	public void setMessageRepository(MessageRepository messageRepository) {MessageRepository = messageRepository;}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirectToIndex() {
		return "redirect:/Index";
	}

	@RequestMapping(value = "/Index", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "bonjour depuis spring 3 mvc");
		return "bonjour";
	}
	
	@RequestMapping(value= "/message",
					method = RequestMethod.GET,
					produces = "application/json")
	@ResponseBody
	public Page<Message> liste(
			@RequestParam(value="noPage",defaultValue="0", required=false) int noPage,
			@RequestParam(value="taillePage", defaultValue="10", required=false) int taillePage) 
	{
		PageRequest pr = new PageRequest(noPage, taillePage);
		return getMessageRepository().findAll(pr); //.getContent();
	}
	
	@RequestMapping(value= "/message/titre/{titre}",
			method = RequestMethod.GET,
			produces = "application/json")	
	@ResponseBody
	public Page<Message> listeByTitre(
			@PathVariable(value="titre") String titre,
			@RequestParam(value="noPage",defaultValue="0", required=false) int noPage,
			@RequestParam(value="taillePage", defaultValue="10", required=false) int taillePage) 
	{
		PageRequest pr = new PageRequest(noPage, taillePage);
		//return getMessageRepository().findAll(pr); 
		return getMessageRepository().findByTitreContaining(titre, pr);
	}
	
}
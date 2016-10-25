package com.courtalon.firstMVCAjaxForm.web;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public Iterable<Message> liste() {
		return getMessageRepository().findAll();
	}
}
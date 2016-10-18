package com.courtalon.firstSpringMvcForm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.courtalon.firstSpringMvcForm.repositories.IMessageDAO;

@Controller
@RequestMapping("/message")
public class MessageController {

	private IMessageDAO messageDAO;
	@Autowired
	public IMessageDAO getMessageDAO() {return messageDAO;}
	public void setMessageDAO(IMessageDAO messageDAO) {this.messageDAO = messageDAO;}
	
	@RequestMapping(value="/liste", method= RequestMethod.GET)
	public ModelAndView liste() {
		ModelAndView model = new ModelAndView();
		model.setViewName("liste");
		model.addObject("messages", messageDAO.findAll());
		
		return model;
	}
	
}

package com.courtalon.firstSpringMvcForm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.courtalon.firstSpringMvcForm.metier.Message;
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
	
	@RequestMapping(value="/add", method= RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView model = new ModelAndView();
		model.setViewName("formMessage");
		model.addObject("message", new Message(0, "nouveau titre", "nouveau corps"));
		
		return model;
	}
	
	@RequestMapping(value="/edit/{mid}", method= RequestMethod.GET)
	public ModelAndView edit(@PathVariable("mid") int mid,
							RedirectAttributes redirectAttribute) {
		ModelAndView model = new ModelAndView();
		Message message = messageDAO.findByID(mid);
		if (message == null) {
			redirectAttribute.addFlashAttribute("css", "danger");
			redirectAttribute.addFlashAttribute("msg", "tweet inconnu");
			model.setViewName("redirect:/message/liste");
		}
		else  {
			model.setViewName("formMessage");
			model.addObject("message", message);
		}
		
		return model;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@ModelAttribute("message") Message message,
						RedirectAttributes redirectAttribute) {
		messageDAO.save(message);
		redirectAttribute.addFlashAttribute("css", "success");
		redirectAttribute.addFlashAttribute("msg",
											"tweet no " + message.getId() + " edité");
		
		return "redirect:/message/liste";
	}
	
	
	@RequestMapping(value="/delete/{mid}", method=RequestMethod.GET)
	public String remove(@PathVariable("mid") int mid,
						RedirectAttributes redirectAttribute) {
		messageDAO.remove(mid);
		redirectAttribute.addFlashAttribute("css", "success");
		redirectAttribute.addFlashAttribute("msg", "tweet no " + mid + " effacé");
		
		return "redirect:/message/liste";
	}
	
}

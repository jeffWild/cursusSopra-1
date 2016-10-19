package com.courtalon.firstSpringMvcForm.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
public class MessageController
{

	// injection de notre validateur de message
	// lui-même déclaré dans spring via @Component
	// on aurait put aussi le déclarer dans le fichier xml
	@Autowired
	private MessageValidator messageValidator;
	public MessageValidator getMessageValidator() {return messageValidator;}
	public void setMessageValidator(MessageValidator messageValidator) {this.messageValidator = messageValidator;}

	// cette méthode choisi le validateur a utiliser
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(messageValidator);
		// pour gérer les dates dans votre formulaire
		// j'utilise un SimpleDateFormat
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(true);
		// que j'enregistre auprès du binder comme
		// convertisseur pour les Dates
		// le parametre a false détermine si on accepte les dates vides
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}

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
		model.addObject("message", new Message(0,
												"nouveau titre",
												"nouveau corps",
												"pasemail@none.com",
												new Date()));
		
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
	
	// ajout de l'annotation  @Validated pour activer la validation
	// sur l'argument message
	// l'argument BindingResult sera injécté par spring
	// il contient le résultat de la validation
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@ModelAttribute("message") @Validated Message message,
						BindingResult result,
						Model model,
						RedirectAttributes redirectAttribute) {
		if (result.hasErrors()) {
			return "formMessage";
		}
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

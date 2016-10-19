package com.courtalon.springMvcExo3Form.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.courtalon.springMvcExo3Form.metier.Employe;
import com.courtalon.springMvcExo3Form.repositories.IDepartementDAO;
import com.courtalon.springMvcExo3Form.repositories.IEmployeDAO;

@Controller
@RequestMapping("/employe")
public class EmployeController {
	
	private EmployeValidator employeValidator;
	@Autowired
	public EmployeValidator getEmployeValidator() {return employeValidator;}
	public void setEmployeValidator(EmployeValidator employeValidator) {this.employeValidator = employeValidator;}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(employeValidator);
	}
	

	private IEmployeDAO employeDAO;
	
	@Autowired
	public IEmployeDAO getEmployeDAO() {return employeDAO;}
	public void setEmployeDAO(IEmployeDAO employeDAO) {this.employeDAO = employeDAO;}

	
	private IDepartementDAO departementDAO;
	
	@Autowired
	public IDepartementDAO getDepartementDAO() {return departementDAO;}
	public void setDepartementDAO(IDepartementDAO departementDAO) {this.departementDAO = departementDAO;}
	

	@RequestMapping(value="/filter/{did}", method=RequestMethod.GET)
	public ModelAndView listeByDepartement(@PathVariable("did")int did) {
		ModelAndView model = new ModelAndView("employe/liste");
		model.addObject("employes", employeDAO.findByDepartement(did));
		
		return model;
	}

	
	@RequestMapping(value="/liste", method=RequestMethod.GET)
	public ModelAndView liste() {
		ModelAndView model = new ModelAndView("employe/liste");
		model.addObject("employes", employeDAO.findAll());
		
		return model;
	}

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView model = new ModelAndView("employe/form");
		model.addObject("employe", new Employe());
		model.addObject("departements", departementDAO.findAll());
		return model;
	}
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView();
		Employe e = employeDAO.findByID(id);
		if (e != null) {
		/*	if (e.getDepartement() != null)
				e.setDepartementID(e.getDepartement().getId());*/
			model.addObject("employe", e);
			model.addObject("departements", departementDAO.findAll());
			model.setViewName("employe/form");
			return model;
		}
		else {
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "employe inconnu");
			model.setViewName("redirect:/employe/liste");
			
			return model;
		}
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@ModelAttribute("employe") @Validated Employe employe,
						BindingResult result,
						Model model,
						RedirectAttributes redirectAttributes) {
		/*System.out.println("employe : " + employe 
							+ " departementID " + employe.getDepartementID());*/
		// j'ai besoin de retrouver le Departement a associer
		// pour bien sauvegarder l'employe
		
		//employe.setDepartement(departementDAO.findById(employe.getDepartementID()));
		
		if (result.hasErrors()) {
			model.addAttribute("departements", departementDAO.findAll());
			return "employe/form";
		}
		
		employeDAO.save(employe);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "employe mis a jour");
		return "redirect:/employe/liste";
	}
	
	
	
}

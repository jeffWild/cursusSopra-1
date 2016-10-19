package com.courtalon.springMvcExo3Form.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.courtalon.springMvcExo3Form.metier.Employe;

@Component
public class EmployeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Employe.class.equals(clazz);
	}

	@Override
	public void validate(Object cible, Errors errors) {
		Employe e = (Employe) cible;
		
		ValidationUtils.rejectIfEmpty(errors,
				 "nom",
				 "employeError.nom",
				 "le nom ne peut etre vide");
		ValidationUtils.rejectIfEmpty(errors,
				 "email",
				 "employeError.email",
				 "l'email ne peut etre vide");
		ValidationUtils.rejectIfEmpty(errors,
				 "poste",
				 "employeError.poste",
				 "le poste ne peut etre vide");

		Pattern p = Pattern.compile("[-a-z0-9_]+([.][-a-z0-9_]+)*@[-a-z0-9_]+[.](com|net|fr|org|info)",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(e.getEmail());
		if (!m.matches()) {
			errors.rejectValue("email",
				  "employeError.formatemail",
				  "format d'email invalide");
		}
		if (e.getSalaire() < 1000.0 || e.getSalaire() > 10000.0) {
			errors.rejectValue("salaire",
				  "employeError.salaire",
				  "le salaire doit etre entre 1000.0 et 10000.0");
		}
		
	}

}

package com.courtalon.firstSpringMvcForm.web;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.courtalon.firstSpringMvcForm.metier.Message;

// indique a spring qu'il s'agit d'un bean injectable ailleur
@Component
public class MessageValidator implements Validator
{
	// cela inquique ce que peu valider notre controlleur
	// ici, on supporte uniquement les messages
	@Override
	public boolean supports(Class<?> clazz) {
		return Message.class.equals(clazz);
	}
	
	// ceci est la méthode qui sera appelée pour valide notre formulaire
	// cible: l'objet metier/formulaire à valider
	// errors: conteneur pour les erreurs de validation
	@Override
	public void validate(Object cible, Errors errors) {
		
		
		
		Message msg = (Message)cible;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
												 "titre",
												 "messageForm.titre",
												 "le titre ne peut etre vide");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
												 "corps",
												 "messageForm.corps",
												 "le corps ne peut etre vide");
		/*
		 *  "toto"   -> titototi
		 * 				  ^^^^
		 *  "t.t."   -> titobutrtibatooooto
		 * 				^^^^  ^^^^
		 *  "ta+t"   -> tatttttaaaaattaaoottaat			+ -> 1 a n
		 * 				^^^   ^^^^^^^      ^^^^
		 * 				   ^^							* -> 0 a n
		 * 												? -> 0 a 1
		 * 												{2,5} -> 2 a 5 fois
		 * "(to)+"   -> tototototiootiiooto
		 * 				^^^^^^^^         ^^
		 * "t[aeiou] -> tatetttitrto					1 caractere parmis ceux listé
		 * 				^^^^  ^^  ^^
		 * "t[a-e0-9]+ -> entre a et e ou entre 0 et 9 
		 * 
		 *  (un|deux|trois)  -> un parmis les trois choix
		 * 	^ en dehors d'une classe de caractere -> debut de chaine
		 *  $ -> fin de chaine
		 * 
		 * 	"to$"		-> totitotitioto
		 * 							  ^^
		 */
		
		// vincent@gmail.com
		// vincent.courtalon@gmail.com
		// bob.joe.eponge@toto.com
		Pattern p = Pattern.compile("[-a-z0-9_]+([.][-a-z0-9_]+)*@[-a-z0-9_]+[.](com|net|fr|org|info)",
									Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(msg.getEmail());
		if (!m.matches()) {
			errors.rejectValue("email",
						  "messageForm.email",
						  "format d'email invalide");
		}
		
		Calendar now = new GregorianCalendar();
		now.setTime(new Date());
		
		Calendar d = new GregorianCalendar();
		d.setTime(msg.getDateMessage());
		
		if (now.before(d)) {
			errors.rejectValue("dateMessage",
							 "messageForm.dateMessage",
							 "pas de voyageur du futur");
		}
	}
}

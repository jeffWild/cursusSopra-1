package com.courtalon.springAOPForm.beans;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyFullAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		// MethodInvocation encapsule toutes les informations
		// sur l'appel de méthode intercepté
		// la methode, ses arguments, le this, le type retourné, etc...
		// mais, EN PLUS, cet objet permet de rappeler la méthode interceptée
		
		// c'est a nous, dans cet intercepteur de rappeler ou pas la méthode
		// original (via le MethodInvocation), et de renvoyer ou pas
		// le valeur retournée
		
		Object[] arguments = mi.getArguments();
		if (arguments.length < 1 || arguments[0] == null) {
			System.out.println("pas d'argument ou valeur nulle");
			// on retourne directement 0 sans appeler la methode originale
			return 0;
		}
		// je passe l'argument en minuscule
		arguments[0] = arguments[0].toString().toLowerCase();
		
		// j'appele la méthode originale
		Object returnValue = mi.proceed();
		System.out.println("valeur retournée = " + returnValue);
		
		// je renvoie son retour
		return returnValue;
	}

}

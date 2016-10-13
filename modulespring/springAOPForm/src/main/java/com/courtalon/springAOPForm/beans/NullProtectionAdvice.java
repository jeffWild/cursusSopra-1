package com.courtalon.springAOPForm.beans;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.MethodBeforeAdvice;

public class NullProtectionAdvice implements MethodBeforeAdvice {

	// le methode before de mon Advice sera appelé AVANT
	// l'execution de la méthode interceptée
	
	// elle recoit 3 parametres
	// 1) un objet de type Method qui décrit la méthode interceptée
	// 2) un tableau d'objet qui contient les parametres passé a la fonction interceptée
	// 3) le "this" sur lequel est appelé la méthode
	@Override
	public void before(Method methode, Object[] arguments, Object cible) throws Throwable {
		System.out.println("appel de before de NullProtectionAdvice");
		System.out.println("methode = " + methode.getName()
							+ " arguments " + Arrays.toString(arguments)
							+ " sur this de type " + cible.getClass().getName());
		// remplacer le premier argument NULL par une chaine vide
		if (arguments.length >= 1 && arguments[0] == null) {
			arguments[0] = "";
		}
	}

}

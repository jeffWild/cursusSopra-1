package com.courtalon.springAOPForm.beans;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.ThrowsAdvice;

public class FormatResultAdvice implements AfterReturningAdvice, ThrowsAdvice {

	@Override
	public void afterReturning(Object returnValue,
								Method methode,
								Object[] arguments,
								Object cible) throws Throwable {
		System.out.println("after returning -> " + methode.getName()
						+ " valeur retournée -> " + returnValue);
	}
	
	public void afterThrowing(Method methode,
								Object[] arguments,
								Object cible,
								RuntimeException ex) {
		System.out.println("after throwing -> " + methode.getName()
					+ " erreur retournée -> " + ex.getMessage());
	}

}

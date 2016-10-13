package com.courtalon.springAOPForm.beans;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class TrimAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method methode, Object[] arguments, Object cible) throws Throwable {
		System.out.println("dans trim advice");
		if (arguments.length >= 1)
			arguments[0] = arguments[0].toString().trim();
	}

}

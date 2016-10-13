package com.courtalon.springMementoForm.beans;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MementoAdvice implements MethodInterceptor {

	
	private Map<String, Map<Object, Object>> globalCache;
	
	
	public MementoAdvice() {
		globalCache = new HashMap<>();
	}
	
	
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		// je génére une clé pour cet appel
		String args = Arrays.toString(mi.getArguments());
		
		// je génére une clé décrivant la méthode appelée
		String methodKey = mi.getThis().getClass().getName() 
							+ "#" + mi.getMethod().getName()
							+ "#" + Arrays.toString(mi.getMethod().getParameterTypes());
		Map<Object, Object> cache = null;
		if (globalCache.containsKey(methodKey)) {
			System.out.println("methode deja connue : " + methodKey);
			cache = globalCache.get(methodKey);
		}
		else {
			System.out.println("nouvelle methode : " + methodKey);
			cache = new HashMap<>();
			globalCache.put(methodKey, cache);
		}
		
		
		Object returnValue = null;
		// ais je déjà eu un appel avec cet argument
		if (cache.containsKey(args)) {
			// OUI, je récupère en cache
			System.out.println("valeur deja en cache");
			returnValue = cache.get(args);
		}
		else {
			// NON, j'appel la méthode, et je stocke dans le cache
			System.out.println("valeur nouvelle");
			returnValue = mi.proceed();
			cache.put(args, returnValue);
		}
		System.out.println("renvoie: " + args + " -> " + returnValue);
		return returnValue;
	}

}

package com.courtalon.springExpressionForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courtalon.springExpressionForm.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        
        
		System.out.println("--------------------------------------");
		Client c1 = ctx.getBean("c1", Client.class);
		Client c2 = ctx.getBean("c2", Client.class);
		Client c3 = ctx.getBean("c3", Client.class);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		
		System.out.println("done...");
	}





}

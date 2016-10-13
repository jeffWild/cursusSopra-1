package com.courtalon.springMementoForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courtalon.springMementoForm.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
        IMathUtils mu = ctx.getBean("mu", IMathUtils.class);
        System.out.println(mu.carre(4));
        System.out.println(mu.carre(5));
        System.out.println(mu.cube(4));
        System.out.println(mu.cube(6));
        System.out.println(mu.carre(5));
        System.out.println(mu.carre(6));
        System.out.println(mu.addition(4, 5));
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}

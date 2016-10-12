package com.courtalon.springAcademyForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courtalon.springAcademyForm.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("academyContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        String[] names = {"mozart",
        					"andreRieu",
        					"louisArmstrong",
        					"hendrix",
        					"remibricka",
        					"darkVador"};
        
        List<IArtiste> artistes = new ArrayList<>();
        for (String name : names)
        	artistes.add(ctx.getBean(name, IArtiste.class));
        
        for (IArtiste a : artistes)
        	a.faireNumero();
        
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}

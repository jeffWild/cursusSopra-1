package com.courtalon.springKamelotForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courtalon.springKamelotForm.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        System.out.println("--------------------------------------");
        
        IChevalier c1 = ctx.getBean("jacquouille", IChevalier.class);
        IChevalier c2 = ctx.getBean("pierrot", IChevalier.class);
        
        c1.partirEnQuete();
        
        c2.partirEnQuete();
        
       /* Magicien merlin = ctx.getBean("merlin", Magicien.class);
        
        System.out.println(merlin.fournirQuete());
        System.out.println(merlin.fournirQuete());
        System.out.println(merlin.fournirQuete());*/
      
        IChevalier c3 = ctx.getBean("caradoc", IChevalier.class);
        IChevalier c4 = ctx.getBean("perceval", IChevalier.class);
        //ChevalierTableRonde c4 = ctx.getBean("perceval", ChevalierTableRonde.class);
        c3.partirEnQuete();
        c4.partirEnQuete();
        
    	System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}

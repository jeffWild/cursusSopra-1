package com.courtalon.springEventConvertForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courtalon.springEventConvertForm.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
      
        ctx.publishEvent(new CustomEvent(ctx, "bonjour depuis le main"));
        
        MyEventProducer p = ctx.getBean("producer1", MyEventProducer.class);
        p.fireEvent();
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}

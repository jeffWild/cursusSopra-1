package com.courtalon.firstSpring.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courtalon.firstSpring.beans.Enveloppe;
import com.courtalon.firstSpring.beans.Message;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
        Message msg = (Message)ctx.getBean("msg1");
       // Message msg2 = ctx.getBean("msg2", Message.class);
        
        Enveloppe env = ctx.getBean("env1", Enveloppe.class);
        
        System.out.println(msg);
        //System.out.println(msg2);
        System.out.println(env);
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}

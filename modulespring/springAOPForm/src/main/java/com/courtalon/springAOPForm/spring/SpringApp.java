package com.courtalon.springAOPForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courtalon.springAOPForm.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        

        input.nextLine();
        System.out.println("--------------------------------------");
        
        ITextUtils tu = ctx.getBean("tu", ITextUtils.class);
        
      /*  String result = tu.inversion("   bonjour monde  ");
        System.out.println(result);
        int cpt = tu.compteVoyelle("bonjour monde");
        System.out.println(cpt);
        
        result = tu.inversion(null);
        System.out.println(result);
        
        tu.inversion("lkmjsdfhkmfdsjhfsdkjqsdfhfslkdqjhsfdlkjsdfhsdfkjsqdfljksdfqhksjfbhdkhdkjdfhkdfjg:kjdhkdfjhgdkhjdfkjhsdkjdhfkm:jfdhfkdjdhfkmhddjkhdfkjhkjdhdkjdhfkjdhkdjfhkdjsdhkmjhjdfkhkdjdhjdshg");
        */
        
        System.out.println(tu.compteVoyelle("bONjour MondE"));
        System.out.println(tu.compteVoyelle(null));
        
        
        
     /*   TextUtils tu = ctx.getBean("tu", TextUtils.class);
        String result = tu.testConcatenation(100000);
        System.out.println("concatenation terminée");
       
        input.nextLine();
		System.out.println("--------------------------------------");

		result = tu.testConcatenation2(100000);
		System.out.println("concatenation2 terminée");*/
        
		System.out.println("done...");
	}





}

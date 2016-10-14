package com.courtalon.springJDBCForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.courtalon.springJDBCForm.beans.*;
import com.courtalon.springJDBCForm.metier.Produit;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        
       

        input.nextLine();
        System.out.println("--------------------------------------");
        
        ProduitDAO produitDAO = ctx.getBean("produitDAO", ProduitDAO.class);
        
        List<Produit> produits = produitDAO.findAll();
        
        for (Produit p : produits)
        	System.out.println(p);
        
        System.out.println("--------------------------------------");
        Produit p2 = produitDAO.findByID(2);
        System.out.println("produit no 2 -> " + p2);
        
        System.out.println("nom nouveau Produit ? ");
        String nom = input.nextLine();
        System.out.println("prix nouveau produit ? ");
        double prix = Double.parseDouble(input.nextLine());
        System.out.println("poids nouveau produit ? ");
        double poids = Double.parseDouble(input.nextLine());
        
        produitDAO.save(new Produit(0, nom, prix, poids));
        
        input.nextLine();
		System.out.println("--------------------------------------");

		System.out.println("done...");
	}





}

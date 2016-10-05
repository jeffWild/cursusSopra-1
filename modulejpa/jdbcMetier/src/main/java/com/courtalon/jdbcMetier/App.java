package com.courtalon.jdbcMetier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.courtalon.jdbcMetier.graphic.FenetreProduits;
import com.courtalon.jdbcMetier.utils.ProduitDAO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/base_secondmaven",
					"root", "");
			
			ProduitDAO produitDAO = new ProduitDAO(connection);
			
			FenetreProduits fenetre = new FenetreProduits(produitDAO);
			// afficher la fenetre
			fenetre.setVisible(true);
			
		} catch (ClassNotFoundException e) {e.printStackTrace();}
    	catch (SQLException e) {e.printStackTrace();}
		
    }
}

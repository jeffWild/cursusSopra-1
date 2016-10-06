package com.courtalon.jpaExercice1Form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.courtalon.jpaExercice1Form.graphic.FenetreTaches;
import com.courtalon.jpaExercice1Form.utils.TacheDAO;


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
					"jdbc:mysql://localhost:3306/base_jpaexercice1",
					"root", "");
			TacheDAO tacheDAO = new TacheDAO(connection);
			System.out.println("connection a la base réussie");
			// interface graphique ...
			FenetreTaches fenetre = new FenetreTaches(tacheDAO);
			fenetre.setVisible(true);
			
		} catch (ClassNotFoundException e) {e.printStackTrace();}
    	catch (SQLException e) {e.printStackTrace();}
		
		
		

    }
}

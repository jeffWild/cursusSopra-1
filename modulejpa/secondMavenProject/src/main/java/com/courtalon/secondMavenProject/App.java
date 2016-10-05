package com.courtalon.secondMavenProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        try {
            // charger le driver mysql
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/base_secondmaven",
					"root", "");
			// la connection a la base est réussie!!!
			System.out.println("connection réussie");
			
			// je récupere un statement "vide"
			Statement stat = connection.createStatement();
			
			// execution d'une requete sql
			ResultSet resultats = stat.executeQuery("select * from produit");
			
			while (resultats.next()){
				System.out.println(resultats.getString("nom") 
									+ " - "
									+ resultats.getDouble("prix"));
			}
			
			resultats.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
}

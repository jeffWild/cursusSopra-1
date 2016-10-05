package com.courtalon.secondMavenProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
			/*Class cls =*/ Class.forName("com.mysql.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/base_secondmaven",
					"root", "");
			// la connection a la base est réussie!!!
			System.out.println("connection réussie");
			
			// je récupere un statement "vide"
			Statement stat = connection.createStatement();
			
			// execution d'une requete sql
			
			// il existe 3 grandes categories de requetes
			// les requettes qui renvoie un tableau de ligne (select "classique")
			// les requettes qui altere le contenu de la base (insert, update, delete)
			//	 	-> elle renvoie un Integer -> le nb de lignes affectées
			// les requettes sclalaires -> renvoie une information
			//		-> select count (id) from produit
			
			// ici, c'est le premier type de requete, d'ou le executeQuery qui renvoie 
			// un ResultSet
			ResultSet resultats = stat.executeQuery("select id, nom, prix, poids from produit");
	
			// next vous positionne sur la prochaine ligne
			// initalement, vous etes positionné AVANT le première ligne
			// de plus, next vous renvoie false s'il n'y a pas de prochaine ligne
			
			while (resultats.next()){
				// une fois positionné sur la ligne, on peu récupérer les valeur
				// des champs/colonne pour cette ligne avec une methode
				// getType (ou typoe est le stype récupéré, STring, Int, Double, etc...)
				// il y a 2 variantes, soit on lui indique le no de la colonne
				// soit le nom de la colonne
				System.out.println( resultats.getInt("id")
									+ " - "
									+ resultats.getString("nom") 
									+ " - "
									+ resultats.getDouble("prix"));
				
			}
			resultats.close();
			Scanner reader = new Scanner(System.in);
			
			System.out.println("prix minimum de produits a afficher ? ");
			double prixMin = Double.parseDouble(reader.nextLine());
			
			// méthode non recommandée
/*			String sql = "select * from produit where prix > " + prixMin;
			resultats = stat.executeQuery(sql);*/
			
			// utilisation d'une requete préparée avec parametres
			PreparedStatement requestPrix = connection.prepareStatement(
					"select id,nom,prix,poids from produit where prix>?");
			// ici, je specifie la valeur du premier parametre, c.a.d du premier ?
			// le driver se chargera de la bonne syntaxe dans la requette
			requestPrix.setDouble(1, prixMin);
			
			resultats = requestPrix.executeQuery();
			
			while (resultats.next()){
				System.out.println(resultats.getString("nom") 
						+ " - "
						+ resultats.getDouble("prix"));
			}
			
			resultats.close();
			//
			// OPERATION CRUD
			//	C -> create   = insert
			//	R -> read	  = select
			//  U -> update	  = update
			//  D -> delete	  - delete
			System.out.println("creation d'un produit");
			System.out.println("nom du produit ? ");
			String nom = reader.nextLine();
			System.out.println("prix produit ? ");
			double prix = Double.parseDouble(reader.nextLine());
			System.out.println("poids produit ? ");
			double poids = Double.parseDouble(reader.nextLine());
			
			PreparedStatement requestInsert = connection.prepareStatement(
					"insert into produit(`nom`, `prix`, `poids`) VALUES(?,?,?)");
			requestInsert.setString(1, nom);
			requestInsert.setDouble(2, prix);
			requestInsert.setDouble(3, poids);
			// c'est le deuxieme type de requette (mise a jour de la base)
			
			int nbligne = requestInsert.executeUpdate();
			System.out.println(nbligne + " on été insérée(s)");
			
			
			// modification du prix d'un produit
			System.out.println("identifiant produit a modifier ?");
			int id = Integer.parseInt(reader.nextLine());
			System.out.println("nouveau prix ? ");
			prix = Double.parseDouble(reader.nextLine());
			PreparedStatement requestUpdate = connection.prepareStatement(
					"update produit set prix=? where id=?");
			requestUpdate.setDouble(1, prix);
			requestUpdate.setInt(2, id);
			
			nbligne = requestUpdate.executeUpdate();
			System.out.println(nbligne + " on été mise(s) à jour(s)");
			
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
}

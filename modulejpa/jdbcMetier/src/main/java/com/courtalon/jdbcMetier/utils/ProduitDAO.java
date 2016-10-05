package com.courtalon.jdbcMetier.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courtalon.jdbcMetier.metier.Produit;

public class ProduitDAO {
	public static final String SELECT_ALL = 
			"select * from produit";
	public static final String SELECT_BY_ID =
			"select * from produit where id=?";
	public static final String INSERT_ONE =
			"insert into produit(nom, prix,  poids) VALUES (?,?,?)";
	public static final String UPDATE_ONE = 
			"update produit set nom=?, prix=?, poids=? where id=?";
	public static final String DELETE_ONE =
			"delete from produit where id=?";
	
	
	// la connection a la base de donnée
	private Connection connection;
	
	private PreparedStatement findAllStatement;
	private PreparedStatement findByIDStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement deleteStatement;
	
	public ProduitDAO(Connection connection) {
		this.connection = connection;
		
		try {
			findAllStatement = connection.prepareStatement(SELECT_ALL);
			findByIDStatement = connection.prepareStatement(SELECT_BY_ID);
			updateStatement = connection.prepareStatement(UPDATE_ONE);
			insertStatement = connection.prepareStatement(INSERT_ONE);
			deleteStatement = connection.prepareStatement(DELETE_ONE);
		} catch (SQLException e) {e.printStackTrace();}
		
	}
	
	public List<Produit> findAll() {
		ArrayList<Produit> produits = new ArrayList<>();
		
		try {
			ResultSet rs = findAllStatement.executeQuery();
			while (rs.next()) {
				// j'instancie un objet produit pour chaque ligne du resultSet
				// et je l'ajoute dans la liste des produits renvoyés
				produits.add(new Produit(rs.getInt("id"),
										 rs.getString("nom"),
										 rs.getDouble("prix"),
										 rs.getDouble("poids")));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		
		return produits;
	}

	public Produit findByID(int id) {
		Produit produit = null;
		
		try {
			findByIDStatement.clearParameters();
			findByIDStatement.setInt(1, id);
			ResultSet rs = findByIDStatement.executeQuery();
			if (rs.next()) {
				// j'instancie un objet produit que je retournerais
				produit = new Produit(rs.getInt("id"),
										 rs.getString("nom"),
										 rs.getDouble("prix"),
										 rs.getDouble("poids"));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return produit;
	}
	
	
	public int save(Produit p) {
		if (p.getId() > 0) {
			//update
			try {
				updateStatement.clearParameters();
				updateStatement.setString(1, p.getNom());
				updateStatement.setDouble(2, p.getPrix());
				updateStatement.setDouble(3, p.getPoids());
				updateStatement.setInt(4, p.getId());
				return updateStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		else {
			// insert
			try {
				insertStatement.clearParameters();
				insertStatement.setString(1, p.getNom());
				insertStatement.setDouble(2, p.getPrix());
				insertStatement.setDouble(3, p.getPoids());
				return insertStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}
	
	
}

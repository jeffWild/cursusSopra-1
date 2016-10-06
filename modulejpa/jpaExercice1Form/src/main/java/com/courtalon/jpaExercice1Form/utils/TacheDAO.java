package com.courtalon.jpaExercice1Form.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courtalon.jpaExercice1Form.metier.Tache;


public class TacheDAO {
	public static final String SELECT_ALL = 
			"select * from tache";
	public static final String SELECT_ALL_ORDER_PRIORITE = 
			"select * from tache ORDER BY priorite ASC";

	public static final String SELECT_BY_ID =
			"select * from tache where id=?";
	public static final String INSERT_ONE =
			"insert into tache(description, priorite,  contexte) VALUES (?,?,?)";
	public static final String UPDATE_ONE = 
			"update tache set description=?, priorite=?, contexte=? where id=?";
	public static final String DELETE_ONE =
			"delete from tache where id=?";
	
	
	// la connection a la base de donnée
	private Connection connection;
	
	private PreparedStatement findAllStatement;
	private PreparedStatement findAllOrderedStatement;
	private PreparedStatement findByIDStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement deleteStatement;
	
	public TacheDAO(Connection connection) {
		this.connection = connection;
		
		try {
			findAllStatement = connection.prepareStatement(SELECT_ALL);
			findAllOrderedStatement = connection.prepareStatement(SELECT_ALL_ORDER_PRIORITE);
			findByIDStatement = connection.prepareStatement(SELECT_BY_ID);
			updateStatement = connection.prepareStatement(UPDATE_ONE);
			insertStatement = connection.prepareStatement(INSERT_ONE);
			deleteStatement = connection.prepareStatement(DELETE_ONE);
		} catch (SQLException e) {e.printStackTrace();}
		
	}
	
	public List<Tache> findAll() { return findAll(false); }
	
	public List<Tache> findAll(boolean triPriorite) {
		ArrayList<Tache> taches = new ArrayList<>();
		PreparedStatement p = 
				(triPriorite) ? findAllOrderedStatement : findAllStatement;
		
		try {
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				// j'instancie un objet produit pour chaque ligne du resultSet
				// et je l'ajoute dans la liste des produits renvoyés
				taches.add(new Tache(rs.getInt("id"),
										 rs.getString("description"),
										 rs.getInt("priorite"),
										 rs.getString("contexte")));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		
		return taches;
	}

	public Tache findByID(int id) {
		Tache tache = null;
		
		try {
			findByIDStatement.clearParameters();
			findByIDStatement.setInt(1, id);
			ResultSet rs = findByIDStatement.executeQuery();
			if (rs.next()) {
				// j'instancie un objet produit que je retournerais
				tache = new Tache(rs.getInt("id"),
						 rs.getString("description"),
						 rs.getInt("priorite"),
						 rs.getString("contexte"));
			}
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return tache;
	}
	
	
	public int save(Tache t) {
		if (t.getId() > 0) {
			//update
			try {
				updateStatement.clearParameters();
				updateStatement.setString(1, t.getDescription());
				updateStatement.setInt(2, t.getPriorite());
				updateStatement.setString(3, t.getContexte());
				updateStatement.setInt(4, t.getId());
				return updateStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		else {
			// insert
			try {
				insertStatement.clearParameters();
				insertStatement.setString(1, t.getDescription());
				insertStatement.setInt(2, t.getPriorite());
				insertStatement.setString(3, t.getContexte());
				return insertStatement.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();}
		}
		return 0;
	}
	
	public int deleteOne(int id) {
		try {
			deleteStatement.clearParameters();
			deleteStatement.setInt(1, id);
			return deleteStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
}

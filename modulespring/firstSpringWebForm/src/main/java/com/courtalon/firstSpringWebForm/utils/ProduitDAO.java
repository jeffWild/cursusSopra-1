package com.courtalon.firstSpringWebForm.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.courtalon.firstSpringWebForm.metier.Produit;



public class ProduitDAO {
	// cette objet/classe sert a faire la correspondance entre
	// une ligne renvoyée par une requette et un objet Produit
	// mapRow sera automatiquement appelé par le jdbcTemplate
	// pour chaque ligne revnoyée par la requette executée
	// le jdbcTemplate se charge du reste, le parcours du resultSet
	// et le remplissage d'une List avec les produits renvoyés
	private static class ProduitMapper implements RowMapper<Produit> {

		@Override
		public Produit mapRow(ResultSet rs, int pos) throws SQLException {
			return new Produit(rs.getInt("id"),
								rs.getString("nom"),
								rs.getDouble("prix"),
								rs.getDouble("poids"));
		}
	}
	
	
	public static final String FIND_ALL_SQL =
			"select id, nom, prix, poids from produit";
	public static final String FIND_BY_ID_SQL =
			"select id, nom, prix, poids from produit where id=?";
	public static final String UPDATE_SQL = 
			"update produit set nom=?, prix=?, poids=? where id=?";
	public static final String INSERT_SQL = 
			"insert into produit (nom, prix, poids) VALUES (?, ?, ?)";
	public static final String DELETE_SQL = 
			"delete from produit where id=?";
	
	
	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {return jdbcTemplate;}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}
	
	public List<Produit> findAll() {
		return jdbcTemplate.query(FIND_ALL_SQL, new Object[]{}, new ProduitMapper());
	}
	
	public Produit findByID(int id) {
		return jdbcTemplate.queryForObject(FIND_BY_ID_SQL,
											new Object[]{id},
											new ProduitMapper());
	}
	
	public int save(Produit p) {
		if (p.getId() == 0)
			return jdbcTemplate.update(INSERT_SQL,
					p.getNom(), p.getPrix(), p.getPoids());
		else
			return jdbcTemplate.update(UPDATE_SQL,
					p.getNom(), p.getPrix(), p.getPoids(), p.getId());
	}

	public int delete(int id) {
		return jdbcTemplate.update(DELETE_SQL, id);
	}
}

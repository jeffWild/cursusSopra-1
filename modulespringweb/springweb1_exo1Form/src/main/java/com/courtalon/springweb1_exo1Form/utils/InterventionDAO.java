package com.courtalon.springweb1_exo1Form.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.courtalon.springweb1_exo1Form.metier.Intervention;

public class InterventionDAO {
	public static final String
		SELECT_ALL = "SELECT `id`,`intervenant`,`lieu`,`noMateriel`,`description`,`dateProgrammee` FROM `Intervention`";
	public static final String
		SELECT_ALL_ORDER_DATE =
			"SELECT `id`,`intervenant`,`lieu`,`noMateriel`,`description`,`dateProgrammee` FROM `Intervention` ORDER BY dateProgrammee";
	public static final String 
		SELECT_BY_ID = "SELECT `id`,`intervenant`,`lieu`,`noMateriel`,`description`,`dateProgrammee` FROM `Intervention` WHERE `id`=?";
	public static final String
		INSERT_ONE = "INSERT INTO `Intervention` (`intervenant`, `lieu`, `noMateriel`, `description`, `dateProgrammee`) VALUES ( ?,?,?,?,?)";
	public static final String
		UPDATE_ONE ="UPDATE `intervention` SET `intervenant`=?,`lieu`=?,`noMateriel`=?,`description`=?,`dateProgrammee`=? WHERE `id`=?";
	public static final String
		DELETE_ONE = "DELETE FROM `intervention` WHERE `id`=?";
	
	
	private static class InterventionMapper implements RowMapper<Intervention> {

		@Override
		public Intervention mapRow(ResultSet rs, int pos) throws SQLException {
			return new Intervention(rs.getInt("id"),
					rs.getString("intervenant"),
					rs.getString("lieu"),
					rs.getString("noMateriel"),
					rs.getString("description"),
					rs.getDate("dateProgrammee"));
		}
		
	}
	
	private JdbcTemplate jdbcTemplate;

	
	public JdbcTemplate getJdbcTemplate() {return jdbcTemplate;}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

	public List<Intervention> findAll() { return findAll(false);}
	public List<Intervention> findAll(boolean tri) {
		if (tri)
			return getJdbcTemplate().query(SELECT_ALL_ORDER_DATE, new InterventionMapper());
		else
			return getJdbcTemplate().query(SELECT_ALL, new InterventionMapper());
	}
	
	public Intervention findByID(int id) {
		return getJdbcTemplate()
			  .queryForObject(SELECT_BY_ID, new Object[]{id},new InterventionMapper());
	}
	public int save(Intervention i) {
		if (i.getId() == 0) {
			return getJdbcTemplate()
					.update(INSERT_ONE, i.getIntervenant(), i.getLieu(), i.getNoMateriel(), i.getDescription(), i.getDateProgrammee());
		}
		else {
			return getJdbcTemplate()
					.update(UPDATE_ONE, i.getIntervenant(), i.getLieu(), i.getNoMateriel(), i.getDescription(), i.getDateProgrammee(), i.getId());
		}
	}
	
	public int remove(int id) {
		return getJdbcTemplate().update(DELETE_ONE, id);
	}

}

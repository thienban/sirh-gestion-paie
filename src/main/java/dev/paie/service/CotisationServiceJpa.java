package dev.paie.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.service.GradeServiceJdbcTemplate.GradeMapper;
import dev.paie.spring.JpaConfig;

@Service
public class CotisationServiceJpa implements CotisationService {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired 
	private JpaConfig jpa;
	@Autowired
	private DataSource data;
	
	@Override
	public void sauvegarder(Cotisation nouvelleCotisation) {
		
		
	}

	@Override
	public void mettreAJour(Cotisation cotisation) {
		Query query = em.createQuery("select h from Hotel h where h.nom='nom2'");
		Hotel hotel = (Hotel) query.getSingleResult();

		If (hotel != null){

		Hotel hotel2 = new Hotel();
		hotel2.setId(hotel.getId());
		hotel2.setNom(hotel.getNom());
		hotel2.setVille("nouvelle ville");
		em.merge(hotel2);

		}
	}

	@Override
	public List<Cotisation> lister() {
		String sql = "SELECT * FROM grade";
		return this.jdbcTemplate.query(sql, new GradeMapper());

	}

	public class GradeMapper implements RowMapper<Grade> {
		public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
			Grade g = new Grade(rowNum, null, null, null);
			g.setId(rs.getInt("ID"));
			g.setCode(rs.getString("CODE"));
			g.setNbHeuresBase(rs.getBigDecimal("nbHeureBase"));
			g.setTauxBase(rs.getBigDecimal("tauxBase"));
			return g;
		}
	}
}
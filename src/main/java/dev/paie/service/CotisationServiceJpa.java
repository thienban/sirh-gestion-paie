package dev.paie.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
		Cotisation c = em.find(Cotisation.class, cotisation.getId());

		if (c != null){
			c.setCode(cotisation.getCode());
			c.setLibelle(cotisation.getLibelle());
			c.setTauxPatronal(cotisation.getTauxPatronal());
			c.setTauxSalarial(cotisation.getTauxSalarial());
		}
	}

	@Override
	public List<Cotisation> lister() {
		TypedQuery<Cotisation> q = em.createQuery("SELECT c FROM Cotisation c", Cotisation.class);
		return q.getResultList();

	}

	public class GradeMapper implements RowMapper<Grade> {
		public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
			Grade q = new Grade();
			q.setId(rs.getInt("ID"));
			q.setCode(rs.getString("CODE"));
			q.setNbHeuresBase(rs.getBigDecimal("nbHeureBase"));
			q.setTauxBase(rs.getBigDecimal("tauxBase"));
			return q;
		}
	}
}
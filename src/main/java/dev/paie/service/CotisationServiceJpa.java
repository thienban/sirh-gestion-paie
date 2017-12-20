package dev.paie.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.service.GradeServiceJdbcTemplate.GradeMapper;
import dev.paie.spring.JpaConfig;

@Service
@Transactional
public class CotisationServiceJpa implements CotisationService {
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private JpaConfig jpa;
	@Autowired
	private DataSource data;

	@Override
	public void sauvegarder(Cotisation nouvelleCotisation) {
		em.persist(nouvelleCotisation);
	}

	@Override
	public void mettreAJour(Cotisation cotisation) {
		em.merge(cotisation);
	}

	@Override
	public List<Cotisation> lister() {
		TypedQuery<Cotisation> q = em.createQuery("SELECT c FROM Cotisation c", Cotisation.class);
		return q.getResultList();

	}
}
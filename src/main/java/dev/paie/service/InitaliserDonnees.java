package dev.paie.service;

import java.time.LocalDate;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.Utilisateur;
import dev.paie.entite.Utilisateur.ROLES;

@Service
public class InitaliserDonnees implements InitialiserDonneesService {

	@Autowired
	private ApplicationContext context;

	@PersistenceContext
	private EntityManager em;
	@Autowired 
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void initialiser() {
		Stream.of(Cotisation.class, Grade.class, Entreprise.class, ProfilRemuneration.class )
		.flatMap(maClasse -> context.getBeansOfType(maClasse).values().stream()).forEach(bean -> {
			em.persist(bean);
		});

	for (int month = 1; month <= 12; month++) {
		Periode p = new Periode();
		LocalDate dateDebut = LocalDate.of(2017, month, 1);
		LocalDate dateFin = dateDebut.withDayOfMonth(dateDebut.lengthOfMonth());
		p.setDateDebut(dateDebut);
		p.setDateFin(dateFin);
		em.persist(p);
	}
	
	Utilisateur utilisateur1 = new Utilisateur();
	utilisateur1.setEstActif(true);
	utilisateur1.setNomUtilisateur("admin");
	utilisateur1.setMotDePasse(passwordEncoder.encode("admin1"));
	utilisateur1.setRole(ROLES.ROLE_ADMINISTRATEUR);
	
	em.persist(utilisateur1);
	
	Utilisateur utilisateur2 = new Utilisateur();
	utilisateur2.setEstActif(true);
	utilisateur2.setNomUtilisateur("user");
	utilisateur2.setMotDePasse(passwordEncoder.encode("user"));
	utilisateur2.setRole(ROLES.ROLE_UTILISATEUR);
	
	em.persist(utilisateur2);

}
}

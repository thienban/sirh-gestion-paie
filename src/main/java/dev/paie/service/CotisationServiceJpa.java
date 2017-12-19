package dev.paie.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import dev.paie.entite.Cotisation;

@Service
public class CotisationServiceJpa implements CotisationService {
@PersistenceContext private EntityManager em;

@Override
public void sauvegarder(Cotisation nouvelleCotisation) {
	// TODO Auto-generated method stub
	
}

@Override
public void mettreAJour(Cotisation cotisation) {
	// TODO Auto-generated method stub
	
}

@Override
public List<Cotisation> lister() {
	// TODO Auto-generated method stub
	return null;
}

}
package dev.paie.service;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;

public class CotisationServiceJpaTest {
	@Autowired
	private CotisationService cotisationService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder une nouvelle cotisation
		Cotisation cotisation1 = new Cotisation("yeah","A001",new BigDecimal(0.05), new BigDecimal(0.05));
		cotisationService.sauvegarder(cotisation1);
		// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation via la méthode lister
		
		// TODO modifier une cotisation
		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		
	}
}
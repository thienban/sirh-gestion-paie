package dev.paie.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;

@ContextConfiguration(classes = {ServicesConfig.class})
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {
	@Autowired
	private CotisationService cotisationService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
			// TODO sauvegarder un nouveau grade
			Cotisation c1 = new Cotisation();
			c1.setCode("A001");
			c1.setLibelle("Gouv");     
			c1.setTauxPatronal(new BigDecimal("0.2"));
			c1.setTauxSalarial(new BigDecimal("0.4"));
			cotisationService.sauvegarder(c1);
			
			// TODO vérifier qu'il est possible de récupérer le nouveau grade 
			List<Cotisation> cListe = cotisationService.lister();
			assertTrue(cListe.stream().anyMatch(unGrade -> unGrade.getCode().equals("A001")));
			
			// TODO modifier un grade
			Cotisation cInsere = cListe.stream().filter(unGrade -> unGrade.getCode().equals("A001")).findAny().get(); //trouver par le code
			cInsere.setCode("A002");
			cInsere.setLibelle("GouvFr");   
			cInsere.setTauxPatronal(new BigDecimal("0.3"));
			cInsere.setTauxSalarial(new BigDecimal("0.5"));
			cotisationService.mettreAJour(cInsere);
			
			// TODO vérifier que les modifications sont bien prises en compte
			List<Cotisation> c2 = cotisationService.lister();
			assertTrue(c2.stream().anyMatch(unCot -> unCot.getCode().equals("A002") && unCot.getTauxPatronal().compareTo(new BigDecimal("0.3")) == 0));
		
	}
}
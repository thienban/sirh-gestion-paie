package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;
import dev.paie.spring.DataSourceMySQLConfig;

@ContextConfiguration(classes = {ServicesConfig.class})
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {
	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		Avantage a = new Avantage();
		int id1 = a.getId();
		a.setCode("TR");
		a.setNom("Transport");
		a.setMontant(new BigDecimal("75.0"));
		//TODO sauvegarder un nouvel avantage
		avantageRepository.save(a);	
		
		//TODO vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
		List <Avantage> avantage2 = avantageRepository.findAll();
		assertTrue(avantage2.stream().anyMatch(Avan -> Avan.getCode().equals("TR")));
		
		 Avantage avRecup = avantage2.stream().filter(av -> av.getCode().equals("TR")).findFirst().orElse(null);
		 assertTrue(avRecup!=null);
				
		//TODO modifier un avantage
		 int id2=avRecup.getId();
		 avRecup.setCode("TR2");
		 avRecup.setNom("Transport");
		 avRecup.setMontant(new BigDecimal("50.0"));
		avantageRepository.save(avRecup);
		//TODO vérifier que les modifications sont bien prises en compte via la méthode findOne
		Avantage avModif = avantageRepository.findOne(id2);
		assertTrue(avModif!=null);
		assertThat(avModif.getCode()).isEqualTo("TR2");
	}	
}	

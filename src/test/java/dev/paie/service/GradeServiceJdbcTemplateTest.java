package dev.paie.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourceMySQLConfig;
import dev.paie.entite.Grade;

@ContextConfiguration(classes = {GradeServiceJdbcTemplate.class, DataSourceMySQLConfig.class})
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {
	@Autowired
	private GradeService gradeService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder un nouveau grade
		Grade grade1 = new Grade();
		grade1.setCode("A001");
		grade1.setNbHeuresBase(new BigDecimal("20"));     
		grade1.setTauxBase(new BigDecimal("5.0"));
		gradeService.sauvegarder(grade1);
		
		// TODO vérifier qu'il est possible de récupérer le nouveau grade 
		List<Grade> grade2Liste = gradeService.lister();
		assertTrue(grade2Liste.stream().anyMatch(unGrade -> unGrade.getCode().equals("A001")));
		
		// TODO modifier un grade
		Grade gradeInsere = grade2Liste.stream().filter(unGrade -> unGrade.getCode().equals("A001")).findAny().get(); //trouver par le code
		gradeInsere.setCode("A002");
		gradeInsere.setNbHeuresBase(new BigDecimal("10"));     
		gradeInsere.setTauxBase( new BigDecimal("4.0"));
		gradeService.mettreAJour(gradeInsere);
		
		// TODO vérifier que les modifications sont bien prises en compte
		List<Grade> grade4 = gradeService.lister();
		assertTrue(grade4.stream().anyMatch(unGrade -> unGrade.getCode().equals("A002") && unGrade.getNbHeuresBase().compareTo(new BigDecimal("10")) == 0));
	
	}
}
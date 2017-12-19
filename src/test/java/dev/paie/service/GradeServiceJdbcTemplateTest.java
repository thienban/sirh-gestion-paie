package dev.paie.service;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dev.paie.entite.Grade;

//TODO compléter la configuration
public class GradeServiceJdbcTemplateTest {
	@Autowired
	private GradeService gradeService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder un nouveau grade
		Grade grade1 = new Grade(1,"A001",new BigDecimal(50), new BigDecimal(5));
		gradeService.sauvegarder(grade1);
		// TODO vérifier qu'il est possible de récupérer le nouveau grade 
		/*grade2 = new 
		lister();		
		// TODO modifier un grade
		grade3 = new
		mettreAJour();
		// TODO vérifier que les modifications sont bien prises en compte
		grade4 = new 
		lister();*/
	
		
	
	
		   
	}
}
package dev.paie.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Component
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService{
	
	@Autowired
	@Qualifier("paieUtils1")
	PaieUtils paieUtils;
	
	public CalculerRemunerationServiceSimple () {
		
	}
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		
		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();
		
		
		BigDecimal salaire_base = bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase().multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase());
		BigDecimal salaire_brut = salaire_base.add(bulletin.getPrimeExceptionnelle());
		BigDecimal total_retenue_salarial = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables()
				.stream().filter(c -> c.getTauxSalarial()!=null).map(c -> c.getTauxSalarial().multiply(salaire_brut)).reduce(BigDecimal.ZERO,(p,q)->p.add(q));
		BigDecimal total_cotisation_patronal = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables()
				.stream().filter(c -> c.getTauxPatronal()!=null).map(c -> c.getTauxPatronal().multiply(salaire_brut)).reduce(BigDecimal.ZERO, (p,q)->p.add(q));
		BigDecimal net_imposable = salaire_brut.subtract(total_retenue_salarial);
		BigDecimal net = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables()
				.stream().filter(c -> c.getTauxSalarial()!=null).map(c -> c.getTauxSalarial().multiply(salaire_brut)).reduce(BigDecimal.ZERO,(p,q)->p.add(q));
		BigDecimal net_a_payer = net_imposable.subtract(net);
		resultat.setNetAPayer(paieUtils.formaterBigDecimal(net_a_payer));
		return resultat;
	}
	
	

}

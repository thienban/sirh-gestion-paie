package dev.paie.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired
	private PaieUtils paieUtils;

	public CalculerRemunerationServiceSimple() {

	}

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {

		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();

		BigDecimal salaire_base = bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase()
				.multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase());
		resultat.setSalaireDeBase(paieUtils.formaterBigDecimal(salaire_base));

		BigDecimal salaire_brut = new BigDecimal(resultat.getSalaireDeBase()).add(bulletin.getPrimeExceptionnelle());
		resultat.setSalaireBrut(paieUtils.formaterBigDecimal(salaire_brut));
		BigDecimal salaire_brut_arrondi = new BigDecimal(resultat.getSalaireBrut());

		BigDecimal total_retenue_salarial = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables().stream().filter(c -> c.getTauxSalarial() != null)
				.map(c -> c.getTauxSalarial().multiply(salaire_brut_arrondi)).reduce(BigDecimal.ZERO, (p, q) -> p.add(q));
		resultat.setTotalRetenueSalarial(paieUtils.formaterBigDecimal(total_retenue_salarial));
		BigDecimal total_retenue_salarial_arrondi = new BigDecimal(resultat.getTotalRetenueSalarial());

		BigDecimal total_cotisation_patronal = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables().stream().filter(c -> c.getTauxPatronal() != null)
				.map(c -> c.getTauxPatronal().multiply(salaire_brut_arrondi)).reduce(BigDecimal.ZERO, (p, q) -> p.add(q));
		resultat.setTotalCotisationsPatronales(paieUtils.formaterBigDecimal(total_cotisation_patronal));
		BigDecimal total_cotisation_patronal_arrondi = new BigDecimal(resultat.getTotalCotisationsPatronales());

		BigDecimal net_imposable = salaire_brut_arrondi.subtract(total_retenue_salarial_arrondi);
		resultat.setNetImposable(paieUtils.formaterBigDecimal(net_imposable));
		BigDecimal net_imposable_arrondi = new BigDecimal(resultat.getNetImposable());

		BigDecimal net = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables().stream()
				.filter(c -> c.getTauxSalarial() != null).map(c -> c.getTauxSalarial().multiply(salaire_brut_arrondi))
				.reduce(BigDecimal.ZERO, (p, q) -> p.add(q));

		BigDecimal net_a_payer = net_imposable_arrondi.subtract(net);
		resultat.setNetAPayer(paieUtils.formaterBigDecimal(net_a_payer));
		return resultat;
	}
}

package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;


@Controller
@RequestMapping("/employes")
public class BulletinController {

		@Autowired
		private PeriodeRepository periodeRepository;
		@Autowired
		private RemunerationEmployeRepository employeRepository;
		

		@RequestMapping(method = RequestMethod.GET, path = "/creerbulletin")
		public ModelAndView creerEmploye() {
			//créer la vue
			ModelAndView mv = new ModelAndView();
			mv.setViewName("employes/creerBulletin");
			//envoyer les objets au front
			List<Periode> periode = periodeRepository.findAll();
			mv.addObject("periode", periode);
			List<RemunerationEmploye> employe = employeRepository.findAll();
			mv.addObject("employe", employe);
			//instancier le bulletin
			BulletinSalaire bulletin = new BulletinSalaire();
			mv.addObject("bulletin", bulletin);
			return mv;
		}
}


package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.BulletinRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class BulletinController {

	@Autowired
	private PeriodeRepository periodeRepository;
	@Autowired
	private RemunerationEmployeRepository employeRepository;
	@Autowired
	private BulletinRepository bulletinRepository;
	

	@RequestMapping(method = RequestMethod.GET, path = "/listerbulletin")
	public ModelAndView listerEmploye() {
		// créer la vue
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerBulletin");
		List<RemunerationEmploye> employe = employeRepository.findAll();
		mv.addObject("employes", employe);

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/creerbulletin")
	public ModelAndView creerEmploye() {
		// créer la vue
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerBulletin");
		// envoyer les objets au front
		List<Periode> periode = periodeRepository.findAll();
		mv.addObject("periodes", periode);
		List<RemunerationEmploye> employe = employeRepository.findAll();
		mv.addObject("employes", employe);
		// instancier le bulletin
		BulletinSalaire bulletin = new BulletinSalaire();
		mv.addObject("bulletin", bulletin);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creerbulletin")
	public String ajouterBulletinSalaire(@ModelAttribute("bulletinSalaire") BulletinSalaire bulletin,
			@RequestParam("employe_id") Integer employe_id, @RequestParam("periode_id") Integer periode_id) {
		bulletin.setPeriode(periodeRepository.findOne(periode_id));
		bulletin.setRemunerationEmploye(employeRepository.findOne(employe_id));
		bulletinRepository.save(bulletin);
		return "redirect:listerBulletin";
	}
}

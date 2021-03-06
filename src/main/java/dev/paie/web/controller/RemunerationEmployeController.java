package dev.paie.web.controller;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private ProfilRepository profilRepository;
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private RemunerationEmployeRepository employeRepository;
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView listerEmploye() {
		//créer la vue
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/lister");
		List<RemunerationEmploye> employe = employeRepository.findAll();
		mv.addObject("employes", employe);

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerEmploye() {
		//créer la vue
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		//envoyer les objets au front
		List<Entreprise> entreprise = entrepriseRepository.findAll();
		mv.addObject("entreprise", entreprise);
		List<ProfilRemuneration> profil = profilRepository.findAll();
		mv.addObject("profil", profil);
		List<Grade> grade = gradeRepository.findAll();
		mv.addObject("grade", grade);
		return mv;
	}
		
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public String ajouter(@ModelAttribute("remunerationEmploye") RemunerationEmploye remunerationEmploye,
				@RequestParam("grade_id") Integer grade_id, @RequestParam("entreprise_id") Integer entreprise_id,
				@RequestParam("profil_id") Integer profil_id) {
			remunerationEmploye.setGrade(gradeRepository.findOne(grade_id));
			remunerationEmploye.setEntreprise(entrepriseRepository.findOne(entreprise_id));
			remunerationEmploye.setProfilRemuneration(profilRepository.findOne(profil_id));
			remunerationEmploye.setDateCreation(ZonedDateTime.now());
			employeRepository.save(remunerationEmploye);
	return "redirect:lister";
	}
}


package dev.paie.web.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.config.ServicesConfig;
import dev.paie.repository.AvantageRepository;
import dev.paie.web.form.EmployeForm;

@Controller
@RequestMapping("/employes")
@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class RemunerationEmployeController {

	@Autowired
	private AvantageRepository avantageRepository;
	/*
	 * public ModelAndView creerEmploye() { ModelAndView mv = new ModelAndView();
	 * mv.setViewName("employes/creerEmploye");//donner la page
	 * mv.addObject("prefixMatricule", "M00");//donner les données return mv;
	 */

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public String setupForm(Model model) {
		// Création de l’objet du modèle.
		EmployeForm employeF = new EmployeForm();
		// Liaison du modèle et de l’objet.
		model.addAttribute("employeF", employeF);
		// Renvoi du nom logique de la vue formulaire.
		return "employes/creerEmploye";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public void String submitForm(@ModelAttribute("EmployeForm") EmployeForm employeF) {
		EmployeForm emF = new EmployeForm();
		avantageRepository.save(emF);
	}
}
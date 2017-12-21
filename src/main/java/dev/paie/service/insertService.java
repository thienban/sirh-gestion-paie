package dev.paie.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.repository.AvantageRepository;
import dev.paie.web.form.EmployeForm;

@ContextConfiguration(classes = {ServicesConfig.class})
@RunWith(SpringRunner.class)
public class insertService {
	@Autowired
	private AvantageRepository avantageRepository;
	
	EmployeForm emF = new EmployeForm();
	emF.setMatricule();
	emF.
	
	
}

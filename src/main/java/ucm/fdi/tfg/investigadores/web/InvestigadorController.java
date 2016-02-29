package ucm.fdi.tfg.investigadores.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ucm.fdi.tfg.investigadores.business.boundary.InvestigadorManager;

@Controller
public class InvestigadorController {

	
	InvestigadorManager investigadorManager;
	
	@Autowired
	public InvestigadorController (InvestigadorManager investigadorManager ){
		this.investigadorManager = investigadorManager;
	}
		
	
	
	
}

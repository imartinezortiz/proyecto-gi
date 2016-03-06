package ucm.fdi.tfg.investigadores.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.investigadores.business.boundary.InvestigadorManager;

@Controller
public class InvestigadorController {

	
	InvestigadorManager investigadorManager;
	
	@Autowired
	public InvestigadorController (InvestigadorManager investigadorManager ){
		this.investigadorManager = investigadorManager;
	}
		
	@RequestMapping(value = "/altaInvestigador", method = RequestMethod.GET)
	public ModelAndView altaInvestigador() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("usuario", null);
		
		ModelAndView view = new ModelAndView("home", model);
		
		return view;	

	}
	
}

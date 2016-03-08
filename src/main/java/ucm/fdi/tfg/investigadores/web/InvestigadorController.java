package ucm.fdi.tfg.investigadores.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.investigadores.business.boundary.InvestigadorManager;


@Controller
public class InvestigadorController {

	InvestigadorManager investigadorManager;

	@Autowired
	public InvestigadorController(InvestigadorManager investigadorManager) {
		this.investigadorManager = investigadorManager;
	}

	//Aqui manda todos los proyectos que hay, 
	//pero cuando arreglemos el login, solo mandará los proyectos asociados a ese investigador
	
	@RequestMapping(value = "/proyectos", method = RequestMethod.GET)
	public ModelAndView listarProeyctos() {

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("proyectos", investigadorManager.getAllProyects());

		ModelAndView view = new ModelAndView("listarProyectos", model);

		return view;
	}
	
	@RequestMapping(value = "/proyecto/{id}/menu", method = RequestMethod.GET)
	public ModelAndView menuProyecto(@PathVariable(value="id") Long id) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("idProyecto",id);
		
		ModelAndView view = new ModelAndView("menuProyecto", model);

		return view;
	}

}

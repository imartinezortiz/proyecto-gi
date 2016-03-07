package ucm.fdi.tfg.gestores.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.administradores.business.boundary.AdministradorManager;
import ucm.fdi.tfg.gestores.business.boundary.GestorManager;
import ucm.fdi.tfg.investigadores.business.entity.Investigador;

@Controller
public class GestorController {
	
	GestorManager gestorManager;
	AdministradorManager administradorManager;
	
	@Autowired
	public GestorController(GestorManager gestorManager,AdministradorManager administradorManager){
		this.gestorManager = gestorManager;
		this.administradorManager = administradorManager;
	}
	
	@RequestMapping(value = "/gestor", method = RequestMethod.GET)
	public ModelAndView menuGestor() {
		
		Map<String, String> model = new HashMap<String, String>();
		
		model.put("usuario",null);
		
		ModelAndView view = new ModelAndView("menuGestor", model);
		
		return view;	
	}
	
	@RequestMapping(value = "/añadirProyecto", method = RequestMethod.GET)
	public ModelAndView añadirProyecto() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("investigadores",administradorManager.findAll());
		
		ModelAndView view = new ModelAndView("proyecto", model);
		
		return view;	
	}
	
	

}

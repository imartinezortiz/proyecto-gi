package ucm.fdi.tfg.administradores.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.administradores.business.boundary.AdministradorManager;
import ucm.fdi.tfg.investigadores.business.boundary.NuevoInvestigadorDTO;

@Controller
public class AdministradoresController {
	
	 AdministradorManager administradorManager;
	
	@Autowired
	public AdministradoresController(AdministradorManager administradorManager) {
		this.administradorManager = administradorManager;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView menuAdmin() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("usuario", null);
		
		ModelAndView view = new ModelAndView("menuAdmin", model);
		
		return view;	

	}
	
	@RequestMapping(value = "/altaInvestigador", method = RequestMethod.GET)
	public ModelAndView altaInvestigador() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("usuario", null);
		
		ModelAndView view = new ModelAndView("investigadorForm", model);
		
		return view;	

	}
	
	@RequestMapping(value = "/addInvestigador", method = RequestMethod.POST)
	public String addInvestigador(NuevoInvestigadorDTO investigador) {
		
		//lo metemos en la bbdd
		
		System.out.println(investigador.toString());
		
		administradorManager.save(investigador);
		
		
		return "redirect:/admin";

	}
	

}

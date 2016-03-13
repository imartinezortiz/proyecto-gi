package ucm.fdi.tfg.gestores.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.proyecto.business.boundary.NuevoProyectoDTO;
import ucm.fdi.tfg.proyecto.business.boundary.ProyectosManager;
import ucm.fdi.tfg.users.business.boundary.UserManager;
import ucm.fdi.tfg.users.business.control.UserRepository;
import ucm.fdi.tfg.users.business.entity.Investigador;

@Controller
public class GestorController {

	private ProyectosManager proyectos;
	private UserManager users;

	@Autowired
	public GestorController(ProyectosManager proyectos, UserManager users) {
		this.proyectos = proyectos;
		this.users = users;
	}

	@RequestMapping(value = "/gestor", method = RequestMethod.GET)
	public ModelAndView menuGestor() {

		ModelAndView view = new ModelAndView("menuGestor");

		return view;
	}
	
	//cambiar a /proyectos/nuevo
	@RequestMapping(value = "/crearProyecto", method = RequestMethod.GET)
	public ModelAndView añadirProyecto() {

		Map<String, Object> model = new HashMap<String, Object>();
		
		//model.put("investigadores", users.findAllInvestigadores());
		model.put("investigadores", users.findAllUserInvestigadores());
		model.put("NuevoProyectoDTO",  new NuevoProyectoDTO());
		ModelAndView view = new ModelAndView("proyectoForm", model);

		return view;
	}

	@RequestMapping(value = "/crearProyecto", method = RequestMethod.POST)
	public ModelAndView añadirProyectoPost(NuevoProyectoDTO proyectoDTO, BindingResult errors) {
		/*
		 * ModelAndView view = null;
		
		 Lo que nos dejó Iván
		
		
		//if (errors.hasErrors()) {
			view = new ModelAndView("proyectoForm");
			view.addObject("investigadores", users.findAllInvestigadores());
			view.addObject("NuevoProyectoDTO", proyectoDTO);
//		} else {
//			gestorManager.nuevoProyecto(proyectoDTO);
//			view = new ModelAndView("redirect:/proyectos");
//		}
		
		*/		
		
		 proyectos.nuevoProyecto(proyectoDTO);
		 
		 ModelAndView view = new ModelAndView("menuGestor");
		
		return view;
	}

}

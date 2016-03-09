package ucm.fdi.tfg.gestores.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.proyecto.business.boundary.NuevoProyectoDTO;
import ucm.fdi.tfg.proyecto.business.boundary.ProyectosManager;
import ucm.fdi.tfg.users.business.boundary.UserManager;

@Controller
public class GestorController {

	private ProyectosManager gestorManager;
	private UserManager users;

	@Autowired
	public GestorController(ProyectosManager gestorManager, UserManager users) {
		this.gestorManager = gestorManager;
		this.users = users;
	}

	@RequestMapping(value = "/gestor", method = RequestMethod.GET)
	public ModelAndView menuGestor() {

		Map<String, String> model = new HashMap<String, String>();

		model.put("usuario", null);

		ModelAndView view = new ModelAndView("menuGestor", model);

		return view;
	}
//cambiar a /proyectos/nuevo
	@RequestMapping(value = "/crearProyecto", method = RequestMethod.GET)
	public ModelAndView añadirProyecto() {

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("investigadores", users.findAllInvestigadores());
		model.put("NuevoProyectoDTO",  new NuevoProyectoDTO());
		ModelAndView view = new ModelAndView("proyectoForm", model);

		return view;
	}

	@RequestMapping(value = "/crearProyecto", method = RequestMethod.POST)
	public ModelAndView añadirProyectoPost(NuevoProyectoDTO proyectoDTO, BindingResult errors) {
		ModelAndView view = null;
		
		//if (errors.hasErrors()) {
			view = new ModelAndView("proyectoForm");
			view.addObject("investigadores", users.findAllInvestigadores());
			view.addObject("NuevoProyectoDTO", proyectoDTO);
//		} else {
//			gestorManager.nuevoProyecto(proyectoDTO);
//			view = new ModelAndView("redirect:/proyectos");
//		}
		
		return view;
	}

}

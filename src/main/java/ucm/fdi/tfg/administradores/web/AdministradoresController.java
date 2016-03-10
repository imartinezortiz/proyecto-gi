package ucm.fdi.tfg.administradores.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ucm.fdi.tfg.users.business.boundary.NuevoInvestigadorDTO;
import ucm.fdi.tfg.users.business.boundary.UserManager;

@Controller
public class AdministradoresController {

	private UserManager users;

	@Autowired
	public AdministradoresController(UserManager users) {
		this.users = users;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView menuAdmin() {

		ModelAndView view = new ModelAndView("menuAdmin");

		return view;
	}

	@RequestMapping(value = "/altaInvestigador", method = RequestMethod.GET)
	public ModelAndView altaInvestigador() {
		
		ModelAndView view = new ModelAndView("usuarios/investigadorForm");
		
		return view;
	}

	@RequestMapping(value = "/altaInvestigador", method = RequestMethod.POST)
	public String addInvestigador(NuevoInvestigadorDTO investigador) {

		users.addInvestigador(investigador);

		return "redirect:/admin";
	}
	
	@RequestMapping(value = "/altaAdmin", method = RequestMethod.GET)
	public ModelAndView altaAdmin() {
		ModelAndView view = new ModelAndView("usuarios/adminForm");

		return view;
	}
	
	@RequestMapping(value = "/altaAdmin", method = RequestMethod.POST)
	public String addAdmin() {
		
		return "redirect:/admin";
	}
	
	
	@RequestMapping(value = "/altaGestor", method = RequestMethod.GET)
	public ModelAndView altaGestor() {
		
		ModelAndView view = new ModelAndView("usuarios/gestorForm");

		return view;
	}
	
	@RequestMapping(value = "/altaGestor", method = RequestMethod.POST)
	public String addGestor() {

		return "redirect:/admin";
	}
	
	
	

}

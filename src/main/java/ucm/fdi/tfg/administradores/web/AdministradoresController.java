package ucm.fdi.tfg.administradores.web;

import java.util.HashMap;
import java.util.Map;

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

		// lo metemos en la bbdd

		System.out.println(investigador.toString());

		users.addInvestigador(investigador);

		return "redirect:/admin";

	}

}

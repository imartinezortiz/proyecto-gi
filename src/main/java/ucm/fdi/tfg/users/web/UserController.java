package ucm.fdi.tfg.users.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.users.business.boundary.UserManager;

@Controller
public class UserController {

	private UserManager users;

	@Autowired
	public UserController(UserManager userManager) {
		this.users = userManager;
	}

	// Por aqui entra la aplicacion
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		
		ModelAndView view = new ModelAndView("home");
		
		return view;
	}

	// Redirecciona al menu despues de hacer login
	@RequestMapping(value = "/bienvenido", method = RequestMethod.GET)
	public ModelAndView prueba() {

		ModelAndView view = new ModelAndView("menu");

		return view;
	}

	// Entra por aqui, cuando hay un error en el login.
	@RequestMapping("/loginError")
	public String login() {
		return "error";
	}

	// Pagina de usuarios
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public ModelAndView usuarios() {

		Map<String, String> model = new HashMap<String, String>();

		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("registrar", model);

		return view;
	}

	@RequestMapping(value = "/registroCompleto", method = RequestMethod.GET)
	public ModelAndView regcompletado() {

		ModelAndView view = new ModelAndView("registroCompletado");

		return view;
	}
	

}

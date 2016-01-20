package ucm.fdi.tfg.users.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import ucm.fdi.tfg.users.business.boundary.UserManager;

@Controller
public class UserController {
	
	 UserManager userManager;
	
	@Autowired
	public UserController(UserManager userManager)
	{
		this.userManager = userManager;
	}
	
	//Para mostrar los usuarios en Home
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("usuario", null);
		
		ModelAndView view = new ModelAndView("home", model);
		
		return view;	

	}
	/*
	@RequestMapping(value = "/bienvenido", method = RequestMethod.GET)
	public ModelAndView prueba(User user) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("usuario",userManager.loadUserByUsername(user.getName()));
		
		ModelAndView view = new ModelAndView("logueados", model);
		
		return view;	

	}*/
	
	@RequestMapping(value = "/bienvenido", method = RequestMethod.GET)
	public ModelAndView prueba() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("usuario",null);
		
		ModelAndView view = new ModelAndView("logueados", model);
		
		return view;	

	}
	
	  @RequestMapping("/login")
	  public String login() {
	    return "holamundo";
	  }
	 
	  // Login form with error
	  @RequestMapping("/login-error.html")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "login.html";
	  }
	
	
}

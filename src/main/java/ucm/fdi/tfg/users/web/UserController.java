package ucm.fdi.tfg.users.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import ucm.fdi.tfg.users.business.boundary.UserManager;
import ucm.fdi.tfg.users.business.entity.User;

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
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String desconectar(User usuario) {	
		
		return "redirect:/holamundo";
	}
	
	
}

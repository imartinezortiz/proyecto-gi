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
import ucm.fdi.tfg.users.business.entity.User;

@Controller
public class UserController {
	
	 UserManager userManager;
	
	@Autowired
	public UserController(UserManager userManager)
	{
		this.userManager = userManager;
	}
		
	//Por aqui entra la aplicacion
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("usuario", null);
		
		ModelAndView view = new ModelAndView("home", model);
		
		return view;	

	}
	
	// Redirecciona al menu despues de hacer login
	@RequestMapping(value = "/bienvenido", method = RequestMethod.GET)
	public ModelAndView prueba() {
		
		Map<String, String> model = new HashMap<String, String>();
		
		model.put("usuario",SecurityContextHolder.getContext().getAuthentication().getName());
		
		ModelAndView view = new ModelAndView("Menu", model);
		
		return view;	

	}
	
	// Entra por aqui, cuando hay un error en el login.
	  @RequestMapping("/loginError")
	  public String login() {
	    return "error";
	  }  
	  
	 //Pagina de usuarios 
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public ModelAndView usuarios() {

		Map<String, String> model = new HashMap<String, String>();

		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("registrar", model);

		return view;

	}

	  @RequestMapping(value = "/addUser", method = RequestMethod.POST)
		public String add(User user) {
			
		 try{ 
			 userManager.save(user);
			 
		 }catch(Exception e){
			 return "redirect:/error";
		 }
		 	return "redirect:/registroCompleto";
		 
		 }
	  
	  @RequestMapping(value = "/registroCompleto", method = RequestMethod.GET)
		public ModelAndView regcompletado() {
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			model.put("usuario",null);
			
			ModelAndView view = new ModelAndView("registroCompletado", model);
			
			return view;	

		}
	  
	  
	  
	  /*
		 
	  // Login form with error
	  @RequestMapping("/login-error.html")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "login.html";
	  }
	  */
	  
	  /*
	  @RequestMapping(value = "/error", method = RequestMethod.GET)
		public ModelAndView error() {
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			model.put("usuario",null);
			
			ModelAndView view = new ModelAndView("error", model);
			
			return view;	

		}
		*/
	  /*
		@RequestMapping(value = "/bienvenido", method = RequestMethod.GET)
		public ModelAndView prueba(User user) {
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			model.put("usuario",userManager.loadUserByUsername(user.getName()));
			
			ModelAndView view = new ModelAndView("logueados", model);
			
			return view;	

		}*/
		
}

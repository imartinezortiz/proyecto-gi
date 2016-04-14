package ucm.fdi.tfg.users.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.users.business.boundary.NuevoInvestigadorDTO;
import ucm.fdi.tfg.users.business.boundary.UserDTO;
import ucm.fdi.tfg.users.business.boundary.UserManager;


@Controller
public class UserController {

	private UserManager users;

	@Autowired
	public UserController(UserManager userManager) {
		this.users = userManager;
	}

	// Redirecciona al menu despues de hacer login
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public ModelAndView prueba() {

		ModelAndView view = new ModelAndView("menus/inicio");
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		return view;
	}

	// Entra por aqui, cuando hay un error en el login.
	@RequestMapping("/loginError")
	public String login() {
		return "error";
	}
	
	// ------------------------------------------------------- ADMIN ---------------------------------------------------------------------
	
	@RequestMapping(value = "/altaAdmin", method = RequestMethod.GET)
	public ModelAndView altaAdmin() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("userDTO", new UserDTO());
		
		ModelAndView view = new ModelAndView("usuarios/adminForm", model);
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		return view;
	}
	
	@RequestMapping(value = "/altaAdmin", method = RequestMethod.POST)
	public ModelAndView addAdmin(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult errors) {
		
		ModelAndView view = null;
		
		if (errors.hasErrors()){			
			view = new ModelAndView ("usuarios/adminForm");
			view.addObject("userDTO", userDTO);
		}
		else{
			users.addAdmin(userDTO);
			view = new ModelAndView ("redirect:/inicio");			
		}	
				
		return view;
	}
	
	
	@RequestMapping(value = "/altaGestor", method = RequestMethod.GET)
	public ModelAndView altaGestor() {
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("userDTO", new UserDTO());		
		
		ModelAndView view = new ModelAndView("usuarios/gestorForm",model);
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		return view;
	}
	
	@RequestMapping(value = "/altaGestor", method = RequestMethod.POST)
	public ModelAndView addGestor(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult errors) {
		ModelAndView view = null;
		
		if (errors.hasErrors()){			
			view = new ModelAndView ("usuarios/gestorForm");
			view.addObject("userDTO", userDTO);
		}
		else{
			users.addGestor(userDTO);
			view = new ModelAndView ("redirect:/inicio");			
		}	
				
		return view;
	}
	
	
	@RequestMapping(value = "/altaInvestigador", method = RequestMethod.GET)
	public ModelAndView altaInvestigador() {
				
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("nuevoInvestigadorDTO", new NuevoInvestigadorDTO());
		ModelAndView view = new ModelAndView("usuarios/investigadorForm", model);
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		return view;
	}

	@RequestMapping(value = "/altaInvestigador", method = RequestMethod.POST)
	public ModelAndView addInvestigador(@ModelAttribute("nuevoInvestigadorDTO") @Valid NuevoInvestigadorDTO nuevoInvestigadorDTO, BindingResult errors) {

		ModelAndView view = null;
		
		if (errors.hasErrors()){			
			view = new ModelAndView ("usuarios/investigadorForm");
			view.addObject("nuevoInvestigadorDTO", nuevoInvestigadorDTO);
		}
		else{
			users.addInvestigador(nuevoInvestigadorDTO);
			view = new ModelAndView ("redirect:/inicio");			
		}				
		
		return view;
	}
	
	
	


}

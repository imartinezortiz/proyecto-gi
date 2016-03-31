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
import ucm.fdi.tfg.users.business.entity.User;

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
	
	// ------------------------------------------------------- ADMIN ---------------------------------------------------------------------
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView menuAdmin() {

		ModelAndView view = new ModelAndView("menuAdmin");

		return view;
	}

	@RequestMapping(value = "/altaInvestigador", method = RequestMethod.GET)
	public ModelAndView altaInvestigador() {
				
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("nuevoInvestigadorDTO", new NuevoInvestigadorDTO());
		ModelAndView view = new ModelAndView("usuarios/investigadorForm", model);
		
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
			view = new ModelAndView ("redirect:/admin");			
		}				
		
		return view;
	}
	
	@RequestMapping(value = "/altaAdmin", method = RequestMethod.GET)
	public ModelAndView altaAdmin() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("userDTO", new UserDTO());
		
		ModelAndView view = new ModelAndView("usuarios/adminForm", model);
		
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
			view = new ModelAndView ("redirect:/admin");			
		}	
				
		return view;
	}
	
	
	@RequestMapping(value = "/altaGestor", method = RequestMethod.GET)
	public ModelAndView altaGestor() {
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("userDTO", new UserDTO());		
		
		ModelAndView view = new ModelAndView("usuarios/gestorForm",model);

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
			view = new ModelAndView ("redirect:/admin");			
		}	
				
		return view;
	}
	
	
	
	// -------------------------------------------------------------------------------------------------------
	
	
	

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

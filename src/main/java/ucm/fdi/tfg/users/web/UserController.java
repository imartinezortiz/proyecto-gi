package ucm.fdi.tfg.users.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.proyecto.business.boundary.NuevoProyectoDTO;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
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
	
	@RequestMapping(value = "/administradores", method = RequestMethod.GET)
	public ModelAndView listarAdministradores() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tipoUsuario", "administradores");
		model.put("usuarios", users.findAllAdministradores());
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		ModelAndView view = new ModelAndView("usuarios/listarUsuarios", model);
		
		return view;
		
	}
	
	@RequestMapping(value = "/altaAdmin", method = RequestMethod.GET)
	public ModelAndView altaAdmin() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("userDTO", new UserDTO());
		model.put("modo", "altaAdmin");
		model.put("modoTitulo", "Alta");

		
		ModelAndView view = new ModelAndView("usuarios/adminForm", model);
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		return view;
	}
	
	@RequestMapping(value = "/altaAdmin", method = RequestMethod.POST)
	public ModelAndView addAdmin(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult errors) {
		
		ModelAndView view = null;
		
		if (errors.hasErrors()){			
			view = new ModelAndView ("usuarios/adminForm");
			view.addObject("modoTitulo", "Alta");
			view.addObject("userDTO", userDTO);
		}
		else{
			users.addAdmin(userDTO);
			view = new ModelAndView ("redirect:/inicio");			
		}	
				
		return view;
	}
	
	@RequestMapping(value = "edit/administradores/{id}/", method = RequestMethod.GET)
	public ModelAndView editAdministrador(@PathVariable(value="id") Long id) {
		
		ModelAndView view = null;
		
		view = new ModelAndView("usuarios/adminForm");
		
		view.addObject("modoTitulo", "Editar");
		
		view.addObject("modo", "");
		User usuarioEditar = users.findOneUser(id);
		
		UserDTO usuarioEditarDTO = users.UserToUserDTO(usuarioEditar);
		
		view.addObject(usuarioEditarDTO);
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		return view;
	}
	
	@RequestMapping(value = "edit/administradores/{id}/", method = RequestMethod.POST)
	public ModelAndView editarAdministradorPost(@ModelAttribute("userDTO") @Valid UserDTO  userDTO, @PathVariable(value="id") Long id, BindingResult errors) {
		
		ModelAndView view = null;			
		
		if (errors.hasErrors()) {
			view = new ModelAndView("usuarios/adminForm");
			view.addObject("modoTitulo", "Editar");	
			view.addObject("modo", "");
			view.addObject("userDTO", userDTO);						
		} else {
			users.editar(userDTO,id);
			view = new ModelAndView("redirect:/administradores");
		}
		
		return view;		
	}
	
	
	@RequestMapping(value = "/gestores", method = RequestMethod.GET)
	public ModelAndView listarGestores() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tipoUsuario", "gestores");
		model.put("usuarios", users.findAllGestores());
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		ModelAndView view = new ModelAndView("usuarios/listarUsuarios", model);
		
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
	
	
	@RequestMapping(value = "/investigadores", method = RequestMethod.GET)
	public ModelAndView listarInvestigadores() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tipoUsuario", "investigadores");
		model.put("usuarios", users.findAllUserInvestigadores());
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		ModelAndView view = new ModelAndView("usuarios/listarUsuarios", model);
		
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

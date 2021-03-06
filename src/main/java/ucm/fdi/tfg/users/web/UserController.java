/**
 * This file is part of proyecto-gi.
 *
 * proyecto-gi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * proyecto-gi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with proyecto-gi.  If not, see <http://www.gnu.org/licenses/>.
 */
package ucm.fdi.tfg.users.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.centros.business.boundary.CentrosManager;
import ucm.fdi.tfg.departamentos.business.boundary.DepartamentosManager;
import ucm.fdi.tfg.users.business.boundary.NuevoInvestigadorDTO;
import ucm.fdi.tfg.users.business.boundary.UserDTO;
import ucm.fdi.tfg.users.business.boundary.UserManager;
import ucm.fdi.tfg.users.business.entity.Investigador;
import ucm.fdi.tfg.users.business.entity.User;


@Controller
public class UserController {

	private UserManager users;
	
	private CentrosManager centros;

	private DepartamentosManager departamentos;
	
	@Autowired
	public UserController(UserManager userManager, CentrosManager centros, DepartamentosManager departamentos) {
		this.users = userManager;
		this.centros = centros;
		this.departamentos = departamentos;
	}

	// Redirecciona al menu despues de hacer login
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public ModelAndView inicio() {

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
		model.put("modo", "altaAdmin");
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
		model.put("tipoUsuario", "administrador");
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		ModelAndView view = new ModelAndView("usuarios/userForm", model);
		
		return view;
	}
	
	@RequestMapping(value = "/altaAdmin", method = RequestMethod.POST)
	public ModelAndView altaAdminPost(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult errors) {
		
		ModelAndView view = null;
		
		if (errors.hasErrors()){			
			view = new ModelAndView ("usuarios/userForm");
			view.addObject("modoTitulo", "Alta");
			view.addObject("tipoUsuario", "administrador");
			view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
			view.addObject("userDTO", userDTO);
		}
		else{
			users.addAdmin(userDTO);
			view = new ModelAndView ("redirect:/inicio");			
		}	
				
		return view;
	}
	
	@RequestMapping(value = "edit/administradores/{id}/", method = RequestMethod.GET)
	public ModelAndView editarAdministrador(@PathVariable(value="id") Long id) {
		
		ModelAndView view = new ModelAndView("usuarios/userForm");
		
		view.addObject("modoTitulo", "Editar");
		view.addObject("tipoUsuario", "administrador");
		view.addObject("modo", "");
		
		User usuarioEditar = users.findOneUser(id);
		
		UserDTO usuarioEditarDTO = users.UserToUserDTO(usuarioEditar);
		
		view.addObject(usuarioEditarDTO);
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		return view;
	}
	
	@RequestMapping(value = "edit/administradores/{id}/", method = RequestMethod.POST)
	public ModelAndView editarAdministradorPost(@ModelAttribute("userDTO") @Valid UserDTO  userDTO, BindingResult errors ,@PathVariable(value="id") Long id ) {
	
		ModelAndView view = null;	
		
		//if (errors.hasErrors()) {
		if (errors.getErrorCount() > 2 || errorPassword(errors.getAllErrors() )) {
			view = new ModelAndView("usuarios/userForm");
			view.addObject("modoTitulo", "Editar");	
			view.addObject("modo", "");
			view.addObject("tipoUsuario", "administrador");
			view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
			view.addObject("userDTO", userDTO);						
		} else {
			users.editar(userDTO,id);
			view = new ModelAndView("redirect:/administradores");
		}
		
		return view;		
	}
	
	// ------------------------------------------------------- GESTORES ---------------------------------------------------------------------

	
	private boolean errorPassword(List<ObjectError> errors) {
		  for(ObjectError e: errors) {
		    if ( !(e instanceof FieldError) ) {
		      return false;
		    }
		    String field = ((FieldError)e).getField();
		    if ( ! "password".equals(field) || ! "nuevaPassword".equals(field)) {
		      return false;
		    }
		  }
		  return true;
		}
	
	@RequestMapping(value = "/gestores", method = RequestMethod.GET)
	public ModelAndView listarGestores() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tipoUsuario", "gestores");
		model.put("usuarios", users.findAllGestores());
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("modo", "altaGestor");
		ModelAndView view = new ModelAndView("usuarios/listarUsuarios", model);
		
		return view;
		
	}
	
	
	@RequestMapping(value = "/altaGestor", method = RequestMethod.GET)
	public ModelAndView altaGestor() {
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("userDTO", new UserDTO());
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("modo", "altaGestor");
		model.put("modoTitulo", "Alta");
		model.put("tipoUsuario", "gestor");
		
		ModelAndView view = new ModelAndView("usuarios/userForm",model);

		return view;
	}
	
	@RequestMapping(value = "/altaGestor", method = RequestMethod.POST)
	public ModelAndView altaGestorPost(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult errors) {
		ModelAndView view = null;
		
		if (errors.hasErrors()){			
			view = new ModelAndView ("usuarios/userForm");
			view.addObject("modoTitulo", "Alta");
			view.addObject("tipoUsuario", "gestor");
			view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
			view.addObject("userDTO", userDTO);
		}
		else{
			users.addGestor(userDTO);
			view = new ModelAndView ("redirect:/inicio");			
		}	
				
		return view;
	}
	
	@RequestMapping(value = "edit/gestores/{id}/", method = RequestMethod.GET)
	public ModelAndView editarGestor(@PathVariable(value="id") Long id) {
		
		ModelAndView view = new ModelAndView("usuarios/userForm");
		
		view.addObject("modoTitulo", "Editar");
		view.addObject("tipoUsuario", "gestor");
		view.addObject("modo", "");
		
		User usuarioEditar = users.findOneUser(id);
		
		UserDTO usuarioEditarDTO = users.UserToUserDTO(usuarioEditar);
		
		view.addObject(usuarioEditarDTO);
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		return view;
	}
	
	@RequestMapping(value = "edit/gestores/{id}/", method = RequestMethod.POST)
	public ModelAndView editarGestorPost(@ModelAttribute("userDTO") @Valid UserDTO  userDTO, BindingResult errors ,@PathVariable(value="id") Long id ) {
	
		ModelAndView view = null;			
		
		if (errors.hasErrors()) {
			view = new ModelAndView("usuarios/userForm");
			view.addObject("modoTitulo", "Editar");	
			view.addObject("tipoUsuario", "gestor");
			view.addObject("modo", "");
			view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
			view.addObject("userDTO", userDTO);						
		} else {
			users.editar(userDTO,id);
			view = new ModelAndView("redirect:/gestores");
		}
		
		return view;		
	}
	
	
	// ------------------------------------------------------- INVESTIGADORES ---------------------------------------------------------------------

	
	@RequestMapping(value = "/investigadores", method = RequestMethod.GET)
	public ModelAndView listarInvestigadores() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tipoUsuario", "investigadores");
		model.put("usuarios", users.findAllUserInvestigadores());
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("modo", "altaInvestigador");
		ModelAndView view = new ModelAndView("usuarios/listarUsuarios", model);
		
		return view;
		
	}
	
	
	@RequestMapping(value = "/altaInvestigador", method = RequestMethod.GET)
	public ModelAndView altaInvestigador() {
				
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("modoTitulo", "Alta");
		model.put("modo", "altaInvestigador");
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("nuevoInvestigadorDTO", new NuevoInvestigadorDTO());
		model.put("centros", centros.getAll());
		model.put("departamentos", departamentos.getAll());
		
		ModelAndView view = new ModelAndView("usuarios/investigadorForm", model);
		
		return view;
	}

	@RequestMapping(value = "/altaInvestigador", method = RequestMethod.POST)
	public ModelAndView addInvestigador(@ModelAttribute("nuevoInvestigadorDTO") @Valid NuevoInvestigadorDTO nuevoInvestigadorDTO, BindingResult errors) {

		ModelAndView view = null;
		
		if (errors.hasErrors()){			
			view = new ModelAndView ("usuarios/investigadorForm");
			view.addObject("modoTitulo", "Alta");
			view.addObject("modo", "altaInvestigador");
			view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

			view.addObject("nuevoInvestigadorDTO", nuevoInvestigadorDTO);
		}
		else{
			users.addInvestigador(nuevoInvestigadorDTO);
			view = new ModelAndView ("redirect:/inicio");			
		}				
		
		return view;
	}
	
	@RequestMapping(value = "edit/investigadores/{id}/", method = RequestMethod.GET)
	public ModelAndView editarInvestigador(@PathVariable(value="id") Long id) {
		
		ModelAndView view = new ModelAndView("usuarios/investigadorForm");

		User usuarioEditar = users.findOneUser(id);
		Investigador investigadorEditar = users.findInvestigador(id);
		
		//Pasamos De User e Investigador al DTO para poder pintarlo en el formulario 
		NuevoInvestigadorDTO nuevoInvestigadorDTO = users.UserAndInvestigadorToDTO(usuarioEditar,investigadorEditar);

		view.addObject(nuevoInvestigadorDTO);
		view.addObject("modoTitulo", "Editar");
		view.addObject("modo", "");
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		return view;
	}
	
	@RequestMapping(value = "edit/investigadores/{id}/", method = RequestMethod.POST)
	public ModelAndView editarInvestigadorPost(@ModelAttribute("nuevoInvestigadorDTO") @Valid NuevoInvestigadorDTO nuevoInvestigadorDTO, BindingResult errors ,@PathVariable(value="id") Long id ) {
	
		ModelAndView view = null;			
		
		if (errors.hasErrors()) {
			view = new ModelAndView("usuarios/investigadorForm");
			view.addObject("modoTitulo", "Editar");	
			view.addObject("modo", "");
			view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
			view.addObject("nuevoInvestigadorDTO", nuevoInvestigadorDTO);						
		} else {
			users.editarInvestigador(nuevoInvestigadorDTO,id);
			view = new ModelAndView("redirect:/investigadores");
		}
		
		return view;		
	}
	
	
	


}

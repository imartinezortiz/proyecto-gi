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
package ucm.fdi.tfg.proyecto.web;

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
import ucm.fdi.tfg.proyecto.business.boundary.ProyectosManager;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.unidadesGestoras.business.boundary.UnidadesGestorasManager;
import ucm.fdi.tfg.unidadesGestoras.business.control.UnidadGestoraRepository;
import ucm.fdi.tfg.users.business.boundary.UserManager;


@Controller
public class ProyectoController {
	
	private ProyectosManager proyectos;
	private UserManager users;
	private UnidadesGestorasManager unidadesGestoras;

	@Autowired
	public ProyectoController(ProyectosManager proyectos, UserManager users, UnidadesGestorasManager unidadesGestoras) {
		this.proyectos = proyectos;
		this.users = users;
		this.unidadesGestoras = unidadesGestoras;
	}

		@RequestMapping(value = "/proyectos", method = RequestMethod.GET)
		public ModelAndView listarProeyctos() {
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			model.put("proyectos", proyectos.findAll());
			model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

			ModelAndView view = new ModelAndView("proyectos/listarProyectos", model);

			return view;
		}
		
		@RequestMapping(value = "/proyectos/{id}/", method = RequestMethod.GET)
		public ModelAndView menuProyecto(@PathVariable(value="id") Long id) {
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			model.put("idProyecto",id);
			model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
			ModelAndView view = new ModelAndView("menus/menuProyecto", model);

			return view;
		}
	
	
	//cambiar a /proyectos/nuevo
	@RequestMapping(value = "/crearProyecto", method = RequestMethod.GET)
	public ModelAndView añadirProyecto() {

		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("investigadores", users.findAllUserInvestigadores());
		model.put("unidadesGestoras", unidadesGestoras.findAll());
		model.put("nuevoProyectoDTO",  new NuevoProyectoDTO());
		model.put("modoTitulo", "Alta");
		model.put("modo", "crearProyecto");	
		model.put("idProyecto", "");
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		ModelAndView view = new ModelAndView("proyectos/proyectoForm", model);

		return view;
	}
	
	@RequestMapping(value = "/crearProyecto", method = RequestMethod.POST)
	public ModelAndView añadirProyectoPost(@ModelAttribute("nuevoProyectoDTO") @Valid NuevoProyectoDTO  nuevoProyectoDTO, BindingResult errors) {
		
		ModelAndView view = null;			
		
		if (errors.hasErrors()) {
			view = new ModelAndView("proyectos/proyectoForm");
			view.addObject("investigadores", users.findAllUserInvestigadores());
			view.addObject("unidadesGestoras", unidadesGestoras.findAll());
			view.addObject("modoTitulo", "Alta");
			view.addObject("nuevoProyectoDTO", nuevoProyectoDTO);
			view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		} else {
			proyectos.nuevoProyecto(nuevoProyectoDTO);
			view = new ModelAndView("redirect:/proyectos");
		}
		
		return view;		
	}
	
	@RequestMapping(value = "edit/proyectos/{id}/", method = RequestMethod.GET)
	public ModelAndView editProyecto(@PathVariable(value="id") Long id) {
		
		ModelAndView view = null;
		view = new ModelAndView("proyectos/proyectoForm");
		
		Proyecto proyecto = proyectos.findProyecto(id);
		
		NuevoProyectoDTO proyectDTO = proyectos.proyectoAproyectoDTO(proyecto);
		
		view.addObject("investigadores", users.findAllUserInvestigadores());
		view.addObject("unidadesGestoras", unidadesGestoras.findAll());
		view.addObject("nuevoProyectoDTO" ,proyectDTO);
		view.addObject("modoTitulo", "Editar");
		view.addObject("modo", "");	
		view.addObject("idProyecto", "");
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		return view;
	}
	
	@RequestMapping(value = "/edit/proyectos/{id}/", method = RequestMethod.POST)
	public ModelAndView editarProyectoPost(@ModelAttribute("nuevoProyectoDTO") @Valid NuevoProyectoDTO  editarProyectoDTO, BindingResult errors) {
		
		ModelAndView view = null;			
		
		if (errors.hasErrors()) {
			view = new ModelAndView("proyectos/proyectoForm");
			view.addObject("investigadores", users.findAllUserInvestigadores());
			view.addObject("unidadesGestoras", unidadesGestoras.findAll());
			view.addObject("nuevoProyectoDTO", editarProyectoDTO);	
			view.addObject("modoTitulo", "Editar");
			view.addObject("modo", "");	
		} else {
			proyectos.editar(editarProyectoDTO);
			view = new ModelAndView("redirect:/proyectos");
		}
		
		return view;		
	}

}

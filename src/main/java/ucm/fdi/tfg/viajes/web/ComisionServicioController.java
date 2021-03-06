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
package ucm.fdi.tfg.viajes.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
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

import ucm.fdi.tfg.proyecto.business.boundary.ProyectosManager;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.boundary.UserManager;
import ucm.fdi.tfg.users.business.entity.Investigador;
import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.users.business.entity.UserRole;
import ucm.fdi.tfg.viajes.business.boundary.ComisionServicioManager;
import ucm.fdi.tfg.viajes.business.entity.ComisionServicio;
import ucm.fdi.tfg.viajes.business.entity.EstadoComisionServicioEnum;
import ucm.fdi.tfg.viajes.business.entity.PermisoAusencia;

@Controller
public class ComisionServicioController {

	private ProyectosManager proyectosManager;

	private UserManager users ;
	
	private ComisionServicioManager comision;
	
	@Autowired
	public  ComisionServicioController(ProyectosManager proyectosManager,UserManager users, ComisionServicioManager comision) {
		this.proyectosManager = proyectosManager;
		this.users =users;
		this.comision = comision;
	}
	
	@RequestMapping(value = "/comisionServicio", method = RequestMethod.GET)
	public ModelAndView listarComisionesServicio() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Collection<UserRole> roles = user.getRoles();
		
		for (UserRole rol : roles){
			if ("ROLE_DECANO".equals(rol.getRole())){
				model.put("comisionesServicio", comision.findByEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_DECANO));
			    break;
			}
			else if ("ROLE_UNIDAD_GESTORA".equals(rol.getRole())){
				model.put("comisionesServicio", comision.findByEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_UNIDAD_GESTORA));
				break;
			}
			else if ("ROLE_DIR_DEPARTAMENTO".equals(rol.getRole())){
				model.put("comisionesServicio", comision.findByEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_DPTO));
				break;
			}
			else if ("ROLE_INVESTIGADOR".equals(rol.getRole())){
				model.put("comisionesServicio", comision.findByEstado(EstadoComisionServicioEnum.EDICION));
				break;
			}
			else if ("ROLE_RRHH_CENTRO".equals(rol.getRole())){
				model.put("comisionesServicio", comision.findByEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_RRHH_CENTRO));
				break;
			}
		}
		
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("viajes/listarComisionesServicio", model);

		return view;
	}
	
	@RequestMapping(value = "cambioEstado/comisionServicio/{id}", method = RequestMethod.POST)
	public ModelAndView cambioEstado(@PathVariable(value="id") Long idComision){
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		comision.cambiarEstado(idComision,user);
		
		ModelAndView  view = new ModelAndView("redirect:/comisionServicio");
		
		return view;
		
	}
	
	
	
	
	@RequestMapping(value = "/proyectos/{idProyecto}/altaComisionServicio", method = RequestMethod.GET)
	public ModelAndView ComisionServicioform(@PathVariable(value="idProyecto") Long idProyecto) {

		
		Map<String, Object> model = new HashMap<String, Object>();
		
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Investigador interesado = users.findInvestigador(user.getId());
		
		ComisionServicio comisionServicio = new ComisionServicio(proyecto,interesado);		
		
		model.put("modoTitulo", "Alta");
		model.put("modo", "altaComisionServicio");	
		
		model.put("comisionServicio", comisionServicio);
		
		//Para el nombre en la cabecera
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		model.put("user", user);

		ModelAndView view = new ModelAndView("viajes/comisionServiciosForm", model);
		
		return view;
	}
	
	@RequestMapping(value = "/proyectos/{idProyecto}/altaComisionServicio", method = RequestMethod.POST)
	public ModelAndView añadirComisionServicioPost(@PathVariable(value="idProyecto") Long idProyecto, @ModelAttribute ("comisionServicio") /*@Valid */ComisionServicio comisionServicio, BindingResult errors){

		ModelAndView view = null;	
		
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		if(errors.hasErrors()){
			view = new ModelAndView("viajes/comisionServiciosForm");
			
			comisionServicio.setProyecto(proyecto);
			
			System.out.println("Entra x aqui si hay errores");
			
			view.addObject("user",user);
			view.addObject("modoTitulo", "Alta");
			view.addObject("modo", "altaComisionServicio");	
			//Para el nombre en la cabecera
			view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
			
			view.addObject("comisionServicio", comisionServicio);
			
		}else{
			Investigador interesado = users.findInvestigador(user.getId());

			comisionServicio.setInteresado(interesado);
			comisionServicio.setProyecto(proyecto);
			Map<String, LocalDate> vbs = comisionServicio.getVbs();
			
			vbs.put(comisionServicio.getEstado().toString(), LocalDate.now());
			
			comisionServicio.setVbs(vbs);
			
			comision.add(comisionServicio);
			view = new ModelAndView("redirect:/proyectos");
		}
		
		return view;
	}
	
	@RequestMapping(value = "verDetalle/comisionServicios/{idComision}/", method = RequestMethod.GET)
	public ModelAndView verDetalleComisionServicio(@PathVariable(value="idComision") Long idComision) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		ComisionServicio comisionServicio = this.comision.findOneComision(idComision);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		
		model.put("comisionServicio", comisionServicio);
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("user", user);

		ModelAndView view = new ModelAndView("viajes/verDetalleComisionServicio", model);

		return view;
		
	}
	
	@RequestMapping(value = "rechazar/comisionServicio/{id}", method = RequestMethod.POST)
	public ModelAndView rechazarComisionServicio(@PathVariable(value="id") Long idComision){
	
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		comision.rechazarComision(idComision);
		
		ModelAndView  view = new ModelAndView("redirect:/comisionServicio");
		
		return view;
	}
	
	
	
	@RequestMapping(value = "/proyectos/{idProyecto}/edit/comisiones/{idComision}/", method = RequestMethod.GET)
	public ModelAndView editarComision(@PathVariable(value="idProyecto") Long idProyecto, @PathVariable(value="idComision") Long idComision) {
			
		ModelAndView view = new ModelAndView("viajes/comisionServiciosForm");
		
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		
		Investigador interesado = users.findInvestigador(user.getId());		
		
		ComisionServicio comisionServicio = comision.findOneComision(idComision);
		
		view.addObject("modoTitulo", "Editar");
		view.addObject("modo", "edit/comisiones");	
		
		view.addObject("comisionServicio", comisionServicio);
		
		//Para el nombre en la cabecera
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		view.addObject("user", user);
		
		return view;
	
	}

	@RequestMapping(value = "/proyectos/{idProyecto}/edit/comisiones/{idComision}", method = RequestMethod.POST)
	public ModelAndView editarComisionPost(@PathVariable(value = "idProyecto") Long idProyecto,
			@ModelAttribute("comisionServicio") ComisionServicio comisionServicio, BindingResult errors,
			@PathVariable(value = "idComision") Long idComision) {

		ModelAndView view = null;

		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (errors.hasErrors()) {
			view = new ModelAndView("viajes/comisionServiciosForm");

			comisionServicio.setProyecto(proyecto);

			view.addObject("user", user);
			view.addObject("modoTitulo", "Editar");
			view.addObject("modo", "edit/comisiones");
			// Para el nombre en la cabecera
			view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

			view.addObject("comisionServicio", comisionServicio);

		} else {
			Investigador interesado = users.findInvestigador(user.getId());

			comisionServicio.setInteresado(interesado);
			comisionServicio.setProyecto(proyecto);
			comision.editar(comisionServicio,idComision );
			
			view = new ModelAndView(("redirect:/proyectos/{idProyecto}/viajes"));
		}

		return view;
	}
	
	
}

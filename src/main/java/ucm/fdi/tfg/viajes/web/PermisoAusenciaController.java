package ucm.fdi.tfg.viajes.web;

import java.util.Collection;
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
import ucm.fdi.tfg.viajes.business.boundary.PermisoAusenciaManager;
import ucm.fdi.tfg.viajes.business.entity.ComisionServicio;
import ucm.fdi.tfg.viajes.business.entity.EstadoComisionServicioEnum;
import ucm.fdi.tfg.viajes.business.entity.EstadoPermisoAusenciaEnum;
import ucm.fdi.tfg.viajes.business.entity.PermisoAusencia;

@Controller
public class PermisoAusenciaController {

	private ProyectosManager proyectosManager;

	private UserManager users ;
	
	private PermisoAusenciaManager permisos;
	
	@Autowired
	public  PermisoAusenciaController(ProyectosManager proyectosManager,UserManager users,PermisoAusenciaManager permisos) {
		this.proyectosManager = proyectosManager;
		this.users =users;
		this.permisos = permisos;
	}
	
	@RequestMapping(value = "/permisoAusencia", method = RequestMethod.GET)
	public ModelAndView listarPermisosAusencia() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Collection<UserRole> roles = user.getRoles();
		
		for (UserRole rol : roles){
			if ("ROLE_DECANO".equals(rol.getRole())){
				model.put("comisionesServicio", permisos.findByEstado(EstadoPermisoAusenciaEnum.PENDIENTE_FIRMA_DECANO));
			    break;
			}
			else if ("ROLE_DEPARTAMENTO".equals(rol.getRole())){
				model.put("comisionesServicio", permisos.findByEstado(EstadoPermisoAusenciaEnum.PENDIENTE_FIRMA_DPTO));
				break;
			}
			else if ("ROLE_INVESTIGADOR".equals(rol.getRole())){
				model.put("comisionesServicio", permisos.findByEstado(EstadoPermisoAusenciaEnum.EDICION));
				break;
			}
		}
		
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("viajes/listarPermisosAusencia", model);

		return view;
	}

	
	@RequestMapping(value = "/proyectos/{idProyecto}/altaPermisoAusencia", method = RequestMethod.GET)
	public ModelAndView permisoAusenciaViajeform(@PathVariable(value="idProyecto") Long idProyecto) {
	Map<String, Object> model = new HashMap<String, Object>();
		
		
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		
		Investigador interesado = users.findInvestigador(user.getId());		
		
		PermisoAusencia permisoAusencia = new PermisoAusencia(proyecto,interesado);
					
		model.put("modoTitulo", "Alta");
		model.put("modo", "altaPermisoAusencia");	
		
		model.put("permisoAusencia", permisoAusencia);
		model.put("user", user);
				
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("viajes/permisoAusenciaForm", model);
		
		return view;
	}
	
	@RequestMapping(value = "/proyectos/{idProyecto}/altaPermisoAusencia", method = RequestMethod.POST)
	public ModelAndView añadirPermisoAusenciaPost(@PathVariable(value="idProyecto") Long idProyecto, @ModelAttribute ("permisoAusencia") PermisoAusencia permisoAusencia, BindingResult errors){
		
		ModelAndView view = null;	
		
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		if(errors.hasErrors()){
			view = new ModelAndView("viajes/permisoAusenciaForm");
									
			permisoAusencia.setProyecto(proyecto);

			view.addObject("user",user);
			view.addObject("modoTitulo", "Alta");
			view.addObject("modo", "PermisoAusencia");	
			//Para el nombre en la cabecera
			view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
			
			view.addObject("comisionServicio", permisoAusencia);
			
		}else{
			Investigador interesado = users.findInvestigador(user.getId());

			permisoAusencia.setInteresado(interesado);
			permisoAusencia.setProyecto(proyecto);
			permisos.add(permisoAusencia);
			//view = new ModelAndView("redirect:/proyectos/{idProyecto}/altaViaje");
			//view = new ModelAndView("redirect:/proyectos/{idProyecto}/altaComisionServicio");
			view = new ModelAndView (("redirect:/proyectos/{idProyecto}/viajes"));
		}
		
		return view;
				
		
	}
	
	@RequestMapping(value = "verDetalle/permisoAusencias/{idPermiso}/", method = RequestMethod.GET)
	public ModelAndView verDetallePermisoAusencia(@PathVariable(value="idPermiso") Long idPermiso) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		PermisoAusencia permisoAusencia = this.permisos.findOnePermiso(idPermiso);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		
		model.put("permisoAusencia", permisoAusencia);
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("user", user);

		ModelAndView view = new ModelAndView("viajes/verDetallePermisoAusencia", model);

		return view;
		
	}

	
}

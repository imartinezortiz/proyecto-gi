package ucm.fdi.tfg.viajes.web;

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
import ucm.fdi.tfg.viajes.business.boundary.PermisoAusenciaManager;
import ucm.fdi.tfg.viajes.business.entity.ComisionServicio;
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

	
	@RequestMapping(value = "/proyectos/{idProyecto}/altaPermisoAusencia", method = RequestMethod.GET)
	public ModelAndView permisoAusenciaViajeform(@PathVariable(value="idProyecto") Long idProyecto) {
	Map<String, Object> model = new HashMap<String, Object>();
		
		//Cogemos el proyecto  que vamos a pintar en el Pago
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		
		Investigador inv = proyecto.getInvestigadorPrincipal();
		
		User userInvestigadorPrincipal = users.findOneUser(inv.getId());
		
		PermisoAusencia permisoAusencia = new PermisoAusencia(proyecto);
		
			
		model.put("modoTitulo", "Alta");
		model.put("modo", "altaPermisoAusencia");	
		
		model.put("permisoAusencia", permisoAusencia);
		model.put("user", userInvestigadorPrincipal);
				
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("viajes/permisoAusenciaForm", model);
		
		return view;
	}
	
	@RequestMapping(value = "/proyectos/{idProyecto}/altaPermisoAusencia", method = RequestMethod.POST)
	public ModelAndView añadirPermisoAusenciapost(@ModelAttribute ("permisoAusencia") PermisoAusencia permisoAusencia, BindingResult errors){
		
		ModelAndView view = null;	
		
	/*	if(errors.hasErrors()){
			view = new ModelAndView("viajes/permisoAusenciaForm");
			
		}else{*/
			permisos.add(permisoAusencia);
			view = new ModelAndView("redirect:/proyectos/{idProyecto}/altaComisionServicio");
		//}
		
		return view;
		
		
	}
	
}

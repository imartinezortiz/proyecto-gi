package ucm.fdi.tfg.viajes.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.proyecto.business.boundary.ProyectosManager;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.boundary.UserManager;
import ucm.fdi.tfg.users.business.entity.Investigador;
import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.viajes.business.entity.PermisoAusencia;

@Controller
public class PermisoAusenciaController {

	private ProyectosManager proyectosManager;

	private UserManager users ;
	
	@Autowired
	public  PermisoAusenciaController(ProyectosManager proyectosManager,UserManager users) {
		this.proyectosManager = proyectosManager;
		this.users =users;
	}

	
	@RequestMapping(value = "/proyectos/{idProyecto}/altaPermisoAusenciaViaje", method = RequestMethod.GET)
	public ModelAndView permisoAusenciaViajeform(@PathVariable(value="idProyecto") Long idProyecto) {
	Map<String, Object> model = new HashMap<String, Object>();
		
		//Cogemos el proyecto  que vamos a pintar en el Pago
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		
		Investigador inv = proyecto.getInvestigadorPrincipal();
		
		User userInvestigadorPrincipal = users.findOneUser(inv.getId());
		
		PermisoAusencia permisoAusencia = new PermisoAusencia(proyecto);
		
			
		model.put("modoTitulo", "Alta");
		model.put("modo", "altaPermisoAusenciaViaje");	
		
		model.put("permisoAusencia", permisoAusencia);
		model.put("user", userInvestigadorPrincipal);
				
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("viajes/permisoAusenciaViajeForm", model);
		
		return view;
	}
	
}

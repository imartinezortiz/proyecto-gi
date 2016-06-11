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
import ucm.fdi.tfg.viajes.business.entity.ComisionServicio;
import ucm.fdi.tfg.viajes.business.entity.PermisoAusencia;

@Controller
public class ComisionServicioController {

	private ProyectosManager proyectosManager;

	private UserManager users ;
	
	@Autowired
	public  ComisionServicioController(ProyectosManager proyectosManager,UserManager users) {
		this.proyectosManager = proyectosManager;
		this.users =users;
	}
	
	@RequestMapping(value = "/proyectos/{idProyecto}/altaComisionServicio", method = RequestMethod.GET)
	public ModelAndView ComisionServicioform(@PathVariable(value="idProyecto") Long idProyecto) {
	Map<String, Object> model = new HashMap<String, Object>();
		
		//Cogemos el proyecto  que vamos a pintar en el Pago
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Investigador interesado = users.findInvestigador(user.getId());
		
		ComisionServicio comisionServicio = new ComisionServicio(proyecto,interesado);		
		
		model.put("modoTitulo", "Alta");
		model.put("modo", "altaPermisoAusenciaViaje");	
		
		model.put("comisionServicio", comisionServicio);
		
		//Para el nombre en la cabecera
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		model.put("user", user);
		
		//model.put("interesado", interesado);

		ModelAndView view = new ModelAndView("viajes/comisionServicios", model);
		
		return view;
	}
	
}

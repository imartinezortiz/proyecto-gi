package ucm.fdi.tfg.viajes.web;

import java.util.HashMap;
import java.util.List;
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
import ucm.fdi.tfg.viajes.business.boundary.ViajesManager;
import ucm.fdi.tfg.viajes.business.entity.Dieta;

import ucm.fdi.tfg.viajes.business.entity.JustificacionViaje;
import ucm.fdi.tfg.viajes.business.entity.PermisoAusencia;

@Controller
public class JustificacionViajesController {


	private UserManager users ;
	private ViajesManager viajes;
	private ProyectosManager proyectosManager;
	
	@Autowired
	public JustificacionViajesController (UserManager users, ViajesManager viajes,ProyectosManager proyectosManager){
		this.users=users;
		this.viajes=viajes;
		this.proyectosManager =  proyectosManager;
	}
	
	
	@RequestMapping(value = "/proyectos/{idProyecto}/viajes", method = RequestMethod.GET)
	public ModelAndView listarViajes(@PathVariable(value="idProyecto") Long idProyecto) {
				
		Map<String, Object> model = new HashMap<String, Object>();
		
		List<JustificacionViaje> viajesPorProyecto = viajes.viajesPorProyecto(idProyecto);
		
		model.put("viajesPorProyecto", viajesPorProyecto);
		model.put("idProyecto", idProyecto);
		
		
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("viajes/listarViajes", model);
		
		return view;
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
	
	@RequestMapping(value = "/proyectos/{idProyecto}/altaViaje", method = RequestMethod.GET)
	public ModelAndView viajeform(@PathVariable(value="idProyecto") Long idProyecto) {
	Map<String, Object> model = new HashMap<String, Object>();
		
		//Cogemos el proyecto  que vamos a pintar en el Pago
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		
		Investigador inv = proyecto.getInvestigadorPrincipal();
		
		User userInvestigadorPrincipal = users.findOneUser(inv.getId());
		
		JustificacionViaje viaje = new JustificacionViaje(proyecto);
		
		
		//GastoViaje g = new GastoViaje();
		//viaje.getGastos().add(g);		
		
		
		List<Dieta> dietas = viajes.dameDietas();
		
		
		//Usuarios que participan en un proyecto
		List<User> investigadoresAsignadosAproyecto = proyectosManager.investigadoresProyecto(idProyecto);
		
		model.put("modoTitulo", "Alta");
		model.put("modo", "altaViaje");	
		
		model.put("viaje", viaje);
		model.put("user", userInvestigadorPrincipal);
		
		model.put("dietas", dietas);
		model.put("importePrecioKm", viajes.getCostePorKm());
		
		model.put("investigadoresAsignadosAproyecto", investigadoresAsignadosAproyecto);
		
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("viajes/viajeForm", model);
		
		return view;
	}
	
	@RequestMapping(value = "/proyectos/{idProyecto}/altaViaje", method = RequestMethod.POST)
	public String addViaje(@PathVariable(value="idProyecto") Long idProyecto ,@ModelAttribute JustificacionViaje viaje, BindingResult errors){
	
		viaje.setProyecto(proyectosManager.findProyecto(idProyecto));
		JustificacionViaje viajeGuardado = viajes.save(viaje);
		Long idViaje = viajeGuardado.getId();
		return "redirect:/proyectos/"+idProyecto+"/edit/viajes/"+idViaje+"/";
		
	}
	
	@RequestMapping(value = "/proyectos/{idProyecto}/edit/viajes/{idViaje}/", method = RequestMethod.GET)
	public ModelAndView editarViaje(@PathVariable(value="idProyecto") Long idProyecto, @PathVariable(value="idViaje") Long idViaje) {

		ModelAndView view = new ModelAndView("viajes/viajeForm");
		
		
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		Investigador inv = proyecto.getInvestigadorPrincipal();
		User userActivo = users.findOneUser(inv.getId());
		
		view.addObject("user", userActivo); 
		
		JustificacionViaje viaje = this.viajes.findOneViaje(idViaje);	
		
		
		List<Dieta> dietas = viajes.dameDietas();
		List<User> investigadoresAsignadosAproyecto = proyectosManager.investigadoresProyecto(idProyecto);
		
		view.addObject("investigadoresAsignadosAproyecto", investigadoresAsignadosAproyecto);
		
		view.addObject(viaje);
		
		view.addObject("dietas", dietas);
		view.addObject("importePrecioKm", viajes.getCostePorKm());
		
		view.addObject("modoTitulo", "Editar");
		view.addObject("modo", "edit/viajes");	
		view.addObject("idViaje", idViaje);	
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		return view;
	}
				
	@RequestMapping(value = "/proyectos/{idProyecto}/edit/viajes/{idViaje}", method = RequestMethod.POST)
	public ModelAndView editarViajePost(@PathVariable(value="idProyecto") Long idProyecto ,@ModelAttribute @Valid JustificacionViaje viaje, BindingResult errors, @PathVariable(value="idViaje") Long idViaje){
				ModelAndView view = null;	
				
				Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
				
				viaje.setProyecto(proyecto);
				
				if(errors.hasErrors()){
					
					view = new ModelAndView("viajes/viajeForm");
					
					Investigador inv = proyecto.getInvestigadorPrincipal();
						
					List<Dieta> dietas = viajes.dameDietas();
					List<User> investigadoresAsignadosAproyecto = proyectosManager.investigadoresProyecto(idProyecto);
					
					view.addObject("investigadoresAsignadosAproyecto", investigadoresAsignadosAproyecto);
					
					view.addObject(viaje);
					
					view.addObject("dietas", dietas);
					view.addObject("importePrecioKm", viajes.getCostePorKm());
					
					view.addObject("modoTitulo", "Editar");
					view.addObject("modo", "edit/viajes");	
					view.addObject("idViaje", idViaje);	
					view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
					
					User userActivo = users.findOneUser(inv.getId());
					view.addObject("user", userActivo);
					view.addObject("viaje", viaje);
					
				}else{
					viajes.editar(viaje,idViaje);	
					view = new ModelAndView("redirect:/proyectos/"+idProyecto+"/");
				}
				return view;
	}
		
	@RequestMapping(value = "/proyectos/{idProyecto}/procesando/viajes/{idViaje}", method = RequestMethod.POST)
	public ModelAndView procesandoViaje(@PathVariable(value="idProyecto") Long idProyecto , @PathVariable(value="idViaje") Long idViaje){
				
		this.viajes.procesando(idViaje);
			
		
		ModelAndView  view = new ModelAndView("redirect:/proyectos/"+idProyecto+"/viajes");	
		
		
		
		return view;	
		
	}
	
	@RequestMapping(value = "/procesar/viajes/{idViaje}", method = RequestMethod.GET)
	public ModelAndView procesarPago(@PathVariable(value="idViaje") Long idViaje){
								
		this.viajes.procesar(idViaje);
				
		ModelAndView  view = new ModelAndView("redirect:/investigacion");	
		
		return view;	
		
	}		
	
	
	
}

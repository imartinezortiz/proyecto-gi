package ucm.fdi.tfg.viajes.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import ucm.fdi.tfg.viajes.business.entity.GastoViaje;
import ucm.fdi.tfg.viajes.business.entity.Viaje;

@Controller
public class ViajesController {


	private UserManager users ;
	private ViajesManager viajes;
	private ProyectosManager proyectosManager;
	
	@Autowired
	public ViajesController (UserManager users, ViajesManager viajes,ProyectosManager proyectosManager){
		this.users=users;
		this.viajes=viajes;
		this.proyectosManager =  proyectosManager;
	}
	
	@RequestMapping(value = "/proyecto/{idProyecto}/viajes", method = RequestMethod.GET)
	public ModelAndView viajeform(@PathVariable(value="idProyecto") Long idProyecto) {
	Map<String, Object> model = new HashMap<String, Object>();
		
		//Cogemos el proyecto  que vamos a pintar en el Pago
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		
		Investigador inv = proyecto.getInvestigadorPrincipal();
		
		User userActivo = users.findOneUser(inv.getId());
		
		Viaje viaje = new Viaje(proyecto);
		
		GastoViaje g = new GastoViaje();
		viaje.getGastos().add(g);
		
		
		
		List<Dieta> dietas = viajes.dameDietas();
		
		          Collection <Investigador> investigadores = proyecto.getInvestigadores();
		          
		             
		          //necesito coger le id de la lista de investigadores
		          List <Long> listaInvestidagoresId = new ArrayList<Long>();
		          
		          for (Investigador i : investigadores ){
		              listaInvestidagoresId.add(i.getId());
		        }

		        List<User> investigadoresAsignadosAproyecto = users.findAll(listaInvestidagoresId);

		
		model.put("viaje", viaje);
		model.put("user", userActivo);
		
		model.put("dietas", dietas);
		model.put("importePrecioKm", 0.19);
		
		model.put("investigadoresAsignadosAproyecto", investigadoresAsignadosAproyecto);

		ModelAndView view = new ModelAndView("viajes/viajeForm", model);
		
		return view;
	}
	
	@RequestMapping(value = "/proyecto/{idProyecto}/viajes", method = RequestMethod.POST)
	public String addViaje(@PathVariable(value="idProyecto") Long idProyecto ,@ModelAttribute Viaje viaje, BindingResult errors){
	
		viaje.setProyecto(proyectosManager.findProyecto(idProyecto));
		viajes.save(viaje);
		return "redirect:/inicio";
		
	}
	
	
	
	
	
}

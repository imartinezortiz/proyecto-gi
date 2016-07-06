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
			else if (rol.getRole().equals("ROLE_UNIDAD_GESTORA")){
				model.put("comisionesServicio", comision.findByEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_UNIDAD_GESTORA));
				break;
			}
			else if (rol.getRole().equals("ROLE_DEPARTAMENTO")){
				model.put("comisionesServicio", comision.findByEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_DPTO));
				break;
			}
			else if (rol.getRole().equals("ROLE_INVESTIGADOR")){
				model.put("comisionesServicio", comision.findByEstado(EstadoComisionServicioEnum.EDICION));
				break;
			}
		}
		
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("viajes/listarComisionesServicio", model);

		return view;
	}
	
	@RequestMapping(value = "cambioEstado/comisionServicio/{id}", method = RequestMethod.GET)
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
	
	
	
}

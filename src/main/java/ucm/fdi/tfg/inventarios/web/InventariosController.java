package ucm.fdi.tfg.inventarios.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.inventarios.business.boundary.InventariosManager;
import ucm.fdi.tfg.inventarios.business.entity.Inventario;
import ucm.fdi.tfg.proyecto.business.boundary.ProyectosManager;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.boundary.UserManager;
import ucm.fdi.tfg.users.business.entity.Investigador;
import ucm.fdi.tfg.users.business.entity.User;

@Controller
public class InventariosController {

	private ProyectosManager proyectos;	
	private UserManager users;
	private InventariosManager inventarios;
	
	@Autowired
	public InventariosController (ProyectosManager proyectos, UserManager users,InventariosManager inventarios){
		this.proyectos = proyectos;
		this.users = users;	
		this.inventarios = inventarios;
	}
		
	
	@RequestMapping(value = "/proyecto/{idProyecto}/inventarios", method = RequestMethod.GET)
	public ModelAndView pagoform(@PathVariable(value="idProyecto") Long idProyecto) {
				
		Map<String, Object> model = new HashMap<String, Object>();
		
		Proyecto proyecto = proyectos.findProyecto(idProyecto);
		
		Investigador inv = proyecto.getInvestigadorPrincipal();
		
		User userActivo = users.findOneUser(inv.getId());		
		
		Inventario inventario = new Inventario(proyecto);
				
		model.put("inventario", inventario);
		model.put("user", userActivo); 

		ModelAndView view = new ModelAndView("inventariosForm", model);
		
		return view;
	}
	
	@RequestMapping(value = "/proyecto/{idProyecto}/inventarios", method = RequestMethod.POST)
	public String addPago(@PathVariable(value="idProyecto") Long idProyecto ,@ModelAttribute Inventario inventario, BindingResult errors){
						
		inventarios.nuevoInventario(idProyecto, inventario);
		return "redirect:/registroCompleto";
		
	}
	
	
	
}

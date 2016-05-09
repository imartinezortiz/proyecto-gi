package ucm.fdi.tfg.inventarios.web;

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
		
	
	@RequestMapping(value = "/proyectos/{idProyecto}/altaInventario", method = RequestMethod.GET)
	public ModelAndView altaInventario(@PathVariable(value="idProyecto") Long idProyecto) {
				
		Map<String, Object> model = new HashMap<String, Object>();
		
		Proyecto proyecto = proyectos.findProyecto(idProyecto);
		Investigador inv = proyecto.getInvestigadorPrincipal();
		User userActivo = users.findOneUser(inv.getId());
		
		Inventario inventario = new Inventario(proyecto);
		
		model.put("inventario", inventario);
		model.put("user", userActivo); 
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		
		model.put("modo", "altaInventario");
		model.put("modoTitulo", "Alta Inventario");
		
		
		ModelAndView view = new ModelAndView("inventarios/inventarioForm",model);

		return view;
	}
	
	@RequestMapping(value = "/proyectos/{idProyecto}/altaInventario/", method = RequestMethod.POST)
	public ModelAndView altaIventarioPost(@PathVariable(value="idProyecto") Long idProyecto ,@ModelAttribute @Valid Inventario inventario, BindingResult errors){
				
		ModelAndView view = null;		
		
		if(errors.hasErrors()){
			Proyecto proyecto = proyectos.findProyecto(idProyecto);
		
			Investigador inv = proyecto.getInvestigadorPrincipal();
			
			User userActivo = users.findOneUser(inv.getId());	
			
			inventario.setProyecto(proyecto);
			view = new ModelAndView("inventarios/inventarioForm");
			view.addObject("inventario",inventario);
			
			//Se pasa un IdInventario para th:action de EDITAR
			view.addObject("idInventario","");
			view.addObject("user",userActivo);
			
			view.addObject("modo", "altaInventario");
			view.addObject("modoTitulo", "Alta Inventario");
			view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
						
		}else{
			inventarios.nuevoInventario(idProyecto, inventario);
			view = new ModelAndView("redirect:/proyectos/"+idProyecto+"/");
			
		}
		return view;			
		
	}	
		
	@RequestMapping(value = "/proyectos/{idProyecto}/inventarios", method = RequestMethod.GET)
	public ModelAndView listarInventarios(@PathVariable(value="idProyecto") Long idProyecto) {
				
		Map<String, Object> model = new HashMap<String, Object>();
		
		List<Inventario> inventariosPorProyecto = inventarios.inventariosPorProyecto(idProyecto);
		
		model.put("inventariosPorProyecto", inventariosPorProyecto);
		
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("inventarios/listarInventarios", model);
		
		return view;
	}
		
	
	@RequestMapping(value = "/proyectos/{idProyecto}/edit/inventarios/{idInventario}", method = RequestMethod.GET)
	public ModelAndView editarInventario(@PathVariable(value="idProyecto") Long idProyecto, @PathVariable(value="idInventario") Long idInventario) {
			
		ModelAndView view = new ModelAndView("inventarios/inventarioForm");
			
		Proyecto proyecto = proyectos.findProyecto(idProyecto);
		Investigador inv = proyecto.getInvestigadorPrincipal();
		User userActivo = users.findOneUser(inv.getId());
		
		view.addObject("user", userActivo); 
		
		Inventario inventario = this.inventarios.findOneInventario(idInventario);		
		
		view.addObject(inventario);
		view.addObject("modoTitulo", "Editar Inventario");
		view.addObject("modo", "edit/inventarios");		
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		
		return view;	
		
	}

	@RequestMapping(value = "/proyectos/{idProyecto}/edit/inventarios/{idInventario}", method = RequestMethod.POST)
	public ModelAndView editarInventarioPost(@PathVariable(value="idProyecto") Long idProyecto , @PathVariable(value="idInventario") Long idInventario,@ModelAttribute @Valid Inventario inventario, BindingResult errors){
		
		ModelAndView view = null;		
		
		Proyecto proyecto = proyectos.findProyecto(idProyecto);	
		inventario.setProyecto(proyecto);
		
		if(errors.hasErrors()){
			
			view = new ModelAndView("inventarios/inventarioForm");			
					
			Investigador inv = proyecto.getInvestigadorPrincipal();			
			User userActivo = users.findOneUser(inv.getId());				
								
			view.addObject("inventario",inventario);
			
			//Se pasa un IdInventario para th:action de EDITAR
			view.addObject("idInventario",idInventario);
			view.addObject("user",userActivo);			
			view.addObject("modo", "edit/inventarios");
			view.addObject("modoTitulo", "Editar Inventario");
			view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
						
		}else{
						
			this.inventarios.editar(inventario,idInventario);
			view = new ModelAndView("redirect:/proyectos/"+idProyecto+"/");			
		}
		
		return view;	
		
	}
	
	
	
	
}

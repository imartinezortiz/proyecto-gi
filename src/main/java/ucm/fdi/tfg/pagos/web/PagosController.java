package ucm.fdi.tfg.pagos.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.pagos.business.boundary.PagosManager;
import ucm.fdi.tfg.pagos.business.entity.Pago;
import ucm.fdi.tfg.proyecto.business.boundary.ProyectosManager;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.boundary.UserManager;
import ucm.fdi.tfg.users.business.entity.Investigador;
import ucm.fdi.tfg.users.business.entity.User;

@Controller
public class PagosController {
	//private static final Logger logger = LoggerFactory.getLogger(PagoController.class);
	
	private PagosManager pagoManager;
	private ProyectosManager proyectosManager;

	
	private UserManager users;
	
	@Autowired
	public PagosController (PagosManager pagoManager,ProyectosManager gestorManager, UserManager users){
		this.pagoManager = pagoManager;
		this.proyectosManager = gestorManager;
		this.users = users;
	}
	

	
	@RequestMapping(value = "/proyectos/{idProyecto}/altaPago", method = RequestMethod.GET)
	public ModelAndView pagoform(@PathVariable(value="idProyecto") Long idProyecto) {
		
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		//Cogemos el proyecto  que vamos a pintar en el Pago
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		
		Investigador inv = proyecto.getInvestigadorPrincipal();
		
		User userActivo = users.findOneUser(inv.getId());
		
		Pago pago = new Pago(proyecto);
		
		//Gasto g =new Gasto();
		//pago.getGastos().add(g);
		
		model.put("pago", pago);
		model.put("user", userActivo);
		model.put("modoTitulo", "Alta");
		model.put("modo", "altaPago");	
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("pagos/pagoForm", model);
		
		return view;
	}
		
	
	//Igual que en thymeleaf le decimos mediante th:object el objeto q mandamos,  
	//en spring mediante ModelAttribute le decimos el tipo de objeto que le llega.
	@RequestMapping(value = "/proyectos/{idProyecto}/altaPago", method = RequestMethod.POST)
	public ModelAndView addPago(@PathVariable(value="idProyecto") Long idProyecto ,@ModelAttribute @Valid Pago pago, BindingResult errors){
		
		ModelAndView view = null;	
		
		if(errors.hasErrors()){
			
			view = new ModelAndView("pagos/pagoForm");
			Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
	
			pago.setProyecto(proyecto);
	
			Investigador inv = proyecto.getInvestigadorPrincipal();
			
			User userActivo = users.findOneUser(inv.getId());
			view.addObject("pago", pago);
			view.addObject("user", userActivo);
			view.addObject("modoTitulo", "Alta");
			view.addObject("modo", "altaPago");	
			
		}else{
			pago.setProyecto(proyectosManager.findProyecto(idProyecto));
			Pago pagoGuardado = pagoManager.save(pago);	
			Long idPago = pagoGuardado.getId();
			view = new ModelAndView("redirect:/proyectos/"+idProyecto+"/edit/pagos/"+idPago+"/");
		}
		return view;
		
		
	}
	
	


	@RequestMapping(value = "/proyectos/{idProyecto}/pagos", method = RequestMethod.GET)
	public ModelAndView listarPagos(@PathVariable(value="idProyecto") Long idProyecto) {
				
		Map<String, Object> model = new HashMap<String, Object>();
		
		List<Pago> pagosPorProyecto = pagoManager.pagosPorProyecto(idProyecto);
		
		model.put("pagosPorProyecto", pagosPorProyecto);
		model.put("idProyecto", idProyecto);		
		
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("pagos/listarPagos", model);
		
		return view;
	}
	
	
	@RequestMapping(value = "/proyectos/{idProyecto}/edit/pagos/{idPago}/", method = RequestMethod.GET)
	public ModelAndView editarPago(@PathVariable(value="idProyecto") Long idProyecto, @PathVariable(value="idPago") Long idPago) {
			
		ModelAndView view = new ModelAndView("pagos/pagoForm");
			
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		Investigador inv = proyecto.getInvestigadorPrincipal();
		User userActivo = users.findOneUser(inv.getId());
		
		view.addObject("user", userActivo); 
		
		Pago pago = this.pagoManager.findOnePago(idPago);		
		
		view.addObject(pago);
		
		view.addObject("modoTitulo", "Editar");
		view.addObject("modo", "edit/pagos");	
		view.addObject("idPago", idPago);	
		view.addObject("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		
		return view;	
		
	}
	
	@RequestMapping(value = "/proyectos/{idProyecto}/edit/pagos/{idPago}", method = RequestMethod.POST)
	public ModelAndView editarPagoPost(@PathVariable(value="idProyecto") Long idProyecto ,@ModelAttribute @Valid Pago pago, BindingResult errors, @PathVariable(value="idPago") Long idPago){
		
		ModelAndView view = null;	
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		
		pago.setProyecto(proyecto);
		
		if(errors.hasErrors()){
			
			view = new ModelAndView("pagos/pagoForm");
	
			Investigador inv = proyecto.getInvestigadorPrincipal();
			
			User userActivo = users.findOneUser(inv.getId());
			view.addObject("pago", pago);
			view.addObject("user", userActivo);
			view.addObject("modoTitulo", "Editar");
			view.addObject("modo", "edit/pagos");	
			view.addObject("idPago", idPago);
			
		}else{
			pagoManager.editar(pago,idPago);	
			view = new ModelAndView("redirect:/proyectos/"+idProyecto+"/");
		}
		return view;
		
		
	}
	
	@RequestMapping(value = "/proyectos/{idProyecto}/procesando/pagos/{idPago}", method = RequestMethod.POST)
	public ModelAndView procesandoPago(@PathVariable(value="idProyecto") Long idProyecto , @PathVariable(value="idPago") Long idPago){
				
		this.pagoManager.procesando(idPago);			
		
		ModelAndView  view = new ModelAndView("redirect:/proyectos/"+idProyecto+"/pagos");	
						
		return view;	
		
	}
	
	@RequestMapping(value = "/procesar/pagos/{idPago}", method = RequestMethod.GET)
	public ModelAndView procesarPago(@PathVariable(value="idPago") Long idPago){
										
		this.pagoManager.procesar(idPago);
				
		ModelAndView  view = new ModelAndView("redirect:/investigacion");	
		
		
		return view;	
		
	}		
	
	
}

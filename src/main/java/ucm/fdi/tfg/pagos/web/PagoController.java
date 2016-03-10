package ucm.fdi.tfg.pagos.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.pagos.business.boundary.PagoManager;
import ucm.fdi.tfg.pagos.business.entity.Gasto;
import ucm.fdi.tfg.pagos.business.entity.Pago;
import ucm.fdi.tfg.proyecto.business.boundary.ProyectosManager;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;

@Controller
public class PagoController {
	private static final Logger logger = LoggerFactory.getLogger(PagoController.class);
	
	PagoManager pagoManager;
	ProyectosManager proyectosManager;
	
	@Autowired
	public PagoController (PagoManager pagoManager,ProyectosManager gestorManager){
		this.pagoManager = pagoManager;
		this.proyectosManager = gestorManager;
	}
		
	@RequestMapping(value = "/proyecto/{idProyecto}/menu/pagos", method = RequestMethod.GET)
	public ModelAndView pagoform(@PathVariable(value="idProyecto") Long idProyecto) {
		
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		//Cogemos el proyecto  que vamos a pintar en el Pago
		Proyecto proyecto = proyectosManager.findProyecto(idProyecto);
		Pago pago = new Pago(proyecto);
		
		Gasto g =new Gasto();
		pago.getGastos().add(g);
		
		model.put("pago", pago);

		ModelAndView view = new ModelAndView("pagos/pagoForm", model);
		
		return view;
	}
		
	
	//Igual que en thymeleaf le decimos mediante th:object el objeto q mandamos,  
	//en spring mediante ModelAttribute le decimos el tipo de objeto que le llega.
	@RequestMapping(value = "/addPago", method = RequestMethod.POST)
	public String addPago(@ModelAttribute Pago pago, BindingResult errors){
				
		if(errors.hasErrors()){
			return "redirect:/errorPago";
		}
		else{
			try{ 				
				pagoManager.save(pago);		 
			}catch(Exception e){
				logger.error("Error añadiendo pago", e);
				return "redirect:/errorPago";
		 }
		return "redirect:/registroCompleto";
		}		
	}
	
	@RequestMapping(value = "/errorPago", method = RequestMethod.GET)
	public ModelAndView errores() {
		
		ModelAndView view = new ModelAndView("error");
		
		return view;
	}
}

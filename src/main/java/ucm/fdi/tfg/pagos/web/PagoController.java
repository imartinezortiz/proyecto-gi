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

import ucm.fdi.tfg.gestores.business.boundary.GestorManager;
import ucm.fdi.tfg.pagos.business.boundary.PagoManager;
import ucm.fdi.tfg.pagos.business.entity.Gasto;
import ucm.fdi.tfg.pagos.business.entity.Pago;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;

@Controller
public class PagoController {
	private static final Logger logger = LoggerFactory.getLogger(PagoController.class);
	
	PagoManager pagoManager;
	GestorManager gestorManager;
	
	@Autowired
	public PagoController (PagoManager pagoManager,GestorManager gestorManager){
		this.pagoManager = pagoManager;
		this.gestorManager = gestorManager;
	}
		
	@RequestMapping(value = "/proyecto/{idProyecto}/menu/pagos", method = RequestMethod.GET)
	public ModelAndView pagoform(@PathVariable(value="idProyecto") Long idProyecto) {
		
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		Pago pago = new Pago();
		//Cogemos el proyecto  que vamos a pintar en el Pago
		Proyecto proyecto = gestorManager.getGestorRepository().getEm().find(Proyecto.class, idProyecto);
		
		Gasto g =new Gasto(null,null,null);
		pago.getGastos().add(g);
		
		pago.setNumContabilidad(proyecto.getNumContabilidad());
		pago.setProyecto(proyecto.getTitulo());
		
		model.put("pago", pago);

		ModelAndView view = new ModelAndView("pagos/pagoForm", model);
		
		return view;
	}
		
	
	//Por aqui entra la aplicacion.
	//Igual que en thymeleaf le decimos mediante th:object el objeto q mandamos,  
	//en spring mediante ModelAttribute le decimos el tipo de objeto que le llega.
	@RequestMapping(value = "/addPago", method = RequestMethod.POST)
	public String addPago(@ModelAttribute Pago pago, BindingResult errors){
		System.out.println("AÑADIENDO PAGO");
		
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
		
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("null", null);

		ModelAndView view = new ModelAndView("error", model);
		
		return view;
	}
}

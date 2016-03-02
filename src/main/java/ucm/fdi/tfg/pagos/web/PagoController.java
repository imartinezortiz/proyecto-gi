package ucm.fdi.tfg.pagos.web;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.pagos.business.boundary.PagoManager;
import ucm.fdi.tfg.pagos.business.entity.Gasto;
import ucm.fdi.tfg.pagos.business.entity.Pago;

@Controller
public class PagoController {
	
	PagoManager pagoManager;
	
	@Autowired
	public PagoController (PagoManager pagoManager){
		this.pagoManager = pagoManager;
	}
		
	@RequestMapping(value = "/pagos", method = RequestMethod.GET)
	public ModelAndView home() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		Pago pago = new Pago();
		Gasto g =new Gasto(null,null,null);
		pago.getGastos().add(g);
		model.put("pago", pago);

		ModelAndView view = new ModelAndView("PagoCabecera", model);
		
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

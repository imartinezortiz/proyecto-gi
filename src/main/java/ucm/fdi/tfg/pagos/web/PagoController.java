package ucm.fdi.tfg.pagos.web;

import java.math.BigDecimal;
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
	
	
	//Por aqui entra la aplicacion
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("usuario", null);
		Pago pago = new Pago();
		pago.getGastos().add(new Gasto("0001", "Hola", BigDecimal.ONE));
		model.put("pago", pago);
		ModelAndView view = new ModelAndView("PagoCabecera", model);
		
		return view;
	}
	
	
	//Por aqui entra la aplicacion
	@RequestMapping(value = "/addPago", method = RequestMethod.POST)
	public String addPago(@ModelAttribute Pago pago, BindingResult errors){
		System.out.println("AÑADIENDO PAGO");
		try{ 
		 pagoManager.save(pago);		 
		}catch(Exception e){
			 return "redirect:/error";
		 }
		
		return "redirect:/registroCompleto";
	}
	
	@RequestMapping(value = "/registroCompleto", method = RequestMethod.GET)
	public ModelAndView regcompletado() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("usuario",null);
		
		ModelAndView view = new ModelAndView("registroCompletado", model);
		
		return view;	

	}
	
	
		
	

}

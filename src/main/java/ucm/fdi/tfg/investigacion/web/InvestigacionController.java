package ucm.fdi.tfg.investigacion.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.inventarios.business.boundary.InventariosManager;
import ucm.fdi.tfg.inventarios.business.entity.Inventario;

@Controller
public class InvestigacionController {
	
	
	private InventariosManager inventarios;
	
	@Autowired
	public InvestigacionController(InventariosManager inventarios){
		this.inventarios = inventarios;
	}
	
	@RequestMapping(value = "/investigacion", method = RequestMethod.GET)
	public ModelAndView listarProcesando() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		List<Inventario> inventariosProcesando = inventarios.inventariosProcesando();
		
		model.put("inventariosProcesando", inventariosProcesando);
		
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		ModelAndView view = new ModelAndView("investigacion/listarProcesando", model);
		
		return view;
		
	}
	

}

package ucm.fdi.tfg.users.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "redirect:/home";
	}
	
	//Por aqui entra la aplicacion
		@RequestMapping(value = "/inventarios", method = RequestMethod.GET)
		public ModelAndView pr() {
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			model.put("usuario", null);
			
			ModelAndView view = new ModelAndView("inventarios", model);
			
			return view;	
		}
}
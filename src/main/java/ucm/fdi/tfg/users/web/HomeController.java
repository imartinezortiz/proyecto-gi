package ucm.fdi.tfg.users.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	
	
	//Entra por aqui la aplicacion, y /home lleva al RequestMapping GET de UserController y ya eso envia la vista home 
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		// si pongo "redirect:/home Redirecciona a una URL /home
		return "home";
	}
	
}
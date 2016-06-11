package ucm.fdi.tfg.viajes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ucm.fdi.tfg.proyecto.business.boundary.ProyectosManager;
import ucm.fdi.tfg.users.business.boundary.UserManager;

@Controller
public class ComisionServicioController {

	private ProyectosManager proyectosManager;

	private UserManager users ;
	
	@Autowired
	public  ComisionServicioController(ProyectosManager proyectosManager,UserManager users) {
		this.proyectosManager = proyectosManager;
		this.users =users;
	}
	
	
}

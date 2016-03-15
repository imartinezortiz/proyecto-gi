package ucm.fdi.tfg.viajes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ucm.fdi.tfg.users.business.boundary.UserManager;
import ucm.fdi.tfg.viajes.business.boundary.ViajesManager;

@Controller
public class ViajesController {


	private UserManager users ;
	private ViajesManager viajes;
	
	@Autowired
	public ViajesController (UserManager users, ViajesManager viajes){
		this.users=users;
		this.viajes=viajes;
	}
	
	public UserManager getUsers() {
		return users;
	}

	public void setUsers(UserManager users) {
		this.users = users;
	}

	public ViajesManager getViajes() {
		return viajes;
	}

	public void setViajes(ViajesManager viajes) {
		this.viajes = viajes;
	}

}

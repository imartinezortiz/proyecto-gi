package ucm.fdi.tfg.users.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ucm.fdi.tfg.users.business.boundary.UserManager;

@Controller
public class UserController {
	
	private UserManager userManager;
	
	@Autowired
	public UserController(UserManager userManager)
	{
		this.userManager = userManager;
	}
	
	
	
}

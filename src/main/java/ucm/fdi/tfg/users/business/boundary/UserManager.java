package ucm.fdi.tfg.users.business.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.users.business.entity.UserRole;
import ucm.fdi.tfg.users.business.repository.UserRepository;

@Service
public class UserManager implements UserDetailsService{
	
	UserRepository repositoryUser;
	//Habrá que añadir el passwordEncoder para que no se vea en la BBDD
	
	@Autowired
	public UserManager(UserRepository usuarios) {
		this.repositoryUser = usuarios;		
	}
	
	public void save(User user){
		
		repositoryUser.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Aqui tenemos que devolver una copia		
		UserDetails user = repositoryUser.findByUsername(username);		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %s not found", username));
		}
		
		return user;
	
		
	}





}

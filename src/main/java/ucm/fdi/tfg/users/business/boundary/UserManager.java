package ucm.fdi.tfg.users.business.boundary;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.users.business.repository.UserRepository;

public class UserManager implements UserDetailsService{
	
	UserRepository repositoryUser;
	
	public void save(User user){
		repositoryUser.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		
		UserDetails user = repositoryUser.findByUsername(name);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %s not found", name));
		}
		
		return user;
	
		
	}





}

package ucm.fdi.tfg.users.business.boundary;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.users.business.entity.UserRole;
import ucm.fdi.tfg.users.business.repository.UserRepository;

@Service
@Transactional
public class UserManager implements UserDetailsService{
	
	UserRepository repositoryUser;
	//Habrá que añadir el passwordEncoder para que no se vea en la BBDD
	

	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserManager(UserRepository usuarios, PasswordEncoder passwordEncoder) {
		this.repositoryUser = usuarios;		
		this.passwordEncoder = passwordEncoder;
	}
	
	public void save(User user){
		//Hasheamos aqui el password	
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.addRole(new UserRole("ROLE_USER"));
		user.addRole(new UserRole("ROLE_ADMIN"));
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

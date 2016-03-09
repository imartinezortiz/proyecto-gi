package ucm.fdi.tfg.users.business.boundary;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.control.InvestigadoresRepository;
import ucm.fdi.tfg.users.business.control.UserRepository;
import ucm.fdi.tfg.users.business.entity.Investigador;
import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.users.business.entity.UserRole;

@Service
@Transactional
public class UserManager implements UserDetailsService{
	
	private UserRepository repositoryUser;
	//Habrá que añadir el passwordEncoder para que no se vea en la BBDD
	
	private InvestigadoresRepository investigadores;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserManager(UserRepository usuarios, InvestigadoresRepository investigadores, PasswordEncoder passwordEncoder) {
		this.repositoryUser = usuarios;		
		this.investigadores = investigadores;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ( principal instanceof User) {
			return (User) principal;
		}
		return null;
	}
	
	private User addUser(User user){
		//Hasheamos aqui el password	
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.addRole(new UserRole("ROLE_USER"));
		user.addRole(new UserRole("ROLE_ADMIN"));
		return repositoryUser.save(user);
	}

	public Investigador addInvestigador(NuevoInvestigadorDTO nuevoInvestigadorDTO){
		
		User user = new User(nuevoInvestigadorDTO.getEmail(), nuevoInvestigadorDTO.getPassword());
		user.addRole(new UserRole("ROLE_INVESTIGADOR"));
		user = addUser(user);	
		
		Persona p = new Persona(nuevoInvestigadorDTO.getNombre(), nuevoInvestigadorDTO.getApellidos(), nuevoInvestigadorDTO.getTelefono(), nuevoInvestigadorDTO.getEmail());
		Investigador inv = new Investigador(user.getId(), p, nuevoInvestigadorDTO.getDepartamento(), nuevoInvestigadorDTO.getCentro());			
		return this.investigadores.save(inv);
	}
	
	public List<Investigador> findAllInvestigadores(){
		return investigadores.findAll();
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

	public Investigador findInvestigador(Long id) {
		return investigadores.findOne(id);
	}
	
	public Investigador getInvestigador(Long id) {
		return investigadores.getOne(id);
	}

	public Investigador findInvestigadorPrincipal(Long id) {
		Investigador inv = investigadores.findInvestigadorPrincipal(id);
		return inv;
	}


}

/**
 * This file is part of proyecto-gi.
 *
 * proyecto-gi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * proyecto-gi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with proyecto-gi.  If not, see <http://www.gnu.org/licenses/>.
 */
package ucm.fdi.tfg.users.business.boundary;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.users.business.control.InvestigadoresRepository;
import ucm.fdi.tfg.users.business.control.UserRepository;
import ucm.fdi.tfg.users.business.entity.Investigador;
import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.users.business.entity.UserRole;

@Service
@Transactional
public class UserManager implements UserDetailsService{
	
	private UserRepository repositoryUser;
	
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
	
	//Lo usamos cuando añadimos un investigador, con est metodo añadimos primero la parte User. Asi obtenemos el id q le metemos al inv.
	private User addUser(User user){
		//Hasheamos aqui el password	
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repositoryUser.save(user);
	}
	
	public User addGestor(UserDTO gestor) {
		User user = new User (gestor.getUsername(), gestor.getPassword(), gestor.getNombre(), gestor.getApellidos(), gestor.getTelefono(), gestor.getEmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.addRole(new UserRole("ROLE_GESTOR"));	
		user.addRole(new UserRole("ROLE_INVESTIGADOR"));
		user.addRole(new UserRole("ROLE_USER"));
		return repositoryUser.save(user);				
	}
	
	public User addAdmin(UserDTO admin) {		
		User user = new User (admin.getUsername(), admin.getPassword(), admin.getNombre(), admin.getApellidos(), admin.getTelefono(), admin.getEmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.addRole(new UserRole("ROLE_ADMIN"));	
		user.addRole(new UserRole("ROLE_GESTOR"));	
		user.addRole(new UserRole("ROLE_INVESTIGADOR"));
		user.addRole(new UserRole("ROLE_USER"));
		return repositoryUser.save(user);		 
	}

	public Investigador addInvestigador(NuevoInvestigadorDTO nuevoInvestigadorDTO){
		User user = new User(nuevoInvestigadorDTO.getUsername(), nuevoInvestigadorDTO.getPassword(), nuevoInvestigadorDTO.getNombre(), nuevoInvestigadorDTO.getApellidos(), nuevoInvestigadorDTO.getTelefono(), nuevoInvestigadorDTO.getEmail());
		user.addRole(new UserRole("ROLE_INVESTIGADOR"));
		user.addRole(new UserRole("ROLE_USER"));
		user = addUser(user);	
		
		Investigador inv = new Investigador(user.getId(), nuevoInvestigadorDTO.getDepartamento(), nuevoInvestigadorDTO.getCentro());			
		return this.investigadores.save(inv);
	}
	
	public Investigador editarInvestigador(NuevoInvestigadorDTO nuevoInvestigadorDTO,Long id){
		User userEdit = repositoryUser.getOne(id);
		userEdit.setNombre(nuevoInvestigadorDTO.getNombre());
		userEdit.setApellidos(nuevoInvestigadorDTO.getApellidos());
		userEdit.setEmail(nuevoInvestigadorDTO.getEmail());
		userEdit.setPassword(passwordEncoder.encode(nuevoInvestigadorDTO.getPassword()));
		userEdit.setTelefono(nuevoInvestigadorDTO.getTelefono());
		userEdit.setUsername(nuevoInvestigadorDTO.getUsername());
		repositoryUser.save(userEdit);
		Investigador investigadorEdit = investigadores.getOne(id);
		investigadorEdit.setCentro(nuevoInvestigadorDTO.getCentro());
		investigadorEdit.setDepartamento(nuevoInvestigadorDTO.getDepartamento());
		return investigadores.save(investigadorEdit);
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

	public Investigador getInvestigadorFindOne(Long id) {
		return investigadores.findOne(id);
	}


	public Investigador findInvestigadorPrincipal(Long id) {
		Investigador inv = investigadores.findInvestigadorPrincipal(id);
		return inv;
	}
	
	
	public List<User> findAllUserInvestigadores(){	
		//Devuelvo mediante una query, los datos en User de los investigadores.
		return repositoryUser.DameDatosUserDeInvestigadores();
	}
	
	public List<User> findAllAdministradores() {
		return repositoryUser.DameDatosUser(new UserRole("ROLE_ADMIN"));
	}
	
	public List<User> findAllGestores() {
		return repositoryUser.DameDatosUser(new UserRole("ROLE_GESTOR"));
	}
	
	public List<User> findAllDecanos() {
		return repositoryUser.DameDatosUser(new UserRole("ROLE_DECANO"));
	}
	
	public List<User> findAllRRHH() {
		return repositoryUser.DameDatosUser(new UserRole("ROLE_RRHH_CENTRO"));
	}
	
	public List<User> findAllGerentes() {
		return repositoryUser.DameDatosUser(new UserRole("ROLE_GERENTE"));
	}
	
	public List<User> findAllDirectorDepartamentos() {
		return repositoryUser.DameDatosUser(new UserRole("ROLE_DIR_DEPARTAMENTO"));
	}


	public User findOneUser(Long id) {
		return this.repositoryUser.findOne(id);
	}

	public List<User> findAll (List<Long>listaInvestidagoresId) {
		return repositoryUser.findAll(listaInvestidagoresId);
		
	}
	
	
	// Se usa para Administradores y Gestores
	public UserDTO UserToUserDTO(User usuarioEditar) {
		UserDTO usuarioEditarDTO = new UserDTO();
		usuarioEditarDTO.setNombre(usuarioEditar.getNombre());
		usuarioEditarDTO.setApellidos(usuarioEditar.getApellidos());
		usuarioEditarDTO.setEmail(usuarioEditar.getEmail());
		usuarioEditarDTO.setTelefono(usuarioEditar.getTelefono());
		usuarioEditarDTO.setUsername(usuarioEditar.getUsername());
		
		return usuarioEditarDTO;
		
	}

	
	// Se usa para Administradores y Gestores
	public User editar(UserDTO editarUserDTO, Long id) {
		User userEdit = repositoryUser.getOne(id);
		userEdit.setNombre(editarUserDTO.getNombre());
		userEdit.setApellidos(editarUserDTO.getApellidos());
		userEdit.setPassword(passwordEncoder.encode(editarUserDTO.getPassword()));
		userEdit.setEmail(editarUserDTO.getEmail());
		userEdit.setTelefono(editarUserDTO.getTelefono());
		userEdit.setUsername(editarUserDTO.getUsername());
		
		return repositoryUser.save(userEdit);
	
	}

	public NuevoInvestigadorDTO UserAndInvestigadorToDTO(User usuarioEditar, Investigador investigadorEditar) {
		NuevoInvestigadorDTO nuevoInvestigadorDTO = new NuevoInvestigadorDTO();

		nuevoInvestigadorDTO.setApellidos(usuarioEditar.getApellidos());
		nuevoInvestigadorDTO.setNombre(usuarioEditar.getNombre());
		nuevoInvestigadorDTO.setEmail(usuarioEditar.getEmail());
		nuevoInvestigadorDTO.setTelefono(usuarioEditar.getTelefono());
		nuevoInvestigadorDTO.setUsername(usuarioEditar.getUsername());
		nuevoInvestigadorDTO.setCentro(investigadorEditar.getCentro());
		nuevoInvestigadorDTO.setDepartamento(investigadorEditar.getDepartamento());
		
		return nuevoInvestigadorDTO;
		
	}

	

	

	




}

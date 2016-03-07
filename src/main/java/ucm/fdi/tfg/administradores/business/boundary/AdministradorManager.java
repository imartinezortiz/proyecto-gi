package ucm.fdi.tfg.administradores.business.boundary;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.administradores.business.repository.AdministradorRepository;
import ucm.fdi.tfg.investigadores.business.boundary.NuevoInvestigadorDTO;
import ucm.fdi.tfg.investigadores.business.boundary.Persona;
import ucm.fdi.tfg.investigadores.business.entity.Investigador;
import ucm.fdi.tfg.users.business.entity.UserRole;

@Service
@Transactional
public class AdministradorManager {
	
	AdministradorRepository administradorRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AdministradorManager(AdministradorRepository administradorRepository, PasswordEncoder passwordEncoder){
		this.administradorRepository = administradorRepository;
		this.passwordEncoder =  passwordEncoder;
		
	}
	
	public void save(NuevoInvestigadorDTO nuevoInvestigadorDTO){
		Persona p = new Persona(nuevoInvestigadorDTO.getNombre(), nuevoInvestigadorDTO.getApellidos(), nuevoInvestigadorDTO.getTelefono(), nuevoInvestigadorDTO.getEmail());
		p.setPassword(passwordEncoder.encode(nuevoInvestigadorDTO.getPassword()));
		p.setRol(new UserRole("ROLE_INVESTIGADOR"));		
		
		Investigador inv = new Investigador (p,nuevoInvestigadorDTO.getDepartamento(), nuevoInvestigadorDTO.getCentro());		
		
		this.administradorRepository.save(inv);
	}
	
	public List<Investigador> findAll(){
		return administradorRepository.findAll();
	}

}

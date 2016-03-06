package ucm.fdi.tfg.administradores.business.boundary;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.administradores.business.repository.AdministradorRepository;

@Service
@Transactional
public class AdministradorManager {
	
	AdministradorRepository administradorRepository;
	
	@Autowired
	public AdministradorManager(AdministradorRepository administradorRepository){
		this.administradorRepository = administradorRepository;
		
	}

}

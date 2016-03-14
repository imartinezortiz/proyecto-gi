package ucm.fdi.tfg.viajes.business.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.viajes.business.control.ViajesRepository;

@Service
@Transactional
public class ViajesManager {

	private ViajesRepository viajes;
	
	@Autowired
	public ViajesManager (ViajesRepository viajes){
		this.viajes=viajes;
	}
}

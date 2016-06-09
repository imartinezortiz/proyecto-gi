package ucm.fdi.tfg.unidadesGestoras.business.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.unidadesGestoras.business.control.UnidadGestoraRepository;
import ucm.fdi.tfg.unidadesGestoras.business.entity.UnidadGestora;

@Service
@Transactional
public class UnidadesGestorasManager {
	
	private UnidadGestoraRepository unidadGestoraRepo;
	
	@Autowired
	public UnidadesGestorasManager(UnidadGestoraRepository unidadGestoraRepo){
		this.unidadGestoraRepo = unidadGestoraRepo;
	}
	
	public List<UnidadGestora> findAll(){
		return unidadGestoraRepo.findAll();
	}

}

package ucm.fdi.tfg.centros.business.boundary;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucm.fdi.tfg.centros.business.control.CentroRepository;
import ucm.fdi.tfg.centros.business.entity.Centro;

@Service
@Transactional
public class CentrosManager {

	private CentroRepository repository;

	@Autowired 
	public CentrosManager(CentroRepository repository) {
		this.repository = repository;
	}
	
	public List<Centro> getAll() {
		return repository.findAll();
	}

	public Centro nuevoCentro(Centro nuevoCentro) {
		return	repository.save(nuevoCentro);	
	}

}

package ucm.fdi.tfg.gestores.business.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.gestores.business.repository.GestorRepository;
import ucm.fdi.tfg.investigadores.business.entity.Investigador;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;

@Service
@Transactional
public class GestorManager {
	
	GestorRepository gestorRepository;
	
	@Autowired
	public GestorManager(GestorRepository gestorRepository){
		this.gestorRepository = gestorRepository;
	}
	
	public void saveProyect(Proyecto proyecto){
		gestorRepository.saveProyect(proyecto);
		
	}
	


}

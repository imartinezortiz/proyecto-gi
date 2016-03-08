package ucm.fdi.tfg.gestores.business.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.administradores.business.repository.AdministradorRepository;
import ucm.fdi.tfg.gestores.business.repository.GestorRepository;
import ucm.fdi.tfg.investigadores.business.entity.Investigador;
import ucm.fdi.tfg.proyecto.business.boundary.NuevoProyectoDTO;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;

@Service
@Transactional
public class GestorManager {
	
	GestorRepository gestorRepository;
	AdministradorRepository administradorRepository;
	
	@Autowired
	public GestorManager(GestorRepository gestorRepository, AdministradorRepository administradorRepository){
		this.gestorRepository = gestorRepository;
		this.administradorRepository = administradorRepository;
	}
	
	public void saveProyect(NuevoProyectoDTO proyectoDTO){
		Proyecto proyecto = new Proyecto(); 
		Investigador inv = this.administradorRepository.getEm().getReference(Investigador.class, proyectoDTO.getInvestigadorId());
		proyecto.setTitulo(proyectoDTO.getTitulo());
		proyecto.setReferencia(proyectoDTO.getReferencia());
		proyecto.setNumContabilidad(proyectoDTO.getNumContabilidad());
		proyecto.setInvestigador(inv);
		gestorRepository.saveProyect(proyecto);
		
	}
	


}

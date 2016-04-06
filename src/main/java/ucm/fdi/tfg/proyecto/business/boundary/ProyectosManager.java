package ucm.fdi.tfg.proyecto.business.boundary;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.proyecto.business.control.ProyectosRepository;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.boundary.UserManager;
import ucm.fdi.tfg.users.business.entity.Investigador;

@Service
@Transactional
public class ProyectosManager {

	private ProyectosRepository proyectos;
	
	private UserManager users;
	
	@Autowired
	public ProyectosManager(ProyectosRepository gestorRepository, UserManager users){
		this.proyectos = gestorRepository;
		this.users = users;
	}
	
	public void nuevoProyecto(NuevoProyectoDTO proyectoDTO){
		Proyecto proyecto = fromDto(proyectoDTO, new Proyecto());
		proyectos.save(proyecto);
	
	}
	
	public void editar(NuevoProyectoDTO proyectoDTO) {
		Proyecto proyecto = fromDto(proyectoDTO, proyectos.getOne(proyectoDTO.getIdProyecto()));
		proyectos.save(proyecto);
	}
	
	private Proyecto fromDto(NuevoProyectoDTO proyectoDTO, Proyecto proyecto) {
		//Proyecto proyecto = new Proyecto(); 
		//Investigador inv = this.users.getInvestigador(proyectoDTO.getInvestigadorId());
		Investigador investigadorPrincipal = this.users.getInvestigadorFindOne(proyectoDTO.getInvestigadorId());
		
		Iterator<Long> it = proyectoDTO.getInvestigadoresID().iterator();
		while(it.hasNext()){
			proyecto.getInvestigadores().add(users.findInvestigador(it.next()));
		}
		//proyecto.setId(proyectoDTO.getIdProyecto());
		proyecto.setTitulo(proyectoDTO.getTitulo());
		proyecto.setReferencia(proyectoDTO.getReferencia());
		proyecto.setNumContabilidad(proyectoDTO.getNumContabilidad());
		proyecto.setInvestigadorPrincipal(investigadorPrincipal);
		
		return proyecto;
	}

	public ProyectosRepository getGestorRepository() {
		return proyectos;
	}

	public void setGestorRepository(ProyectosRepository gestorRepository) {
		this.proyectos = gestorRepository;
	}

	public Proyecto findProyecto(Long idProyecto) {
		return proyectos.findOne(idProyecto);
	}

	public Proyecto getProyecto(Long idProyecto) {
		return proyectos.getOne(idProyecto);
	}
	
	public List<Proyecto> findAll(){
		return proyectos.findAll();
	}
	
	public void deleteProyect(Long idProyecto){
		proyectos.delete(idProyecto);
	}

	
}

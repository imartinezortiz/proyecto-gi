package ucm.fdi.tfg.proyecto.business.boundary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.proyecto.business.control.ProyectosRepository;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.boundary.UserManager;
import ucm.fdi.tfg.users.business.entity.Investigador;
import ucm.fdi.tfg.users.business.entity.User;

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
		
		Investigador investigadorPrincipal = this.users.getInvestigadorFindOne(proyectoDTO.getInvestigadorId());
		
		//Para limpiar los investigadores anteriores, y que solo esten los q has añadido al editar y no aparezcan todos.
		proyecto.getInvestigadores().clear();
		
		Iterator<Long> it = proyectoDTO.getInvestigadoresID().iterator();
		while(it.hasNext()){
			proyecto.getInvestigadores().add(users.getInvestigador(it.next()));
		}

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

	public NuevoProyectoDTO proyectoAproyectoDTO(Proyecto proyecto){
		
		NuevoProyectoDTO proyectDTO = new NuevoProyectoDTO();
		proyectDTO.setInvestigadorId(proyecto.getInvestigadorPrincipal().getId());
		proyectDTO.setNumContabilidad((proyecto.getNumContabilidad()));
		proyectDTO.setReferencia((proyecto.getReferencia()));
		proyectDTO.setTitulo((proyecto.getTitulo()));
		proyectDTO.setIdProyecto(proyecto.getId());
				
		Iterator<Investigador> it = proyecto.getInvestigadores().iterator();
		while(it.hasNext()){
			proyectDTO.getInvestigadoresID().add(it.next().getId());
		}
		
		return proyectDTO;

	}
	
	/**
	 * Método que nos devuelve los Usuarios que participian en un proyecto.
	 * @param idProyecto
	 * @return List<User>
	 */
	public List<User> investigadoresProyecto(Long idProyecto){
	
		return proyectos.investigadoresProyecto(idProyecto);
	}
	
		
	
}

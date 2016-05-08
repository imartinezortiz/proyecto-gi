package ucm.fdi.tfg.inventarios.business.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.inventarios.business.control.InventariosRepository;
import ucm.fdi.tfg.inventarios.business.entity.Estado;
import ucm.fdi.tfg.inventarios.business.entity.Inventario;
import ucm.fdi.tfg.proyecto.business.boundary.ProyectosManager;

@Service
@Transactional
public class InventariosManager {
	private ProyectosManager proyectos;
	
	InventariosRepository inventarioRepository;
	
	@Autowired
	public InventariosManager(ProyectosManager proyectos, InventariosRepository inventarioRepository){
		this.proyectos = proyectos;
		this.inventarioRepository=inventarioRepository;
	}
	
	
	public void save(Inventario inventario) {	
		this.inventarioRepository.save(inventario);
	}


	public Inventario nuevoInventario(Long idProyecto, Inventario inventario) {
		inventario.setProyecto(proyectos.getProyecto(idProyecto));
	//	inventario.setNumContabilidad(proyectos.getProyecto(idProyecto).getNumContabilidad());
		return inventarioRepository.save(inventario);
	}
	
	public List<Inventario> inventariosPorProyecto(Long idProyecto){
		
		return inventarioRepository.inventariosPorProyecto(idProyecto);
	}


	public Inventario findOneInventario(Long id) {
		
		return this.inventarioRepository.findOne(id);
	}


	public Inventario editar(Inventario inventario, Long idInventario) {
		Inventario inventarioEdit = inventarioRepository.getOne(idInventario);	
		
		inventarioEdit.setAutorizacion(inventario.getAutorizacion());
		inventarioEdit.setCentro(inventario.getCentro());
		inventarioEdit.setDescripcion(inventario.getAutorizacion());
		inventarioEdit.setFecha(inventario.getFecha());
		inventarioEdit.setObservaciones(inventario.getObservaciones());
		inventarioEdit.setProyecto(inventario.getProyecto());
		inventarioEdit.setFase(inventario.getFase());
		
			
		return this.inventarioRepository.save(inventarioEdit);
		
	}


	public Inventario procesar(Long idInventario) {
		Inventario inventarioEdit = inventarioRepository.getOne(idInventario);	
				
		inventarioEdit.setFase(Estado.PROCESANDO);		
			
		return this.inventarioRepository.save(inventarioEdit);
		
	}

}

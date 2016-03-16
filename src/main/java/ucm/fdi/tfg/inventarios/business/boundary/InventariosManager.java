package ucm.fdi.tfg.inventarios.business.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.inventarios.business.control.InventariosRepository;
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


	public void nuevoInventario(Long idProyecto, Inventario inventario) {
		inventario.setProyecto(proyectos.getProyecto(idProyecto));
		inventarioRepository.save(inventario);
	}

}

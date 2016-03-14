package ucm.fdi.tfg.inventarios.business.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.inventarios.business.control.InventariosRepository;
import ucm.fdi.tfg.inventarios.business.entity.Inventario;

@Service
@Transactional
public class InventariosManager {
	
	InventariosRepository inventarioRepository;
	
	@Autowired
	public InventariosManager(InventariosRepository inventarioRepository){
		this.inventarioRepository=inventarioRepository;
	}
	
	
	public void save(Inventario inventario) {	
		this.inventarioRepository.save(inventario);
	}

}

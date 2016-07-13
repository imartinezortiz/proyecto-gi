package ucm.fdi.tfg.viajes.business.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.viajes.business.control.ComisionSerivicioRepository;
import ucm.fdi.tfg.viajes.business.control.PermisoAusenciaRepository;
import ucm.fdi.tfg.viajes.business.entity.ComisionServicio;
import ucm.fdi.tfg.viajes.business.entity.EstadoPermisoAusenciaEnum;
import ucm.fdi.tfg.viajes.business.entity.JustificacionViaje;
import ucm.fdi.tfg.viajes.business.entity.PermisoAusencia;

@Service
@Transactional
public class PermisoAusenciaManager {
	
	private PermisoAusenciaRepository permisoAusenciaRepo;
	
	
	@Autowired
	public PermisoAusenciaManager(PermisoAusenciaRepository permisoAusenciaRepo){
		this.permisoAusenciaRepo =  permisoAusenciaRepo;
	}
	
	public PermisoAusencia add(PermisoAusencia peticionAusencia){
		return permisoAusenciaRepo.save(peticionAusencia);
	}
	
	public List<PermisoAusencia> findAll(){
		return permisoAusenciaRepo.findAll();
	}

	public List<PermisoAusencia> findByEstado(EstadoPermisoAusenciaEnum estado) {
		
		return permisoAusenciaRepo.findByEstado(estado);
	}

	public PermisoAusencia findOnePermiso(Long idPermiso) {
		
		return permisoAusenciaRepo.findOne(idPermiso);
	}
	
	public List<PermisoAusencia> permisosAusenciaPorProyecto(Long idProyecto) {
		return permisoAusenciaRepo.permisosAusenciaPorProyecto(idProyecto);
	}	


}

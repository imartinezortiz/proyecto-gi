package ucm.fdi.tfg.viajes.business.boundary;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.viajes.business.control.ComisionSerivicioRepository;
import ucm.fdi.tfg.viajes.business.control.PermisoAusenciaRepository;
import ucm.fdi.tfg.viajes.business.entity.ComisionServicio;
import ucm.fdi.tfg.viajes.business.entity.EstadoComisionServicioEnum;
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
		Map<String, LocalDate> vbs = new HashMap<String, LocalDate>();;		
		vbs.put(EstadoPermisoAusenciaEnum.EDICION.toString(), LocalDate.now());		
		peticionAusencia.setVbs(vbs);
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

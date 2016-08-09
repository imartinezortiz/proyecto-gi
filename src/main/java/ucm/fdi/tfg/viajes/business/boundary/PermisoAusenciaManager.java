package ucm.fdi.tfg.viajes.business.boundary;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.users.business.entity.UserRole;
import ucm.fdi.tfg.viajes.business.control.PermisoAusenciaRepository;
import ucm.fdi.tfg.viajes.business.entity.EstadoComisionServicioEnum;
import ucm.fdi.tfg.viajes.business.entity.EstadoPermisoAusenciaEnum;
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
	
	public PermisoAusencia cambiarEstado(Long idComision, User user) {
		PermisoAusencia permiso = permisoAusenciaRepo.getOne(idComision);
		
		Map<String, LocalDate> vbs = permiso.getVbs();
		Collection<UserRole> roles = user.getRoles();
		
		for (UserRole rol : roles){
			if (rol.getRole().equals("ROLE_INVESTIGADOR")){
					permiso.setEstado(EstadoPermisoAusenciaEnum.PENDIENTE_FIRMA_DPTO);
					vbs.put(EstadoPermisoAusenciaEnum.PENDIENTE_FIRMA_DPTO.toString(), LocalDate.now());
					break;
				}
			else if (rol.getRole().equals("ROLE_DIR_DEPARTAMENTO")){
				permiso.setEstado(EstadoPermisoAusenciaEnum.PENDIENTE_FIRMA_DECANO);
				vbs.put(EstadoPermisoAusenciaEnum.PENDIENTE_FIRMA_DECANO.toString(), LocalDate.now());
				break;
			}
			else if (rol.getRole().equals("ROLE_DECANO")){
				permiso.setEstado(EstadoPermisoAusenciaEnum.ACEPTADO);
					vbs.put(EstadoPermisoAusenciaEnum.ACEPTADO.toString(), LocalDate.now());
			    break;
			}
		
		}
		
		
		
		return permisoAusenciaRepo.save(permiso);
		
		
	}
	
	public PermisoAusencia rechazarPermiso(Long idComision) {
		
		PermisoAusencia permiso = permisoAusenciaRepo.getOne(idComision);		
		
		Map<String, LocalDate> vbs = permiso.getVbs();
		
		vbs.put(EstadoPermisoAusenciaEnum.RECHAZADO.toString(), LocalDate.now());
		permiso.setEstado(EstadoPermisoAusenciaEnum.RECHAZADO);
		
		return permisoAusenciaRepo.save(permiso);
		
	}
	


}

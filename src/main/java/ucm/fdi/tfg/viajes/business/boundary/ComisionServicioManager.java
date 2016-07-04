package ucm.fdi.tfg.viajes.business.boundary;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.users.business.entity.UserRole;
import ucm.fdi.tfg.viajes.business.control.ComisionSerivicioRepository;
import ucm.fdi.tfg.viajes.business.entity.ComisionServicio;
import ucm.fdi.tfg.viajes.business.entity.EstadoComisionServicioEnum;

@Service
@Transactional
public class ComisionServicioManager {

	private ComisionSerivicioRepository comisionServicioRepo;
	
	
	@Autowired
	public ComisionServicioManager(ComisionSerivicioRepository comisionServicioRepo){
		this.comisionServicioRepo =  comisionServicioRepo;
	}
	
	public ComisionServicio add(ComisionServicio comisionServicio){
		return comisionServicioRepo.save(comisionServicio);
	}
	
	public List<ComisionServicio> findAll(){
		return comisionServicioRepo.findAll();
	}
	
	public List<ComisionServicio> findByEstado(EstadoComisionServicioEnum estado){
		return comisionServicioRepo.findByestado(estado);
	}

	public ComisionServicio cambiarEstado(Long idComision, User user) {
		ComisionServicio comision = comisionServicioRepo.getOne(idComision);
		
		Map<String, LocalDate> vbs = comision.getVbs();
		Collection<UserRole> roles = user.getRoles();
		
		for (UserRole rol : roles){
			if (rol.getRole().equals("ROLE_DECANO")){
					comision.setEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_CENTRO);
					vbs.put(EstadoComisionServicioEnum.PENDIENTE_FIRMA_CENTRO.toString(), LocalDate.now());
			    break;
			}
			else if (rol.getRole().equals("ROLE_UNIDAD_GESTORA")){
				comision.setEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_CENTRO);
				vbs.put(EstadoComisionServicioEnum.PENDIENTE_FIRMA_CENTRO.toString(), LocalDate.now());
				break;
			}
			else if (rol.getRole().equals("ROLE_DEPARTAMENTO")){
				comision.setEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_CENTRO);
				vbs.put(EstadoComisionServicioEnum.PENDIENTE_FIRMA_CENTRO.toString(), LocalDate.now());
				break;
			}
			else if (rol.getRole().equals("ROLE_INVESTIGADOR")){
				comision.setEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_CENTRO);
				vbs.put(EstadoComisionServicioEnum.PENDIENTE_FIRMA_CENTRO.toString(), LocalDate.now());
				break;
			}
		}
		
		
		
		return comisionServicioRepo.save(comision);
		
		
	}
}

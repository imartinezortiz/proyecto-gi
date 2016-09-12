/**
 * This file is part of proyecto-gi.
 *
 * proyecto-gi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * proyecto-gi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with proyecto-gi.  If not, see <http://www.gnu.org/licenses/>.
 */
package ucm.fdi.tfg.viajes.business.boundary;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.pagos.business.entity.Pago;
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
	
	public PermisoAusencia editar(PermisoAusencia permiso, Long idPermiso) {
		PermisoAusencia permisoEditar = permisoAusenciaRepo.getOne(idPermiso);
		
		permisoEditar.setAfectaDodencia(permiso.isAfectaDodencia());
		permisoEditar.setDesde(permiso.getDesde());
		permisoEditar.setEstado(permiso.getEstado());
		permisoEditar.setGeneraGasto(permiso.isGeneraGasto());
		permisoEditar.setHasta(permiso.getHasta());
		permisoEditar.setInteresado(permiso.getInteresado());
		permisoEditar.setLugar(permiso.getLugar());
		permisoEditar.setMotivo(permiso.getMotivo());
		permisoEditar.setOtrasActividades(permiso.getOtrasActividades());
		permisoEditar.setProyecto(permiso.getProyecto());
		permisoEditar.setSustituciones(permiso.getSustituciones());
		permisoEditar.setVbs(permiso.getVbs());
		
		return permisoAusenciaRepo.save(permisoEditar);
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

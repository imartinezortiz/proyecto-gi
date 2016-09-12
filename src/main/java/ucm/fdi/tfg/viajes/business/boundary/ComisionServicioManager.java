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
import ucm.fdi.tfg.viajes.business.entity.EstadoPermisoAusenciaEnum;
import ucm.fdi.tfg.viajes.business.entity.PermisoAusencia;

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
			if (rol.getRole().equals("ROLE_INVESTIGADOR")){
				comision.setEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_DPTO);
				vbs.put(EstadoComisionServicioEnum.PENDIENTE_FIRMA_DPTO.toString(), LocalDate.now());
				break;
			}
			else if (rol.getRole().equals("ROLE_DIR_DEPARTAMENTO")){
				comision.setEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_RRHH_CENTRO);
				vbs.put(EstadoComisionServicioEnum.PENDIENTE_FIRMA_RRHH_CENTRO.toString(), LocalDate.now());
				break;
			}
			else if (rol.getRole().equals("ROLE_RRHH_CENTRO")){
				comision.setEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_UNIDAD_GESTORA);
				vbs.put(EstadoComisionServicioEnum.PENDIENTE_FIRMA_UNIDAD_GESTORA.toString(), LocalDate.now());
				break;
			}
			else if (rol.getRole().equals("ROLE_UNIDAD_GESTORA")){
				comision.setEstado(EstadoComisionServicioEnum.PENDIENTE_FIRMA_DECANO);
				vbs.put(EstadoComisionServicioEnum.PENDIENTE_FIRMA_DECANO.toString(), LocalDate.now());
				break;
			}
			else if (rol.getRole().equals("ROLE_DECANO")){
					comision.setEstado(EstadoComisionServicioEnum.ACEPTADO);
					vbs.put(EstadoComisionServicioEnum.ACEPTADO.toString(), LocalDate.now());
			    break;
			}
		}
		
		
		
		return comisionServicioRepo.save(comision);
		
		
	}

	public List<ComisionServicio> comisionesServicioPorProyecto(Long idProyecto) {		
		return comisionServicioRepo.comisionesServicioPorProyecto(idProyecto);
	}

	public ComisionServicio findOneComision(Long idComision) {
		
		return this.comisionServicioRepo.findOne(idComision);
	}

	public ComisionServicio rechazarComision(Long idComision) {
		
		ComisionServicio  comision = comisionServicioRepo.getOne(idComision);
		
		Map<String, LocalDate> vbs = comision.getVbs();
		
		vbs.put(EstadoComisionServicioEnum.RECHAZADO.toString(), LocalDate.now());
		comision.setEstado(EstadoComisionServicioEnum.RECHAZADO);
		
		return comisionServicioRepo.save(comision);
		
	}

	public ComisionServicio editar(ComisionServicio comision, Long idComision) {
		
		ComisionServicio  comisionEditar = comisionServicioRepo.getOne(idComision);
		
		comisionEditar.setEstado(comision.getEstado());
		comisionEditar.setFin(comision.getFin());
		comisionEditar.setGastosInscripcion(comision.isGastosInscripcion());
		comisionEditar.setInteresado(comision.getInteresado());
		comisionEditar.setItinerario(comision.getItinerario());
		comisionEditar.setObjetoDesplazamiento(comision.getObjetoDesplazamiento());
		comisionEditar.setTransportePrincipal(comision.getTransportePrincipal());
		comisionEditar.setVbs(comision.getVbs());
		
		
		return comisionServicioRepo.save(comisionEditar);
		
	}
}

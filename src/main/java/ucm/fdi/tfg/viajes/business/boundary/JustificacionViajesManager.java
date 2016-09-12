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

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.viajes.business.control.JustificacionViajeRepository;
import ucm.fdi.tfg.viajes.business.entity.Dieta;
import ucm.fdi.tfg.viajes.business.entity.EstadoJustificacionEnum;
import ucm.fdi.tfg.viajes.business.entity.JustificacionViaje;

@Service
@Transactional
public class JustificacionViajesManager {

	private JustificacionViajeRepository viajes;
	
	private BigDecimal costePorKm;

	@Autowired
	public JustificacionViajesManager (JustificacionViajeRepository viajes){
		this.costePorKm = new BigDecimal(0.19);
		this.viajes=viajes;
	}
	
	public JustificacionViaje save(JustificacionViaje viaje){	
		return viajes.save(viaje);
	}
	
	public List<Dieta> dameDietas(){
		return viajes.DameDietas();
	}

	public BigDecimal getCostePorKm() {
		return costePorKm;
	}

	public void setCostePorKm(BigDecimal costePorKm) {
		this.costePorKm = costePorKm;
	}

	public List<JustificacionViaje> justificacionesViajesPorProyecto(Long idProyecto) {
		return viajes.viajesPorProyecto(idProyecto);
	}	

	public JustificacionViaje findOneViaje(Long idViaje) {
		// TODO Auto-generated method stub
		return viajes.findOne(idViaje);
	}

	public void editar(JustificacionViaje viaje, Long idViaje) {
		JustificacionViaje viajeEditar = this.viajes.getOne(idViaje);
		viajeEditar.setDietaID(viaje.getDietaID());
		viajeEditar.setFecha(viaje.getFecha());
		viajeEditar.setFechaFin(viaje.getFechaFin());
		viajeEditar.setFechaInicio(viaje.getFechaInicio());
		viajeEditar.setGastos(viaje.getGastos());
		viajeEditar.setImporteDieta(viaje.getImporteDieta());
		viajeEditar.setInvestigador(viaje.getInvestigador());
		viajeEditar.setInvitado(viaje.getInvitado());
		viajeEditar.setItinerario(viaje.getItinerario());
		viajeEditar.setMiembroProyecto(viaje.isMiembroProyecto());
		viajeEditar.setNumDietas(viaje.getNumDietas());
		viajeEditar.setNumOrden(viaje.getNumOrden());
		viajeEditar.setObjetoDesplazamiento(viaje.getObjetoDesplazamiento());
		viajeEditar.setObservaciones(viaje.getObservaciones());
		viajeEditar.setPagarA(viaje.getPagarA());
		viajeEditar.setProyecto(viaje.getProyecto());
		viajeEditar.setFase(viaje.getFase());
		viajes.save(viajeEditar);
	}
	
	public JustificacionViaje procesando(Long idViaje) {
		JustificacionViaje viajeEditar = viajes.getOne(idViaje);	
				
		viajeEditar.setFase(EstadoJustificacionEnum.PROCESANDO);		
			
		return this.viajes.save(viajeEditar);
		
	}
	
	public JustificacionViaje procesar(Long idViaje) {
		JustificacionViaje viajeEditar = viajes.getOne(idViaje);	
				
		viajeEditar.setFase(EstadoJustificacionEnum.PROCESADO);		
			
		return this.viajes.save(viajeEditar);
		
	}
	
	public List<JustificacionViaje> viajesProcesando(){
		
		return viajes.viajesProcesando();
	}
	
	
}

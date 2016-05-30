package ucm.fdi.tfg.viajes.business.boundary;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ucm.fdi.tfg.investigacion.business.entity.Estado;
import ucm.fdi.tfg.viajes.business.control.ViajesRepository;
import ucm.fdi.tfg.viajes.business.entity.Dieta;
import ucm.fdi.tfg.viajes.business.entity.Viaje;

@Service
@Transactional
public class ViajesManager {

	private ViajesRepository viajes;
	
	private BigDecimal costePorKm;

	@Autowired
	public ViajesManager (ViajesRepository viajes){
		this.costePorKm = new BigDecimal(0.19);
		this.viajes=viajes;
	}
	
	public Viaje save(Viaje viaje){	
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

	public List<Viaje> viajesPorProyecto(Long idProyecto) {
		return viajes.viajesPorProyecto(idProyecto);
	}

	public Viaje findOneViaje(Long idViaje) {
		// TODO Auto-generated method stub
		return viajes.findOne(idViaje);
	}

	public void editar(Viaje viaje, Long idViaje) {
		Viaje viajeEditar = this.viajes.getOne(idViaje);
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
	
	public Viaje procesando(Long idViaje) {
		Viaje viajeEditar = viajes.getOne(idViaje);	
				
		viajeEditar.setFase(Estado.PROCESANDO);		
			
		return this.viajes.save(viajeEditar);
		
	}
	
	public Viaje procesar(Long idViaje) {
		Viaje viajeEditar = viajes.getOne(idViaje);	
				
		viajeEditar.setFase(Estado.PROCESADO);		
			
		return this.viajes.save(viajeEditar);
		
	}
	
	public List<Viaje> viajesProcesando(){
		
		return viajes.viajesProcesando();
	}
	
	
}

package ucm.fdi.tfg.viajes.business.boundary;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public void save(Viaje viaje){	
		 viajes.save(viaje);
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
	
	
}

package ucm.fdi.tfg.viajes.business.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.viajes.business.control.ComisionSerivicioRepository;
import ucm.fdi.tfg.viajes.business.entity.ComisionServicio;

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
}

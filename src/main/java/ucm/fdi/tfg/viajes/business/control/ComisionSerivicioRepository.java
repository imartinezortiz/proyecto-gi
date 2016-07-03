package ucm.fdi.tfg.viajes.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.viajes.business.entity.ComisionServicio;
import ucm.fdi.tfg.viajes.business.entity.EstadoComisionServicioEnum;

@Repository
public interface ComisionSerivicioRepository extends JpaRepository<ComisionServicio, Long>{
	
	List<ComisionServicio> findByestado(EstadoComisionServicioEnum estado);


}

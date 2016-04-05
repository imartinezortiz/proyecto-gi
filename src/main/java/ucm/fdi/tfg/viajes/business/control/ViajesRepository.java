package ucm.fdi.tfg.viajes.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ucm.fdi.tfg.viajes.business.entity.Dieta;
import ucm.fdi.tfg.viajes.business.entity.Viaje;


public interface ViajesRepository extends JpaRepository<Viaje, Long>{

	@Query("SELECT D FROM Dieta D") 
	public List<Dieta> DameDietas();
}

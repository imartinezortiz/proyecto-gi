package ucm.fdi.tfg.viajes.business.control;

import org.springframework.data.jpa.repository.JpaRepository;

import ucm.fdi.tfg.viajes.business.entity.Viaje;

public interface ViajesRepository extends JpaRepository<Viaje, Long>{

}

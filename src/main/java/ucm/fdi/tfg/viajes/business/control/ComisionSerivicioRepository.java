package ucm.fdi.tfg.viajes.business.control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.viajes.business.entity.ComisionServicio;

@Repository
public interface ComisionSerivicioRepository extends JpaRepository<ComisionServicio, Long>{

}

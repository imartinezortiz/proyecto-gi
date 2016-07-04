package ucm.fdi.tfg.viajes.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.viajes.business.entity.EstadoPermisoAusenciaEnum;
import ucm.fdi.tfg.viajes.business.entity.PermisoAusencia;

@Repository
public interface PermisoAusenciaRepository extends JpaRepository<PermisoAusencia, Long>{

	List<PermisoAusencia> findByEstado(EstadoPermisoAusenciaEnum estado);

}

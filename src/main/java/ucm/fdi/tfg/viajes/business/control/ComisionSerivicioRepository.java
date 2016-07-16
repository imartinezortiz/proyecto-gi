package ucm.fdi.tfg.viajes.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.viajes.business.entity.ComisionServicio;
import ucm.fdi.tfg.viajes.business.entity.EstadoComisionServicioEnum;
import ucm.fdi.tfg.viajes.business.entity.PermisoAusencia;

@Repository
public interface ComisionSerivicioRepository extends JpaRepository<ComisionServicio, Long>{
	
	List<ComisionServicio> findByestado(EstadoComisionServicioEnum estado);
	
	@Query(" SELECT I FROM ComisionServicio I WHERE I.proyecto.id = :idProyecto")
	public List<ComisionServicio> comisionesServicioPorProyecto(@Param(value = "idProyecto") Long idProyecto);


}

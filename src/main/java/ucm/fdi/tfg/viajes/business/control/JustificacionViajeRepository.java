package ucm.fdi.tfg.viajes.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ucm.fdi.tfg.viajes.business.entity.Dieta;
import ucm.fdi.tfg.viajes.business.entity.JustificacionViaje;


public interface JustificacionViajeRepository extends JpaRepository<JustificacionViaje, Long>{

	@Query("SELECT D FROM Dieta D") 
	public List<Dieta> DameDietas();
	
	//@Query(" SELECT I FROM Viaje I LEFT JOIN FETCH I.gastos G JOIN FETCH I.proyecto P WHERE I.proyecto.id = :idProyecto")
	@Query(" SELECT I FROM JustificacionViaje I WHERE I.proyecto.id = :idProyecto")
	public List<JustificacionViaje> viajesPorProyecto(@Param(value = "idProyecto") Long idProyecto);
	
	@Query(" SELECT I FROM JustificacionViaje I WHERE I.fase = 'PROCESANDO' ")
	public List<JustificacionViaje> viajesProcesando();
}

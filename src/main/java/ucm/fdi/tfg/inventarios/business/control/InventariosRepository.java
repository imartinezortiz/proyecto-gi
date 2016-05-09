package ucm.fdi.tfg.inventarios.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ucm.fdi.tfg.inventarios.business.entity.Inventario;


public interface InventariosRepository extends JpaRepository<Inventario, Long>{
	
	@Query(" SELECT I FROM Inventario I WHERE I.proyecto.id = :idProyecto")
	public List<Inventario> inventariosPorProyecto(@Param(value = "idProyecto") Long idProyecto);
	
	@Query(" SELECT I FROM Inventario I WHERE I.fase = 'PROCESANDO' ")
	public List<Inventario> inventariosProcesando();

}

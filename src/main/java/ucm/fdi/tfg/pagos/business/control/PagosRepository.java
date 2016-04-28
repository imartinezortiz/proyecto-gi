package ucm.fdi.tfg.pagos.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import ucm.fdi.tfg.pagos.business.entity.Pago;

@Repository
public interface PagosRepository extends JpaRepository<Pago, Long> {	
		
	@Query(" SELECT I FROM Pago I JOIN FETCH I.gastos G JOIN FETCH I.proyecto P WHERE I.proyecto.id = :idProyecto")
	public List<Pago> pagosPorProyecto(@Param(value = "idProyecto") Long idProyecto);


	
	
}

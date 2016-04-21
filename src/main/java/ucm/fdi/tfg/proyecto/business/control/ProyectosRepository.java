package ucm.fdi.tfg.proyecto.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.entity.User;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyecto, Long> {
	
	@Query("SELECT U FROM User U WHERE U.id IN (SELECT I.id FROM Proyecto P, Investigador I WHERE P.id = :idProyecto AND I MEMBER OF P.investigadores)") 
	public List<User> investigadoresProyecto(@Param(value = "idProyecto") Long idProyecto);

}

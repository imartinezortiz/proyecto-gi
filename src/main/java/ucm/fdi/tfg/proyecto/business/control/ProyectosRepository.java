package ucm.fdi.tfg.proyecto.business.control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.proyecto.business.entity.Proyecto;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyecto, Long> {
	

}

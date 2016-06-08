package ucm.fdi.tfg.centros.business.control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.centros.business.entity.Centro;

@Repository
public interface CentroRepository extends JpaRepository<Centro, Long> {

}

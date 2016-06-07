package ucm.fdi.tfg.unidadesGestoras.business.control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.unidadesGestoras.business.entity.UnidadGestora;

@Repository
public interface UnidadGestoraRepository extends JpaRepository<UnidadGestora, Long> {

}

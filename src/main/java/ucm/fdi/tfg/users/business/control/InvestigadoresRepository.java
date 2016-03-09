package ucm.fdi.tfg.users.business.control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.users.business.entity.Investigador;

@Repository
public interface InvestigadoresRepository extends JpaRepository<Investigador, Long> {

}

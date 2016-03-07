package ucm.fdi.tfg.administradores.business.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.investigadores.business.boundary.NuevoInvestigadorDTO;
import ucm.fdi.tfg.investigadores.business.entity.Investigador;

@Repository
public class AdministradorRepository {
	
	@PersistenceContext
	private EntityManager em; 
	
	public void save (Investigador investigador){
		em.merge(investigador);
	}

}

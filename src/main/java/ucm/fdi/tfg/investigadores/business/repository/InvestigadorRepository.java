package ucm.fdi.tfg.investigadores.business.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.investigadores.business.entity.Investigador;


@Repository
public class InvestigadorRepository {

	@PersistenceContext
	private EntityManager em; 
	
	public void save(Investigador investigador){
		em.merge(investigador);
	}
	
	
}

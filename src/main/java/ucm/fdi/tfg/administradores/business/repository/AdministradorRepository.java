package ucm.fdi.tfg.administradores.business.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.investigadores.business.boundary.NuevoInvestigadorDTO;
import ucm.fdi.tfg.investigadores.business.entity.Investigador;

@Repository
public class AdministradorRepository {
	
	@PersistenceContext
	private EntityManager em; 
	
	

	List<Investigador> listaInvestigadores;
	
	public void save (Investigador investigador){
		em.merge(investigador);
	}
	
	public List<Investigador> findAll(){
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Investigador> q = cb.createQuery(Investigador.class);
	
		
		q.from(Investigador.class);
		listaInvestigadores = em.createQuery(q).getResultList();  

		return this.listaInvestigadores;
		
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}

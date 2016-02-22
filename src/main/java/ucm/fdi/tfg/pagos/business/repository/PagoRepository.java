package ucm.fdi.tfg.pagos.business.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.pagos.business.entity.Pago;
import ucm.fdi.tfg.users.business.entity.User;

@Repository
public class PagoRepository {
	
	@PersistenceContext
	private EntityManager em; 
	
	public void save(Pago pago){
		em.merge(pago);
	}
	
	
	public Pago findByNumOrden (int numOrden){
		Query query = em.createQuery("SELECT p FROM Pago p where p.numOrden= :numOrden");
		query.setParameter("numOrden",numOrden);
		Pago p;
		//Como solo quiero un objeto, uso getsingleresult
		try{
			p = (Pago) query.getSingleResult();
		}catch(NoResultException | NonUniqueResultException e ){
			  return null;
		}
		  
		return p;
	}
	
	
}

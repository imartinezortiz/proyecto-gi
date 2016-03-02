package ucm.fdi.tfg.pagos.business.repository;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.investigadores.business.entity.Investigador;
import ucm.fdi.tfg.pagos.business.entity.Gasto;
import ucm.fdi.tfg.pagos.business.entity.Pago;
import ucm.fdi.tfg.users.business.entity.User;

@Repository
public class PagoRepository {
	
	@PersistenceContext
	private EntityManager em; 
	
	
	public void save(Pago pago){
	
		//Preguntar a Ivan que hacer con el metodo getReference, 
		//ya que ese método te genera un obj, mediante un id..y aqui el problema esq no tenemos id 
		
		em.merge(pago);
	}
	
	
	public Pago findByNumOrden (int numOrden){
		// from ...(nombreClase)
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

	/*

	public Collection<Gasto> getAllGastos() {
		
		
	}

	*/
	
	
}

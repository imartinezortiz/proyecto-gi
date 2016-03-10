package ucm.fdi.tfg.pagos.business.control;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.pagos.business.entity.Pago;

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

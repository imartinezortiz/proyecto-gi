package ucm.fdi.tfg.pagos.business.control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.pagos.business.entity.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {	
		
		
	/*
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
	
	*/


	
	
}

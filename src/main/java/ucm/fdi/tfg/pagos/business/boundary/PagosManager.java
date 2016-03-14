package ucm.fdi.tfg.pagos.business.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.pagos.business.control.PagoRepository;
import ucm.fdi.tfg.pagos.business.entity.Pago;


@Service
@Transactional
public class PagoManager {
	
	PagoRepository repositoryPago;
	
	 @Autowired
	 public PagoManager (PagoRepository pagos){
		 this.repositoryPago = pagos;
		 
	 }
	 
	 public void save(Pago pago){	
		repositoryPago.save(pago);
	}
	 
	 /*
	public Collection<Gasto> getAllGastos(){
		repositoryPago.getAllGastos();
	}
	*/
}

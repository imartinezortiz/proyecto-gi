package ucm.fdi.tfg.pagos.business.boundary;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ucm.fdi.tfg.pagos.business.entity.Gasto;
import ucm.fdi.tfg.pagos.business.entity.Pago;
import ucm.fdi.tfg.pagos.business.repository.PagoRepository;
import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.users.business.entity.UserRole;

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

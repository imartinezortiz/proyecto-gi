package ucm.fdi.tfg.pagos.business.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.pagos.business.control.PagosRepository;
import ucm.fdi.tfg.pagos.business.entity.Pago;


@Service
@Transactional
public class PagosManager {
	
	PagosRepository repositoryPago;
	
	 @Autowired
	 public PagosManager (PagosRepository pagos){
		 this.repositoryPago = pagos;
		 
	 }
	 
	 public void save(Pago pago){	
		repositoryPago.save(pago);
	}

	public List<Pago> pagosPorProyecto(Long idProyecto) {
		 return repositoryPago.pagosPorProyecto(idProyecto);
	}

	public Pago findOnePago(Long idPago) {
		return repositoryPago.findOne(idPago);
	}

	public Pago editar(Pago pago, Long idPago) {
		Pago pagoEditar = repositoryPago.getOne(idPago);
		pagoEditar.setBic(pago.getBic());
		pagoEditar.setFecha(pago.getFecha());
		pagoEditar.setGastos(pago.getGastos());
		pagoEditar.setIban(pago.getIban());
		pagoEditar.setMemoria(pago.getMemoria());
		pagoEditar.setNumOrden(pago.getNumOrden());
		pagoEditar.setPagador(pago.getPagador());
		pagoEditar.setProyecto(pago.getProyecto());
		pagoEditar.setRelacion(pago.getRelacion());
		return repositoryPago.save(pagoEditar);
	}
	 
	 /*
	public Collection<Gasto> getAllGastos(){
		repositoryPago.getAllGastos();
	}
	*/
}

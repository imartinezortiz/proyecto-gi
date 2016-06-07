package ucm.fdi.tfg.pagos.business.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.inventarios.business.entity.Inventario;
import ucm.fdi.tfg.pagos.business.control.PagosRepository;
import ucm.fdi.tfg.pagos.business.entity.Pago;
import ucm.fdi.tfg.viajes.business.entity.EstadoJustificacionViajeEnum;


@Service
@Transactional
public class PagosManager {
	
	PagosRepository repositoryPago;
	
	 @Autowired
	 public PagosManager (PagosRepository pagos){
		 this.repositoryPago = pagos;
		 
	 }
	 
	 public Pago save(Pago pago){	
		return repositoryPago.save(pago);
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
		pagoEditar.setFase(pago.getFase());
		
		return repositoryPago.save(pagoEditar);
	}
	
	public Pago procesando(Long idPago) {
		Pago pagoEdit = repositoryPago.getOne(idPago);	
				
		pagoEdit.setFase(EstadoJustificacionViajeEnum.PROCESANDO);		
			
		return this.repositoryPago.save(pagoEdit);
		
	}
	
	public Pago procesar(Long idPago) {
		Pago pagoEdit = repositoryPago.getOne(idPago);	
				
		pagoEdit.setFase(EstadoJustificacionViajeEnum.PROCESADO);		
			
		return this.repositoryPago.save(pagoEdit);
		
	}
	
	public List<Pago> pagosProcesando(){
		
		return repositoryPago.pagosProcesando();
	}
	 
	 /*
	public Collection<Gasto> getAllGastos(){
		repositoryPago.getAllGastos();
	}
	*/
}

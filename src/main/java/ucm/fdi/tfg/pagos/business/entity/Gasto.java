package ucm.fdi.tfg.pagos.business.entity;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable // es atributo de un Entity 
@Table(name="PAGO_GASTOS")
public class Gasto {
	
	@Basic
	private String numFactura;
	
	@Basic
	private String provedor;
	
	@Basic
	private BigDecimal importe;
	
	public Gasto(String numFactura,String provedor, BigDecimal importe ){
		this.numFactura = numFactura;
		this.provedor = provedor;
		this.importe = importe;
	}	
	
	public Gasto(){
		
	}
}

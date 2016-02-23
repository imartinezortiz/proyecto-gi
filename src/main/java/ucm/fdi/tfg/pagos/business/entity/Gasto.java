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
	private String proveedor;
	
	@Basic
	private BigDecimal importe;
	
	public Gasto(String numFactura,String provedor, BigDecimal importe ){
		this.numFactura = numFactura;
		this.proveedor = provedor;
		this.importe = importe;
	}	
	
	public Gasto(){		
	}
}

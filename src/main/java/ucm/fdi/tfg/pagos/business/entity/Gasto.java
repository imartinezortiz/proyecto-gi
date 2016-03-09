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
	
	public Gasto(){
		this(null, null, BigDecimal.ZERO);
	}
	
	public Gasto(String numFactura,String provedor, BigDecimal importe ){
		this.numFactura = numFactura;
		this.proveedor = provedor;
		this.importe = importe;
	}	


	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
}

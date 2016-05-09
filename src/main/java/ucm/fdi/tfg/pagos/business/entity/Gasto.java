package ucm.fdi.tfg.pagos.business.entity;

import java.math.BigDecimal;


import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;


@Embeddable // es atributo de un Entity 
@Table(name="PAGO_GASTOS")
public class Gasto {
	
	@NotEmpty(message="Campo vacio")
	private String numFactura;
	
	@NotEmpty(message="Campo vacio")
	private String proveedor;
	
	
	
	//@NotBlank(message = "Campo vacio")
	@Digits(integer=6, fraction=2,  message="Máximo dos decimales.")
	@Min(value = (long) 0.01, message = "Valor mayor que 0.")
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

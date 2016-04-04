package ucm.fdi.tfg.viajes.business.entity;

import java.math.BigDecimal;

import javax.persistence.Embeddable;


@Embeddable
public class GastoViaje {
	
	// Transporte, alojamiento, ..
	private String tipo;
	
	private String descripcion;
	
	
	private BigDecimal importe;
	
	public GastoViaje(){
		this(null, null, BigDecimal.ZERO);
	}
	
	public GastoViaje(String tipo,String descripcion, BigDecimal importe ){
		this.tipo= tipo;
		this.descripcion = descripcion;
		this.importe = importe;		
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

}

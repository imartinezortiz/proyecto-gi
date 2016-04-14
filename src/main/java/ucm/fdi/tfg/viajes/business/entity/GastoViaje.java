package ucm.fdi.tfg.viajes.business.entity;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Embeddable
public class GastoViaje {
	
		
	@Enumerated(EnumType.STRING)
	private TipoGasto tipo;
	
	private String descripcion;	
	
	private BigDecimal importe;
	
	public GastoViaje(){
		this(null, null, BigDecimal.ZERO);
	}
	
	public GastoViaje(TipoGasto tipo,String descripcion, BigDecimal importe ){
		this.tipo= tipo;
		this.descripcion = descripcion;
		this.importe = importe;		
	}

	public TipoGasto getTipo() {
		return tipo;
	}

	public void setTipo(TipoGasto tipo) {
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

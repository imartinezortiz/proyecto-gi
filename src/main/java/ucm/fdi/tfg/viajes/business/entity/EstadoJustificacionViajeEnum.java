package ucm.fdi.tfg.viajes.business.entity;

public enum EstadoJustificacionViajeEnum {
	
	EDICION("Edición"), PROCESANDO("Procesando"), PROCESADO("Procesado");
	
	private String estado;
	
	private EstadoJustificacionViajeEnum (String estado){
		this.setEstado(estado);
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return estado;
	}

}

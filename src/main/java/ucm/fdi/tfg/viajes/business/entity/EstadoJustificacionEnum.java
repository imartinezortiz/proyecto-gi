package ucm.fdi.tfg.viajes.business.entity;

public enum EstadoJustificacionEnum {
	
	EDICION("Edición"), PENDIENTE_FIRMA_INVESTIGADOR_PRINCIPAL("Pendiente Firma Investigador Principal"), PROCESANDO("Procesando"), PROCESADO("Procesado");
	
	private String estado;
	
	private EstadoJustificacionEnum (String estado){
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

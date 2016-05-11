package ucm.fdi.tfg.investigacion.business.entity;

public enum Estado {
	
	EDICION("Edición"), PROCESANDO("Procesando"), PROCESADO("Procesado");
	
	private String estado;
	
	private Estado (String estado){
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

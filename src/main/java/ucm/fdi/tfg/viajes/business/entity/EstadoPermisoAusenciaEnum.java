package ucm.fdi.tfg.viajes.business.entity;

public enum EstadoPermisoAusenciaEnum {
		
	 EDICION("Edición"), PENDIENTE_FIRMA_DPTO("Pendiente Firma Dpto"), PENDIENTE_FIRMA_CENTRO("Pendiente Firma Centro"), FIN("Fin");
	
	private String estado;
	
	private EstadoPermisoAusenciaEnum (String estado){
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

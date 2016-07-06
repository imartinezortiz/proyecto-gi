package ucm.fdi.tfg.viajes.business.entity;

public enum EstadoComisionServicioEnum {
	
	EDICION("Edicion"), PENDIENTE_FIRMA_INV_PRINCIPAL("Pendiente Firma Investigador Principal"), PENDIENTE_FIRMA_DPTO("Pendiente Firma Dpto"), PENDIENTE_FIRMA_RRHH_CENTRO("Pendiente Firma RRHH Centro"), PENDIENTE_FIRMA_UNIDAD_GESTORA("Pendiente Firma Unidad Gestora"), PENDIENTE_FIRMA_DECANO("Pendiente Firma Decano"),ACEPTADO("Aceptado"), RECHAZADO("Rechazado");;

	private String estado;
	
	private EstadoComisionServicioEnum (String estado){
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

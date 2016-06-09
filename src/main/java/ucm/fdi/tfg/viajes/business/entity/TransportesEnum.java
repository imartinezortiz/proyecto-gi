package ucm.fdi.tfg.viajes.business.entity;

public enum TransportesEnum {
	
	AVION("Avion"), TREN("Tren"), BUS("Bus"), TAXIS("Taxis"), VEHICULO_OFICIAL("Vehículo Oficial"), VEHICULO_PROPIO("Vehículo Propio"), OTROS("Otros");
	
	private String estado;
	
	private TransportesEnum (String estado){
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

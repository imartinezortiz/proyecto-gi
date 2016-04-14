package ucm.fdi.tfg.viajes.business.entity;

public enum TipoGasto {
	
	//Medios de Transporte
	AVION("Avión"), TREN("Tren"), BUS("Bus"), TAXIS("Taxis"),COCHE("Coche"), OTROS("Otros"),
	//Otros
	ALOJAMIENTO("Alojamiento"), INSCRIPCION_CONGRESO("Inscripción Congreso"), SEMINARIO("Seminario"), REUNION("Reunion");

	
	private String estado;
	
	private TipoGasto (String estado){
		this.setEstado(estado);
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/*	
	public static TipoGasto fromEstado(String estado){
		if(estado.equals("Avion"))
			return TipoGasto.AVION;
		else if(estado.equals("Coche"))
			return TipoGasto.COCHE;
		else
		    return TipoGasto.OTROS;
	}*/
	
	@Override
	public String toString() {
		return estado;
	}
	
	
}

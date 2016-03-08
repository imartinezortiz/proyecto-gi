package ucm.fdi.tfg.proyecto.business.boundary;

public class NuevoProyectoDTO {

	private String referencia;
	private String titulo;
	private Long investigadorId;
	private String numContabilidad;

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getInvestigadorId() {
		return investigadorId;
	}

	public void setInvestigadorId(Long investigadorId) {
		this.investigadorId = investigadorId;
	}

	public String getNumContabilidad() {
		return numContabilidad;
	}

	public void setNumContabilidad(String numContabilidad) {
		this.numContabilidad = numContabilidad;
	}

}

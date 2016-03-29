package ucm.fdi.tfg.proyecto.business.boundary;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class NuevoProyectoDTO {
	
	@NotEmpty(message = "Campo vacío")
	@Email(message = "Debe ser un email")
	private String referencia;
	
	@NotEmpty(message = "Campo vacío")
	private String titulo;
	
	private Long investigadorId;
	
	@NotEmpty(message = "Campo vacío")
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

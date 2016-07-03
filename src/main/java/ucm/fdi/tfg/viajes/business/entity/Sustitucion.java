package ucm.fdi.tfg.viajes.business.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Embeddable;

@Embeddable
public class Sustitucion {
	
	private String asignatura;
	
	private boolean esClase;
	
	private LocalDate dia;
	
	private String sustituto;
	
	
	public Sustitucion(){
		this(null, null, null,null);
	}
	
	public Sustitucion (String asignatura, Boolean esClase, LocalDate dia, String sustituto){		
		this.asignatura = asignatura;
		this.esClase = esClase;
		this.dia = dia;
		this.sustituto = sustituto;		
	}
	
	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public boolean isEsClase() {
		return esClase;
	}

	public void setEsClase(boolean esClase) {
		this.esClase = esClase;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public String getSustituto() {
		return sustituto;
	}

	public void setSustituto(String sustituto) {
		this.sustituto = sustituto;
	}

	public String getRecuperacion() {
		return recuperacion;
	}

	public void setRecuperacion(String recuperacion) {
		this.recuperacion = recuperacion;
	}

	private String recuperacion;
}

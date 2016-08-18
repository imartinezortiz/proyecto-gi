package ucm.fdi.tfg.viajes.business.entity;

import java.time.LocalDate;

import javax.persistence.Embeddable;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Embeddable
public class Sustitucion {
	
	private String asignatura;
	
	private boolean esClase;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dia;
	
	private String sustituto;
	
	
	public Sustitucion(){
		this(null, false, LocalDate.now(),null);
	}
	
	public Sustitucion (String asignatura, boolean esClase, LocalDate dia, String sustituto){		
		this.asignatura = asignatura;
		this.setEsClase(esClase);
		this.dia = dia;
		this.sustituto = sustituto;		
	}
	
	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
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

	public boolean getEsClase() {
		return esClase;
	}

	public void setEsClase(boolean esClase) {
		this.esClase = esClase;
	}


}

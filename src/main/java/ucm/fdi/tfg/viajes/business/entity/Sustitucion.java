package ucm.fdi.tfg.viajes.business.entity;

import java.time.LocalDate;

import javax.persistence.Embeddable;

@Embeddable
public class Sustitucion {
	
	private String asignatura;
	
	private boolean esClase;
	
	private LocalDate dia;
	
	private String sustituto;
	
	private String recuperacion;
}

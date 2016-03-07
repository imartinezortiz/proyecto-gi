package ucm.fdi.tfg.investigadores.business.entity;



import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import ucm.fdi.tfg.investigadores.business.boundary.Persona;




@Entity
@Table(name="Investigadores")
public class Investigador {
	
	@Id
	@Column(name="investigadorId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	Long id;	
	
	private String departamento;
	private String centro;
	
	
	private Persona persona;
	
	public Investigador(){
		
	}
	
	public Investigador(Persona persona, String departamento, String centro ){
		this.persona = persona;
		this.departamento = departamento;
		this.centro = centro;
	}

	public Long getId(){
		return id;
	}	
	

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	

}

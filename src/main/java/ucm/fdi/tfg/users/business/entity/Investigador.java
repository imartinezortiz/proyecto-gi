package ucm.fdi.tfg.users.business.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.boundary.Persona;

@Entity
@Table(name = "Investigadores")
public class Investigador {

	@Id
	@Column(name = "investigadorId")
	private Long id;

	private String departamento;
	private String centro;

	@Embedded
	private Persona persona;

	@OneToMany(mappedBy="investigadorPrincipal")
	private Collection<Proyecto> proyectosDirigidos;
	
	Investigador() {

	}

	public Investigador(Long id, Persona persona, String departamento, String centro) {
		this.id = id;
		this.persona = persona;
		this.departamento = departamento;
		this.centro = centro;
	}

	public Long getId() {
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Collection<Proyecto> getProyectosDirigidos() {
		return proyectosDirigidos;
	}

}

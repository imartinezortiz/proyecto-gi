package ucm.fdi.tfg.users.business.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ucm.fdi.tfg.proyecto.business.entity.Proyecto;

@Entity
@Table(name = "Investigadores")
public class Investigador {

	@Id
	@Column(name = "investigadorId")
	private Long id;

	private String departamento;
	private String centro;
	
	@OneToMany(mappedBy="investigadorPrincipal")
	private Collection<Proyecto> proyectosDirigidos;
	
	Investigador() {

	}

	public Investigador(Long id, String departamento, String centro) {
		this.id = id;
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

	
	public Collection<Proyecto> getProyectosDirigidos() {
		return proyectosDirigidos;
	}

}

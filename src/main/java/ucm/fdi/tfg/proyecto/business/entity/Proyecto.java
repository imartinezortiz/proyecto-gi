package ucm.fdi.tfg.proyecto.business.entity;


import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ucm.fdi.tfg.users.business.entity.Investigador;

@Entity
@Table(name="Proyectos")
public class Proyecto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	private Long id;
	
	private String referencia;
	
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name="investigadorID")
	private Investigador investigadorPrincipal;
	
	@ManyToMany
	private Collection<Investigador> investigadores;
	
	private String numContabilidad;	
	
	
	public Proyecto(){
	
	}
	
	public Long getId(){
		return this.id;
	}

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

	public Investigador getInvestigadorPrincipal() {
		return investigadorPrincipal;
	}

	public void setInvestigadorPrincipal(Investigador investigador) {
		this.investigadorPrincipal = investigador;
	}

	public String getNumContabilidad() {
		return numContabilidad;
	}

	public void setNumContabilidad(String numContabilidad) {
		this.numContabilidad = numContabilidad;
	}
	

}

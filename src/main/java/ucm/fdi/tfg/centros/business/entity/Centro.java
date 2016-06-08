package ucm.fdi.tfg.centros.business.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ucm.fdi.tfg.users.business.entity.User;

@Entity
public class Centro {

	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;

	@ManyToOne
	private User gerente;
	
	@ManyToOne
	private User decano;
	
	@ManyToOne
	private User recursosHumanos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public User getGerente() {
		return gerente;
	}

	public void setGerente(User gerente) {
		this.gerente = gerente;
	}

	public User getDecano() {
		return decano;
	}

	public void setDecano(User decano) {
		this.decano = decano;
	}

	public User getRecursosHumanos() {
		return recursosHumanos;
	}

	public void setRecursosHumanos(User recursosHumanos) {
		this.recursosHumanos = recursosHumanos;
	}

	public Long getId() {
		return id;
	}
	
	
}

package ucm.fdi.tfg.departamentos.business.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

import ucm.fdi.tfg.users.business.entity.User;

@Entity
public class Departamento {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty(message = "Campo vacío")
	private String nombre;
	
	@ManyToOne
	private User director;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public User getDirector() {
		return director;
	}

	public void setDirector(User director) {
		this.director = director;
	}

	public Long getId() {
		return id;
	}
	
	
}

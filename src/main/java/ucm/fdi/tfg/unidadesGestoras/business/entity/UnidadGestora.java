package ucm.fdi.tfg.unidadesGestoras.business.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ucm.fdi.tfg.users.business.entity.User;

@Entity
public class UnidadGestora {

	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	
	@OneToMany
	private Collection<User> responsables;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}
	
	
}

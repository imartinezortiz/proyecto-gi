package ucm.fdi.tfg.investigadores.business.boundary;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import ucm.fdi.tfg.users.business.entity.UserRole;

@Embeddable
public class Persona {
	
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;
	private String password;	
	
	
	private UserRole rol;
	
	public Persona(){
		
	}
	
	public Persona(String nombre, String apellidos, String telefono, String email){
		this.nombre = nombre;
		this.apellidos= apellidos;
		this.telefono = telefono;
		this.email = email;
	
	}
	
	public UserRole getRol() {
		return rol;
	}

	public void setRol(UserRole rol) {
		this.rol = rol;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

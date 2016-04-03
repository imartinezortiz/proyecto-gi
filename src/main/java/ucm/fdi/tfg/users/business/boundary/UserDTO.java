package ucm.fdi.tfg.users.business.boundary;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserDTO {
	

	@Size(min=3, max=20, message="La longitud debe estar entre 3 y 20")
	String username;
	
	@NotEmpty(message = "Campo vacio") 
	String nombre;
	
	@NotEmpty(message = "Campo vacio") 
	String apellidos;
	
	@NotEmpty(message = "Campo vacio") 
	String telefono;
	
	@NotEmpty(message = "Campo vacio") 
	@Email(message = "Debe ser un email") 
	String email;
	
	@NotEmpty(message = "Campo vacio") 
	String password;
	
	@NotEmpty(message = "Campo vacio") 
	String nuevaPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getNuevaPassword() {
		return nuevaPassword;
	}

	public void setNuevaPassword(String nuevaPassword) {
		this.nuevaPassword = nuevaPassword;
	}

}

/**
 * This file is part of proyecto-gi.
 *
 * proyecto-gi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * proyecto-gi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with proyecto-gi.  If not, see <http://www.gnu.org/licenses/>.
 */
package ucm.fdi.tfg.users.business.boundary;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.ScriptAssert;

import ucm.fdi.tfg.centros.business.entity.Centro;
import ucm.fdi.tfg.departamentos.business.entity.Departamento;

@ScriptAssert(lang="javascript",script="(_this.password!=null)?  _this.password.equals(_this.nuevaPassword) : false",message="Los 2 passwords deben ser iguales")
public class NuevoInvestigadorDTO {
	
	//Atributos para Persona
	
	
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
	
	//Atributos para Investigador
	@NotNull(message = "Campo vacio") 
	Departamento departamento;	
	
	@NotNull(message = "Campo vacio") 
	Centro centro;		
	
	
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
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
	
	

}

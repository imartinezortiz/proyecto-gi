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
package ucm.fdi.tfg.centros.business.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

import ucm.fdi.tfg.users.business.entity.User;

@Entity
public class Centro {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty(message = "Campo vacío")
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

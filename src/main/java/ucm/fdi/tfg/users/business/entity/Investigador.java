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
package ucm.fdi.tfg.users.business.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ucm.fdi.tfg.centros.business.entity.Centro;
import ucm.fdi.tfg.departamentos.business.entity.Departamento;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;

@Entity
@Table(name = "Investigadores")
public class Investigador {

	@Id
	@Column(name = "investigadorId")
	private Long id;

	@ManyToOne
	@JoinColumn(name="departamento")
	private Departamento departamento;
	
	@ManyToOne
	@JoinColumn(name="centro")
	private Centro centro;
	
	@OneToMany(mappedBy="investigadorPrincipal")
	private Collection<Proyecto> proyectosDirigidos;
	
	@ManyToMany(mappedBy="investigadores")
	private Collection<Proyecto> proyectos;
	
	public Investigador() {

	}

	public Investigador(Long id, Departamento departamento, Centro centro) {
		this.id = id;
		this.departamento = departamento;
		this.centro = centro;
	}

	public Long getId() {
		return id;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	
	public Collection<Proyecto> getProyectosDirigidos() {
		return proyectosDirigidos;
	}

}

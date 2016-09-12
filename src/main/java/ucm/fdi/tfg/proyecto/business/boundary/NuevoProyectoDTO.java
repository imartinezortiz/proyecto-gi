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
package ucm.fdi.tfg.proyecto.business.boundary;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.validator.constraints.NotEmpty;

import ucm.fdi.tfg.unidadesGestoras.business.entity.UnidadGestora;


public class NuevoProyectoDTO {
	
	private Long idProyecto;

	@NotEmpty(message = "Campo vacío")
	private String referencia;
	
	@NotEmpty(message = "Campo vacío")
	private String titulo;
	
	private Long investigadorId;
	
	@NotEmpty(message = "Mínimo un investigador")
	private Collection<Long> investigadoresID;
	
	private UnidadGestora unidadGestora;
	
	public UnidadGestora getUnidadGestora() {
		return unidadGestora;
	}

	public void setUnidadGestora(UnidadGestora unidadGestora) {
		this.unidadGestora = unidadGestora;
	}

	public NuevoProyectoDTO(){
		investigadoresID = new ArrayList<Long>();
	}
	
	public Long getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}
	
	public Collection<Long> getInvestigadoresID() {
		return investigadoresID;
	}

	public void setInvestigadoresID(Collection<Long> investigadoresID) {
		this.investigadoresID = investigadoresID;
	}

	@NotEmpty(message = "Campo vacío")
	private String numContabilidad;

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

	public Long getInvestigadorId() {
		return investigadorId;
	}

	public void setInvestigadorId(Long investigadorId) {
		this.investigadorId = investigadorId;
	}

	public String getNumContabilidad() {
		return numContabilidad;
	}

	public void setNumContabilidad(String numContabilidad) {
		this.numContabilidad = numContabilidad;
	}

}

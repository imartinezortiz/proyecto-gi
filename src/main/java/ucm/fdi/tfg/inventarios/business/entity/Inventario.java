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
package ucm.fdi.tfg.inventarios.business.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import ucm.fdi.tfg.fileupload.business.entity.Attachment;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.viajes.business.entity.EstadoJustificacionEnum;

@Entity
@Table(name="Inventarios")
public class Inventario {

	@Id
	@Column(name="inventarioId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	private Long id;
	
	
	@OneToOne
	private Proyecto proyecto;
	
	@NotEmpty(message="Campo vacio")
	private String descripcion;
	
	@NotEmpty(message="Campo vacio")
	private String centro;
	
	private String autorizacion;
	
	private String observaciones;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message="Introduce una fecha")
	private LocalDate fecha;
	
	@Enumerated(EnumType.STRING)
	private EstadoJustificacionEnum fase;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Attachment> adjuntos;
	
	public List<Attachment> getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(List<Attachment> adjuntos) {
		this.adjuntos = adjuntos;
	}

	public Inventario(){
		this.fecha = LocalDate.now();
		adjuntos = new ArrayList<Attachment>();
		this.fase= EstadoJustificacionEnum.EDICION;
	}
	
	public Inventario(Proyecto proyecto){
		this.proyecto = proyecto;
		this.fecha = LocalDate.now();
		this.fase= EstadoJustificacionEnum.EDICION;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public EstadoJustificacionEnum getFase() {
		return fase;
	}

	public void setFase(EstadoJustificacionEnum fase) {
		this.fase = fase;
	}
	
	

}

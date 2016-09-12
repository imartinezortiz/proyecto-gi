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
package ucm.fdi.tfg.viajes.business.entity;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyEnumerated;

import org.hibernate.annotations.MapKeyType;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.entity.Investigador;

@Entity
public class ComisionServicio {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Proyecto proyecto;
	
	@ManyToOne
	private Investigador interesado;
	
	private String itinerario;
	
	private String objetoDesplazamiento;
	
	private boolean gastosInscripcion;
	
	@NotEmpty
	private String observaciones;
	
	private TransportesEnum transportePrincipal;
	
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	//@Column(nullable=false)
	private LocalDate inicio;
	
	

	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	//@Column(nullable=false)
	private LocalDate fin;
	
	@Enumerated(EnumType.STRING)
	private EstadoComisionServicioEnum estado;
	
	
	@ElementCollection
	@MapKeyEnumerated(EnumType.STRING)
	//Influye en el LocalDate
	@MapKeyType(@Type(type="string"))
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
    private Map<String, LocalDate> vbs;
  //private Map<EstadoComisionServicioEnum, LocalDate> vbs;
	
	public ComisionServicio(){
		this.estado = EstadoComisionServicioEnum.EDICION;
		vbs = new HashMap<String, LocalDate>();
	}
	
	
	public ComisionServicio(Proyecto proyecto, Investigador interesado) {
		this.proyecto = proyecto;
		this.estado = EstadoComisionServicioEnum.EDICION;
		this.interesado = interesado;
		this.vbs = new HashMap<String, LocalDate>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Investigador getInteresado() {
		return interesado;
	}

	public void setInteresado(Investigador interesado) {
		this.interesado = interesado;
	}

	public String getItinerario() {
		return itinerario;
	}

	public void setItinerario(String itinerario) {
		this.itinerario = itinerario;
	}

	public String getObjetoDesplazamiento() {
		return objetoDesplazamiento;
	}

	public void setObjetoDesplazamiento(String objetoDesplazamiento) {
		this.objetoDesplazamiento = objetoDesplazamiento;
	}

	public boolean isGastosInscripcion() {
		return gastosInscripcion;
	}

	public void setGastosInscripcion(boolean gastosInscripcion) {
		this.gastosInscripcion = gastosInscripcion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public TransportesEnum getTransportePrincipal() {
		return transportePrincipal;
	}

	public void setTransportePrincipal(TransportesEnum transportePrincipal) {
		this.transportePrincipal = transportePrincipal;
	}

	public EstadoComisionServicioEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoComisionServicioEnum estado) {
		this.estado = estado;
	}

	public Map<String, LocalDate> getVbs() {
		return vbs;
	}

	public void setVbs(Map<String, LocalDate> vbs) {
		this.vbs = vbs;
	}
	
	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}
}

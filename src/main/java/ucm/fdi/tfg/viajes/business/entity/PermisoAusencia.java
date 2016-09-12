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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyEnumerated;
import org.hibernate.annotations.MapKeyType;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.entity.Investigador;

@Entity
public class PermisoAusencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Proyecto proyecto;
	
	@ManyToOne
	private Investigador interesado;
	
	private String motivo;
	
	private String lugar;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	//@Column(nullable=false)
	private LocalDate desde;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	//@Column(nullable=false)
	private LocalDate hasta;
	
	private boolean afectaDodencia;
	
	@ElementCollection
	private Collection<Sustitucion> sustituciones;

	private String otrasActividades;
	
	@Enumerated(EnumType.STRING)
	private EstadoPermisoAusenciaEnum estado;
	
	@ElementCollection
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyType(@Type(type="string"))
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	private Map<String, LocalDate> vbs;
	
	private boolean generaGasto;
	
	
	public PermisoAusencia(){
		this.estado = EstadoPermisoAusenciaEnum.EDICION;
		this.sustituciones = new ArrayList<Sustitucion>();
		vbs = new HashMap<String, LocalDate>();				
	}
		
	public PermisoAusencia(Proyecto proyecto, Investigador interesado) {
		this.proyecto = proyecto;
		this.interesado = interesado;
		this.estado = EstadoPermisoAusenciaEnum.EDICION;	
		vbs = new HashMap<String, LocalDate>();
		this.sustituciones = new ArrayList<Sustitucion>();
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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public LocalDate getDesde() {
		return desde;
	}

	public void setDesde(LocalDate desde) {
		this.desde = desde;
	}

	public LocalDate getHasta() {
		return hasta;
	}

	public void setHasta(LocalDate hasta) {
		this.hasta = hasta;
	}

	public boolean isAfectaDodencia() {
		return afectaDodencia;
	}

	public void setAfectaDodencia(boolean afectaDodencia) {
		this.afectaDodencia = afectaDodencia;
	}

	public Collection<Sustitucion> getSustituciones() {
		return sustituciones;
	}

	public void setSustituciones(Collection<Sustitucion> sustituciones) {
		this.sustituciones = sustituciones;
	}

	public String getOtrasActividades() {
		return otrasActividades;
	}

	public void setOtrasActividades(String otrasActividades) {
		this.otrasActividades = otrasActividades;
	}

	public EstadoPermisoAusenciaEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoPermisoAusenciaEnum estado) {
		this.estado = estado;
	}

	public Map<String, LocalDate> getVbs() {
		return vbs;
	}

	public void setVbs(Map<String, LocalDate> vbs) {
		this.vbs = vbs;
	}

	public boolean isGeneraGasto() {
		return generaGasto;
	}

	public void setGeneraGasto(boolean generaGasto) {
		this.generaGasto = generaGasto;
	}

}

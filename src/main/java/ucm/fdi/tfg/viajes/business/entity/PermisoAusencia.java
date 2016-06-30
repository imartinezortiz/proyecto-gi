package ucm.fdi.tfg.viajes.business.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue
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
	private List<Sustitucion> sustituciones;

	private String otrasActividades;
	
	private EstadoPermisoAusenciaEnum estado;
	
	@ElementCollection
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyType(@Type(type="string"))
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	private Map<EstadoPermisoAusenciaEnum, LocalDate> vbs;
	
	private boolean generaGasto;
	
	
	public PermisoAusencia(){
		
	}
		
	public PermisoAusencia(Proyecto proyecto, Investigador interesado) {
		this.proyecto = proyecto;
		this.interesado = interesado;
		this.estado = EstadoPermisoAusenciaEnum.EDICION;	
				
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

	public List<Sustitucion> getSustituciones() {
		return sustituciones;
	}

	public void setSustituciones(List<Sustitucion> sustituciones) {
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

	public Map<EstadoPermisoAusenciaEnum, LocalDate> getVbs() {
		return vbs;
	}

	public void setVbs(Map<EstadoPermisoAusenciaEnum, LocalDate> vbs) {
		this.vbs = vbs;
	}

	public boolean isGeneraGasto() {
		return generaGasto;
	}

	public void setGeneraGasto(boolean generaGasto) {
		this.generaGasto = generaGasto;
	}

}

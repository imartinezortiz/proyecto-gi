package ucm.fdi.tfg.viajes.business.entity;

import java.time.LocalDate;
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
	
	private String observaciones;
	
	private TransportesEnum transportePrincipal;
	
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(nullable=false)
	private LocalDate inicio;
	
	

	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(nullable=false)
	private LocalDate fin;
	

	private EstadoComisionServicioEnum estado;
	
	@ElementCollection
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyType(@Type(type="string"))
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	private Map<EstadoComisionServicioEnum, LocalDate> vbs;
	
	public ComisionServicio(Proyecto proyecto, Investigador interesado) {
		this.proyecto = proyecto;
		this.estado = EstadoComisionServicioEnum.EDICION;
		this.interesado = interesado;
	
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

	public Map<EstadoComisionServicioEnum, LocalDate> getVbs() {
		return vbs;
	}

	public void setVbs(Map<EstadoComisionServicioEnum, LocalDate> vbs) {
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

package ucm.fdi.tfg.viajes.business.entity;

import java.time.LocalDate;
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
	enum EstadoPermisoAusenciaEnum { EDICION, PENDIENTE_FIRMA_DPTO, PENDIENTE_FIRMA_CENTRO, FIN}
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
	@Column(nullable=false)
	private LocalDate desde;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(nullable=false)
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
}

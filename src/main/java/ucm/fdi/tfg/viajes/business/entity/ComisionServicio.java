package ucm.fdi.tfg.viajes.business.entity;

import java.time.LocalDate;
import java.util.Map;

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

	enum TransportesEnum {AVION, TREN, BUS, TAXIS, VEHICULO_OFICIAL, VEHICULO_PROPIO, OTROS }
	
	enum EstadoComisionServicioEnum { EDICION, PENDIENTE_FIRMA_INV_PRINCIPAL, PENDIENTE_FIRMA_DPTO, PENDIENTE_FIRMA_RRHH_CENTRO, PENDIENTE_FIRMA_UNIDAD_GESTORA, PENDIENTE_FIRMA_CENTRO, FIN}

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
	

	private EstadoComisionServicioEnum estado;
	
	@ElementCollection
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyType(@Type(type="string"))
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	private Map<EstadoComisionServicioEnum, LocalDate> vbs;
}

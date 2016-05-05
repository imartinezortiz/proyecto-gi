package ucm.fdi.tfg.viajes.business.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.entity.Investigador;

@Entity
@Table(name="viajes")
public class Viaje {
	
	@Id
	@Column(name="viajeId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	private Long id;
	
	private String numOrden;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message="Introduce una fecha")
	private LocalDate fecha;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Proyecto proyecto;
	
	private boolean miembroProyecto;
	
	private Long investigador;
	
	private String invitado;
	
	private String objetoDesplazamiento;
	private String itinerario;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message="Introduce una fecha")
	private LocalDate fechaInicio;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message="Introduce una fecha")
	private LocalDate fechaFin;
	
	
	
// ----------- JUSTIFICACION GASTOS -----------------------------------
	
	@ElementCollection
	private Collection<GastoViaje> gastos; //Transporte 

	//Atributos para Dieta
		private BigDecimal importeDietaTotal;		
		private Long dietaID;
		private Double numDietas;
	

// --------------------------------------------------------------------
	
	
	private String pagarA;
	
	private String observaciones;	
	
	

	public Viaje(){
		this.miembroProyecto = true;
		this.gastos = new ArrayList<GastoViaje>();
		this.fecha = LocalDate.now();
		
	}
	
		
	public Viaje(Proyecto proyecto) {
		this.miembroProyecto = true;
		this.proyecto = proyecto;
		this.gastos = new ArrayList<GastoViaje>();
		this.fecha = LocalDate.now();
	}

	
	
	public BigDecimal getImporteDietaTotal() {
		return importeDietaTotal;
	}


	public void setImporteDietaTotal(BigDecimal importeDietaTotal) {
		this.importeDietaTotal = importeDietaTotal;
	}


	public Double getNumDietas() {
		return numDietas;
	}


	public void setNumDietas(Double numDietas) {
		this.numDietas = numDietas;
	}


	public Long getDietaID() {
		return dietaID;
	}


	public void setDietaID(Long dietaID) {
		this.dietaID = dietaID;
	}

	

	public String getPagarA() {
		return pagarA;
	}

	public void setPagarA(String pagarA) {
		this.pagarA = pagarA;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}	
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public Long getId() {
		return id;
	}

	public BigDecimal getImporteDieta() {
		return importeDietaTotal;
	}

	public void setImporteDieta(BigDecimal importeDieta) {
		this.importeDietaTotal = importeDieta;
	}
	
	public String getInvitado() {
		return invitado;
	}

	public void setInvitado(String invitado) {
		this.invitado = invitado;
	}

	public boolean isMiembroProyecto() {
		return miembroProyecto;
	}


	public void setMiembroProyecto(boolean miembroProyecto) {
		this.miembroProyecto = miembroProyecto;
	}


	public Long getInvestigador() {
		return investigador;
	}


	public void setInvestigador(Long investigador) {
		this.investigador = investigador;
	}


	public String getObjetoDesplazamiento() {
		return objetoDesplazamiento;
	}

	public Collection<GastoViaje> getGastos() {
		return gastos;
	}

	public void setGastos(Collection<GastoViaje> gastos) {
		this.gastos = gastos;
	}

	public void setObjetoDesplazamiento(String objetoDesplazamiento) {
		this.objetoDesplazamiento = objetoDesplazamiento;
	}

	public String getItinerario() {
		return itinerario;
	}

	public void setItinerario(String itinerario) {
		this.itinerario = itinerario;
	}

	
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public String getNumOrden() {
		return numOrden;
	}

	public void setNumOrden(String numOrden) {
		this.numOrden = numOrden;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	

}

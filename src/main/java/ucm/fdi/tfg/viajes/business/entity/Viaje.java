package ucm.fdi.tfg.viajes.business.entity;

import java.math.BigDecimal;
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
import javax.validation.constraints.Digits;

import ucm.fdi.tfg.pagos.business.entity.Gasto;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;

@Entity
@Table(name="viajes")
public class Viaje {
	
	@Id
	@Column(name="viajeId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	private Long id;
	
	private String numOrden;
	
	private String fecha;	
	
	@OneToOne(fetch=FetchType.LAZY)
	private Proyecto proyecto;
	
	private String credenciales;
		
	private String relacion;
	
	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	private String objetoDesplazamiento;
	private String itinerario;
	private String fechaInicio;
	private String fechaFin;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private Collection<GastoViaje> gastos;

	private BigDecimal importeDieta;
	
	private String congreso;
	
	private String seminario;
	
	private String pagarA;
	
	private String observaciones;
	
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

	
	public String getCongreso() {
		return congreso;
	}

	public void setCongreso(String congreso) {
		this.congreso = congreso;
	}

	public String getSeminario() {
		return seminario;
	}

	public void setSeminario(String seminario) {
		this.seminario = seminario;
	}

	public String getReunion() {
		return reunion;
	}

	public void setReunion(String reunion) {
		this.reunion = reunion;
	}

	private String reunion;
	

	public Viaje(){
		this.gastos = new ArrayList<GastoViaje>();
		
	}
	
	public Viaje(Proyecto proyecto) {
		this.proyecto = proyecto;
		this.gastos = new ArrayList<GastoViaje>();
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getImporteDieta() {
		return importeDieta;
	}

	public void setImporteDieta(BigDecimal importeDieta) {
		this.importeDieta = importeDieta;
	}
	
	public String getRelacion() {
		return relacion;
	}

	public void setRelacion(String relacion) {
		this.relacion = relacion;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCredenciales() {
		return credenciales;
	}

	public void setCredenciales(String credenciales) {
		this.credenciales = credenciales;
	}

}

package ucm.fdi.tfg.viajes.business.entity;

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
	private String medioTransporte;
	

	@ElementCollection(fetch=FetchType.EAGER)
	private Collection<GastoViaje> gastos;


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

	
	public String getMedioTransporte() {
		return medioTransporte;
	}

	public void setMedioTransporte(String medioTransporte) {
		this.medioTransporte = medioTransporte;
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

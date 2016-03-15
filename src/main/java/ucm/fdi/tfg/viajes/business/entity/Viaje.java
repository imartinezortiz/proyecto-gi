package ucm.fdi.tfg.viajes.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ucm.fdi.tfg.proyecto.business.entity.Proyecto;

@Entity
@Table(name="viajes")
public class Viaje {
	
	@Id
	@Column(name="viajeId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	Long id;
	
	private String relacion;
	private String objetoDesplazamiento;
	private String itinerario;
	private String fechas;
	private String medioTransporte;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Proyecto proyecto;
	


	public Viaje(){
		
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

	public void setObjetoDesplazamiento(String objetoDesplazamiento) {
		this.objetoDesplazamiento = objetoDesplazamiento;
	}

	public String getItinerario() {
		return itinerario;
	}

	public void setItinerario(String itinerario) {
		this.itinerario = itinerario;
	}

	public String getFechas() {
		return fechas;
	}

	public void setFechas(String fechas) {
		this.fechas = fechas;
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

}

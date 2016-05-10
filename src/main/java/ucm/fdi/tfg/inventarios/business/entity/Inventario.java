package ucm.fdi.tfg.inventarios.business.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import ucm.fdi.tfg.fileupload.business.entity.Attachment;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;

@Entity
@Table(name="Inventarios")
public class Inventario {

	@Id
	@Column(name="inventarioId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	private Long id;
	
	
	@OneToOne
	private Proyecto proyecto;
	
	@NotEmpty(message="Campo vacio")
	private String descripcion;
	
	@NotEmpty(message="Campo vacio")
	private String centro;
	
	private String autorizacion;
	
	private String observaciones;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message="Introduce una fecha")
	private LocalDate fecha;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Attachment> adjuntos;
	
	public List<Attachment> getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(List<Attachment> adjuntos) {
		this.adjuntos = adjuntos;
	}

	public Inventario(){
		this.fecha = LocalDate.now();
		adjuntos = new ArrayList<Attachment>();
	}
	
	public Inventario(Proyecto proyecto){
		this.proyecto = proyecto;
		this.fecha = LocalDate.now();
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}
	
	

}

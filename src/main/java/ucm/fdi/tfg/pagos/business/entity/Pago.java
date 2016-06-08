package ucm.fdi.tfg.pagos.business.entity;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import ucm.fdi.tfg.fileupload.business.entity.Attachment;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.viajes.business.entity.EstadoJustificacionViajeEnum;

@Entity
@Table(name="PAGOS")
public class Pago {

	@Id
	@Column(name="pagoId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	private Long id;
	
	@NotEmpty(message="Campo vacio")
	private String numOrden;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message="Introduce una fecha")
	private LocalDate fecha;
	
	@NotEmpty(message="Campo vacio")
	private String iban;
	
	@NotEmpty(message="Campo vacio")
	private String bic;
	
	@NotEmpty(message="Campo vacio")
	private String pagador;
	
	@NotEmpty(message="Campo vacio")
	private String memoria;
	
	@NotEmpty(message="Campo vacio")
	private String relacion;

	@OneToOne
	private Proyecto proyecto;
	
	@Valid
	@ElementCollection
	@CollectionTable(name="PAGO_GASTOS", joinColumns=@JoinColumn(name="pagoId"),  uniqueConstraints=@UniqueConstraint(columnNames={"pagoId", "numFactura"}))  
	private Collection<Gasto> gastos;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Attachment> adjuntos;
	
	@Enumerated(EnumType.STRING)
	private EstadoJustificacionViajeEnum fase;
	
	Pago() {
		this.gastos = new ArrayList<Gasto>();
		adjuntos = new ArrayList<Attachment>();
		this.fase= EstadoJustificacionViajeEnum.EDICION;
	}
	public Pago(Proyecto proyecto) {
		this(proyecto, "","");
		this.fase= EstadoJustificacionViajeEnum.EDICION;
	}
	
	public Pago (Proyecto proyecto, String numOrden,String fecha) {			
		this.numOrden = numOrden;
		this.fecha = LocalDate.now();
		this.proyecto = proyecto;
		this.gastos = new ArrayList<Gasto>();	
	}
	
	public List<Attachment> getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(List<Attachment> adjuntos) {
		this.adjuntos = adjuntos;
	}

	
	public void setNumOrden(String numOrden) {
		this.numOrden = numOrden;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Collection<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(Collection<Gasto> gastos) {
		this.gastos = gastos;
	}
	public String getMemoria() {
		return memoria;
	}
	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}
	public String getRelacion() {
		return relacion;
	}
	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}
	public String getPagador() {
		return pagador;
	}
	public void setPagador(String pagador) {
		this.pagador = pagador;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public String getBic() {
		return bic;
	}
	public void setBic(String bic) {
		this.bic = bic;
	}
	public Long getId() {
		return id;
	}
	public String getNumOrden() {
		return numOrden;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public EstadoJustificacionViajeEnum getFase() {
		return fase;
	}

	public void setFase(EstadoJustificacionViajeEnum fase) {
		this.fase = fase;
	}
	
	
}

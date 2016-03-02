package ucm.fdi.tfg.pagos.business.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ucm.fdi.tfg.investigadores.business.entity.Investigador;

@Entity
@Table(name="PAGOS")
public class Pago {

	@Id
	@Column(name="pagoId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	Long id;
	
	private int numOrden;
	private String proyecto;	
	private int numContabilidad;
	//private LocalDate fecha;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Investigador investigadorPrincipal;
		
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PAGO_GASTOS", joinColumns=@JoinColumn(name="pagoId"),  uniqueConstraints=@UniqueConstraint(columnNames={"pagoId", "numFactura"}))
	private Collection<Gasto> gastos;
	
	
	public Pago()
	{
		this.gastos = new ArrayList<Gasto>();
	}
	
	public Pago (int numOrden,String proyecto, int numContabilidad, Investigador investigadorPrincipal)
	{
				
		this.numOrden = numOrden;
		//this.fecha = fecha;
		this.proyecto = proyecto;
		this.numContabilidad = numContabilidad;
		this.investigadorPrincipal = investigadorPrincipal;
		this.gastos = new ArrayList<Gasto>();	
	}

	
	public void setNumOrden(int numOrden) {
		this.numOrden = numOrden;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public int getNumContabilidad() {
		return numContabilidad;
	}

	public void setNumContabilidad(int numContabilidad) {
		this.numContabilidad = numContabilidad;
	}

	public Investigador getInvestigadorPrincipal() {
		return investigadorPrincipal;
	}

	public void setInvestigadorPrincipal(Investigador investigadorPrincipal) {
		this.investigadorPrincipal = investigadorPrincipal;
	}

	public Collection<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(Collection<Gasto> gastos) {
		this.gastos = gastos;
	}
	
	
	
}

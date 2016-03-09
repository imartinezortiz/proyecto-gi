package ucm.fdi.tfg.pagos.business.entity;


import java.util.ArrayList;
import java.util.Collection;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.entity.Investigador;

@Entity
@Table(name="PAGOS")
public class Pago {

	@Id
	@Column(name="pagoId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	Long id;
	
	private String numOrden;	
	//private LocalDate fecha;
	

	@OneToOne(fetch=FetchType.LAZY)
	private Proyecto proyecto;
		
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PAGO_GASTOS", joinColumns=@JoinColumn(name="pagoId"),  uniqueConstraints=@UniqueConstraint(columnNames={"pagoId", "numFactura"}))
	private Collection<Gasto> gastos;
	
	Pago() {
		
	}
	public Pago(Proyecto proyecto) {
		this(proyecto, "" );
	}
	
	public Pago (Proyecto proyecto, String numOrden) {			
		this.numOrden = numOrden;
		//this.fecha = fecha;
		this.proyecto = proyecto;
		this.gastos = new ArrayList<Gasto>();	
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
	
	
	
}

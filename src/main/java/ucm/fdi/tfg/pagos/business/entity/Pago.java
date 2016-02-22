package ucm.fdi.tfg.pagos.business.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="PAGOS")
public class Pago {

	@Id
	@Column(name="pagoId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	long id;
	
	private int numOrden;
	private String proyecto;	
	private int numContabilidad;
	
	//private Date fecha;	//hay que ver que formato le damos	
	
	private String investigadorPrincipal; //Habrá que crear una entidad investigador 
		
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PAGO_GASTOS", joinColumns=@JoinColumn(name="pagoId"),  uniqueConstraints=@UniqueConstraint(columnNames={"pagoId", "numFactura"}))
	private Collection<Gasto> gastos;
	
	
	public Pago()
	{
		//this.gastos = new ArrayList<Gasto>();
	}
	
	public Pago (int numOrden, String proyecto, int numContabilidad, String investigadorPrincipal)
	{
		this.numOrden = numOrden;
		this.proyecto = proyecto;
		this.numContabilidad = numContabilidad;
		this.investigadorPrincipal = investigadorPrincipal;
		//this.fecha = fecha;		
		//this.gastos = new ArrayList<Gasto>();		
	}
	
}

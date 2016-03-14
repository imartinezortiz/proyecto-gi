package ucm.fdi.tfg.inventarios.business.entity;

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
@Table(name="Inventarios")
public class Inventario {

	@Id
	@Column(name="inventarioId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	Long id;
	
	private String numContabilidad;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Proyecto proyecto;

}

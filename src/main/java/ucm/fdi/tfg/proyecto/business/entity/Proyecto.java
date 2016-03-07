package ucm.fdi.tfg.proyecto.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ucm.fdi.tfg.investigadores.business.entity.Investigador;

@Entity
public class Proyecto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	Long id;
	String referencia;
	String titulo;
	Investigador investigador;
	String numContabilidad;

}

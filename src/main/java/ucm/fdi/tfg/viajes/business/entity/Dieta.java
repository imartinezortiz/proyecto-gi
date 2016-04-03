package ucm.fdi.tfg.viajes.business.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="dietas")
public class Dieta {
	
	@Id
	@Column(name="dietaId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	String pais;
	
	// @Digits(integer=2, fraction=2)    Preguntar a Iván si esta anotacion es correcta.
	BigDecimal importe;
	
}

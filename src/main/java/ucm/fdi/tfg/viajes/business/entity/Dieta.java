package ucm.fdi.tfg.viajes.business.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="dietas")
public class Dieta {
	
	@Id
	@Column(name="dietaId")
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	@Column(unique=true)
	String pais;
	
	@Digits(integer=2, fraction=2)   
	BigDecimal importe;
	
	
	public Dieta(){
		
	}

	public Long getId() {
		return id;
	}

	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public BigDecimal getImporte() {
		return importe;
	}


	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
}

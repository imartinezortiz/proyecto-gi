/**
 * This file is part of proyecto-gi.
 *
 * proyecto-gi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * proyecto-gi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with proyecto-gi.  If not, see <http://www.gnu.org/licenses/>.
 */
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

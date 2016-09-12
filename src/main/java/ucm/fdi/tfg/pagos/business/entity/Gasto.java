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
package ucm.fdi.tfg.pagos.business.entity;

import java.math.BigDecimal;


import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;


@Embeddable // es atributo de un Entity 
@Table(name="PAGO_GASTOS")
public class Gasto {
	
	@NotEmpty(message="Campo vacio")
	private String numFactura;
	
	@NotEmpty(message="Campo vacio")
	private String proveedor;
	
	
	
	//@NotBlank(message = "Campo vacio")
	@Digits(integer=6, fraction=2,  message="Máximo dos decimales.")
	@Min(value = (long) 0.01, message = "Valor mayor que 0.")
	private BigDecimal importe;
	
	
	
	public Gasto(){
		this(null, null, BigDecimal.ZERO);
	}
	
	public Gasto(String numFactura,String provedor, BigDecimal importe ){
		this.numFactura = numFactura;
		this.proveedor = provedor;
		this.importe = importe;
	}	


	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
}

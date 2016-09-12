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

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Embeddable
public class GastoViaje {
	
		
	@Enumerated(EnumType.STRING)
	private TipoGasto tipo;
	
	private String descripcion;	
	
	private BigDecimal importe;
	
	public GastoViaje(){
		this(null, null, BigDecimal.ZERO);
	}
	
	public GastoViaje(TipoGasto tipo,String descripcion, BigDecimal importe ){
		this.tipo= tipo;
		this.descripcion = descripcion;
		this.importe = importe;		
	}

	public TipoGasto getTipo() {
		return tipo;
	}

	public void setTipo(TipoGasto tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

}

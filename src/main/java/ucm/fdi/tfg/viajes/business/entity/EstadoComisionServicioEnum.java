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

public enum EstadoComisionServicioEnum {
	
	EDICION("Edicion"), PENDIENTE_FIRMA_INV_PRINCIPAL("Pendiente Firma Investigador Principal"), PENDIENTE_FIRMA_DPTO("Pendiente Firma Dpto"), PENDIENTE_FIRMA_RRHH_CENTRO("Pendiente Firma RRHH Centro"), PENDIENTE_FIRMA_UNIDAD_GESTORA("Pendiente Firma Unidad Gestora"), PENDIENTE_FIRMA_DECANO("Pendiente Firma Decano"),ACEPTADO("Aceptado"), RECHAZADO("Rechazado");;

	private String estado;
	
	private EstadoComisionServicioEnum (String estado){
		this.setEstado(estado);
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return estado;
	}
	
}

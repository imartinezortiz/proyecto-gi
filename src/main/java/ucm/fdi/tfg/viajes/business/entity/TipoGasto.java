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

public enum TipoGasto {
	
	//Medios de Transporte
	AVION("Avión"), TREN("Tren"), BUS("Bus"), TAXIS("Taxis"),COCHE("Coche"), OTROS("Otros"),
	//Otros
	ALOJAMIENTO("Alojamiento"), INSCRIPCION_CONGRESO("Inscripción Congreso"), SEMINARIO("Seminario"), REUNION("Reunion");

	
	private String estado;
	
	private TipoGasto (String estado){
		this.setEstado(estado);
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/*	
	public static TipoGasto fromEstado(String estado){
		if(estado.equals("Avion"))
			return TipoGasto.AVION;
		else if(estado.equals("Coche"))
			return TipoGasto.COCHE;
		else
		    return TipoGasto.OTROS;
	}*/
	
	@Override
	public String toString() {
		return estado;
	}
	
	
}

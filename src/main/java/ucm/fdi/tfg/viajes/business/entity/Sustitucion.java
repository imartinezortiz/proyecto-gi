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

import java.time.LocalDate;

import javax.persistence.Embeddable;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Embeddable
public class Sustitucion {
	
	private String asignatura;
	
	private boolean esClase;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dia;
	
	private String sustituto;
	
	
	public Sustitucion(){
		this(null, false, LocalDate.now(),null);
	}
	
	public Sustitucion (String asignatura, boolean esClase, LocalDate dia, String sustituto){		
		this.asignatura = asignatura;
		this.setEsClase(esClase);
		this.dia = dia;
		this.sustituto = sustituto;		
	}
	
	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public String getSustituto() {
		return sustituto;
	}

	public void setSustituto(String sustituto) {
		this.sustituto = sustituto;
	}

	public boolean getEsClase() {
		return esClase;
	}

	public void setEsClase(boolean esClase) {
		this.esClase = esClase;
	}


}

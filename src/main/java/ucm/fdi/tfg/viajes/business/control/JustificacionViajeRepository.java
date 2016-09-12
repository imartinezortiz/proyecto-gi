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
package ucm.fdi.tfg.viajes.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ucm.fdi.tfg.viajes.business.entity.Dieta;
import ucm.fdi.tfg.viajes.business.entity.JustificacionViaje;


public interface JustificacionViajeRepository extends JpaRepository<JustificacionViaje, Long>{

	@Query("SELECT D FROM Dieta D") 
	public List<Dieta> DameDietas();
	
	//@Query(" SELECT I FROM Viaje I LEFT JOIN FETCH I.gastos G JOIN FETCH I.proyecto P WHERE I.proyecto.id = :idProyecto")
	@Query(" SELECT I FROM JustificacionViaje I WHERE I.proyecto.id = :idProyecto")
	public List<JustificacionViaje> viajesPorProyecto(@Param(value = "idProyecto") Long idProyecto);
	
	@Query(" SELECT I FROM JustificacionViaje I WHERE I.fase = 'PROCESANDO' ")
	public List<JustificacionViaje> viajesProcesando();
}

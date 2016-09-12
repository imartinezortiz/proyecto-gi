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
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.viajes.business.entity.EstadoPermisoAusenciaEnum;
import ucm.fdi.tfg.viajes.business.entity.JustificacionViaje;
import ucm.fdi.tfg.viajes.business.entity.PermisoAusencia;

@Repository
public interface PermisoAusenciaRepository extends JpaRepository<PermisoAusencia, Long>{

	List<PermisoAusencia> findByEstado(EstadoPermisoAusenciaEnum estado);
	
	@Query(" SELECT I FROM PermisoAusencia I WHERE I.proyecto.id = :idProyecto")
	public List<PermisoAusencia> permisosAusenciaPorProyecto(@Param(value = "idProyecto") Long idProyecto);
	

}

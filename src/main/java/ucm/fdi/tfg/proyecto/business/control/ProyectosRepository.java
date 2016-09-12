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
package ucm.fdi.tfg.proyecto.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.proyecto.business.entity.Proyecto;
import ucm.fdi.tfg.users.business.entity.User;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyecto, Long> {
	
	@Query("SELECT U FROM User U WHERE U.id IN (SELECT I.id FROM Proyecto P, Investigador I WHERE P.id = :idProyecto AND I MEMBER OF P.investigadores)") 
	public List<User> investigadoresProyecto(@Param(value = "idProyecto") Long idProyecto);

	@Query("Select distinct P from Proyecto P join P.investigadores i where i.id = :idUser") // Proyectos en los que participa
	public List<Proyecto> proyectosPorInvestigadorParticipante(@Param(value = "idUser") Long idUser);
	
	@Query("SELECT P FROM Proyecto P WHERE P.investigadorPrincipal.id = :idUser") // Proyectos en los que es investigador Principal 
	public List<Proyecto> proyectosPorInvestigadorPrincipal(@Param(value = "idUser") Long idUser);
		
}

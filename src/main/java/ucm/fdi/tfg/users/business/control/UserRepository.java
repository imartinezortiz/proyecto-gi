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
package ucm.fdi.tfg.users.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.users.business.entity.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	
	@Query("SELECT U FROM User U ,Investigador I WHERE U.id = I.id") 
	public List<User> DameDatosUserDeInvestigadores();

	//@Query(value ="SELECT * from users where usersId in (SELECT usersId from user_roles where role=?1)", nativeQuery = true)
	@Query("SELECT U from User U where ?1 MEMBER OF U.roles")
	public List<User> DameDatosUser(UserRole rol);
	
	
}

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
package ucm.fdi.tfg.users.business.entity;



import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Embeddable // es atributo de un Entity ()
@Table(name="USER_ROLES")
public class UserRole implements  GrantedAuthority {


	private static final long serialVersionUID = 1L;
	@Basic
	private String role;
	
	UserRole() {
		
	}
	
	public UserRole(String role) {
		this.role = role;
	}
	
	public String getRole(){
		return this.role;
	}
	
	@Override
	public String getAuthority() {
		return this.role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRole [role=" + role + "]";
	}

}

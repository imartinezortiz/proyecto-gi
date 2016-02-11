package ucm.fdi.tfg.users.business.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name="Users")
public class User implements UserDetails, CredentialsContainer {
	

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="usersId")
	@GeneratedValue(strategy=GenerationType.IDENTITY) //va incrementando id
	int id;
	private String username;
	private String password;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="USER_ROLES", joinColumns=@JoinColumn(name="usersId"),  uniqueConstraints=@UniqueConstraint(columnNames={"usersId", "role"}))
	private Collection<UserRole> roles;
	
	private boolean accountExpired;
	
	private boolean accountLocked;
	
	private boolean credentialsExpired;
	
	private boolean enabled;
	
	public User(){
		this.roles = new ArrayList<UserRole>();
		this.enabled = true;
		this.accountExpired = false;
		this.accountLocked = false;
		this.credentialsExpired = false;
	}
	

	public User (String username, String password)
	{
		this.username = username;
		this.password = password;
		this.enabled = true;
		this.accountExpired = false;
		this.accountLocked = false;
		this.credentialsExpired = false;
		this.roles = new ArrayList<UserRole>();
	}

	public String getName() {
		return username;
	}
	public void setName(String username) {
		this.username = username;
		
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Collection<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<UserRole> roles) {
		this.roles = roles;
	}

	

	
	public void addRole(UserRole role) {
		this.roles.add(role);
	}
	
	public void removeRole(UserRole role) {
		this.roles.remove(role);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return !this.accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !this.accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return !this.credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public void eraseCredentials() {
		this.password = null;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
}

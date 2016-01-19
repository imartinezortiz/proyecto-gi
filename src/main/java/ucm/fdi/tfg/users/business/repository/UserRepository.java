package ucm.fdi.tfg.users.business.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.users.business.entity.UserRole;

@Repository
public class UserRepository {
	
	
	 ArrayList<User> userList;
	
	public UserRepository(){
		userList = new ArrayList<User>();
		User u = new User("david","fdi");
		u.addRole(new UserRole("ROLE_ADMIN"));
		userList.add(u);
		
	}
	
	public void save(User user){
		userList.add(user);
	}
	
	public User findByUsername(String name){
		for(User u : userList){
			if (u.getName() == name)
				return u;
		}
		return null;
		
	}
	

}

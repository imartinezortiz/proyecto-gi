package ucm.fdi.tfg.users.business.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.users.business.entity.UserRole;

@Repository
public class UserRepository {
	
	
	 ArrayList<User> userList;
	 
	@PersistenceContext
	private EntityManager em;
	 
	
	/*public UserRepository(){
		userList = new ArrayList<User>();
		User u = new User("david","fdi");
		u.addRole(new UserRole("ROLE_ADMIN"));
		u.addRole(new UserRole("ROLE_USER"));
		userList.add(u);
		
		em.merge(u);
	}*/
	
	public void save(User user){
		user.addRole(new UserRole("ROLE_USER"));
		user.addRole(new UserRole("ROLE_ADMIN"));
		//userList.add(user);
		em.merge(user);
	}
	
	public User findByUsername(String username){
		/*for(User u : userList){
			if (u.getName().equals(username))
				return u;
		}
		return null */
		
		//No funciona con find
		//User u = em.find(User.class, username);
			User us = new User("hola","adios");
		  Query query = em.createQuery("SELECT u FROM User u where u.username=" +username);
		  em.persist(us);
		  User u =  (User) query.getSingleResult();
		  return u;
		
		
		
	}
	

}

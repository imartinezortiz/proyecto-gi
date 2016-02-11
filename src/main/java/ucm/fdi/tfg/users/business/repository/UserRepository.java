package ucm.fdi.tfg.users.business.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.users.business.entity.User;
import ucm.fdi.tfg.users.business.entity.UserRole;

@Repository
public class UserRepository {
	 
	@PersistenceContext
	private EntityManager em; 
	
	public void save(User user){
		user.addRole(new UserRole("ROLE_USER"));
		user.addRole(new UserRole("ROLE_ADMIN"));
		//userList.add(user);
		em.merge(user);
	}
	

	public User findByUsername(String username){
		
		  Query query = em.createQuery("SELECT u FROM User u where u.username= :username");
		  query.setParameter("username",username);
		  
		  //Como solo quiero un objeto, uso getsingleresult
		  User u = (User) query.getSingleResult();
		
		 
		 // Find solo funciona pasandole un entero (por id)
		 // User u = em.find(User.class, 1);
		  
		  return u;
	
	}
	

}

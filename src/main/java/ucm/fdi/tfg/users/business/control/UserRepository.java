package ucm.fdi.tfg.users.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.users.business.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	
	@Query("SELECT U FROM User U ,Investigador I WHERE U.id = I.id") 
	public List<User> DameDatosUserDeInvestigadores();
	

	@Query(value ="SELECT * from users where usersId in (SELECT usersId from user_roles where role=?1)", nativeQuery = true)
	public List<User> DameDatosUser(String rol);
	
	
}

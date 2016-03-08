package ucm.fdi.tfg.gestores.business.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.gestores.business.entity.Gestor;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;

//@Repository
//public interface GestorRepository extends PagingAndSortingRepository<Gestor, Long> {

//}

@Repository
public class GestorRepository {
	
	@PersistenceContext
	private EntityManager em; 

	public EntityManager getEm() {
		return em;
	}

	public void saveProyect(Proyecto proyecto) {
		em.merge(proyecto);
	}
	
	 

}

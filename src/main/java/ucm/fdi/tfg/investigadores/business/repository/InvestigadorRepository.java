package ucm.fdi.tfg.investigadores.business.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import ucm.fdi.tfg.investigadores.business.entity.Investigador;
import ucm.fdi.tfg.proyecto.business.entity.Proyecto;


@Repository
public class InvestigadorRepository {

	@PersistenceContext
	private EntityManager em; 
	
	private List<Proyecto> listaProyectos;
	
	public void save(Investigador investigador){
		em.merge(investigador);
	}

	
	
	//Aqui, cuando arreglemos el login, tendremos q modificar el método, y solo devolver los 
	//proyectos asignados al investigador que esté logueado. De omento muestra todos de prueba.
	
	public List<Proyecto> getAllProyects() {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Proyecto> q = cb.createQuery(Proyecto.class);
	
		
		q.from(Proyecto.class);
		listaProyectos = em.createQuery(q).getResultList();  

		return this.listaProyectos;
	}
	
	
}

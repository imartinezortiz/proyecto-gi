package ucm.fdi.tfg.departamentos.business.boundary;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucm.fdi.tfg.departamentos.business.control.DepartamentoRepository;
import ucm.fdi.tfg.departamentos.business.entity.Departamento;

@Service
@Transactional
public class DepartamentosManager {

	private DepartamentoRepository repository;

	@Autowired
	public DepartamentosManager(DepartamentoRepository repository) {
		this.repository = repository;
	}
	
	public List<Departamento> getAll() {
		return this.repository.findAll();
	}
}

package ucm.fdi.tfg.investigadores.business.boundary;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.investigadores.business.entity.Investigador;
import ucm.fdi.tfg.investigadores.business.repository.InvestigadorRepository;

@Service
@Transactional
public class InvestigadorManager {
	
	 InvestigadorRepository repositoryInvestigador;
	
	@Autowired
	public InvestigadorManager(InvestigadorRepository investigadores){
		this.repositoryInvestigador = investigadores;
	}
	
	public void save(Investigador inves){
		this.repositoryInvestigador.save(inves);
	}

}

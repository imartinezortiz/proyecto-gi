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
package ucm.fdi.tfg.inventarios.business.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucm.fdi.tfg.inventarios.business.control.InventariosRepository;
import ucm.fdi.tfg.inventarios.business.entity.Inventario;
import ucm.fdi.tfg.proyecto.business.boundary.ProyectosManager;
import ucm.fdi.tfg.viajes.business.entity.EstadoJustificacionEnum;

@Service
@Transactional
public class InventariosManager {
	private ProyectosManager proyectos;
	
	InventariosRepository inventarioRepository;
	
	@Autowired
	public InventariosManager(ProyectosManager proyectos, InventariosRepository inventarioRepository){
		this.proyectos = proyectos;
		this.inventarioRepository=inventarioRepository;
	}
	
	
	public void save(Inventario inventario) {	
		this.inventarioRepository.save(inventario);
	}


	public Inventario nuevoInventario(Long idProyecto, Inventario inventario) {
		inventario.setProyecto(proyectos.getProyecto(idProyecto));
	//	inventario.setNumContabilidad(proyectos.getProyecto(idProyecto).getNumContabilidad());
		return inventarioRepository.save(inventario);
	}
	
	public List<Inventario> inventariosPorProyecto(Long idProyecto){
		
		return inventarioRepository.inventariosPorProyecto(idProyecto);
	}


	public Inventario findOneInventario(Long id) {
		
		return this.inventarioRepository.findOne(id);
	}


	public Inventario editar(Inventario inventario, Long idInventario) {
		Inventario inventarioEdit = inventarioRepository.getOne(idInventario);	
		
		inventarioEdit.setAutorizacion(inventario.getAutorizacion());
		inventarioEdit.setCentro(inventario.getCentro());
		inventarioEdit.setDescripcion(inventario.getAutorizacion());
		inventarioEdit.setFecha(inventario.getFecha());
		inventarioEdit.setObservaciones(inventario.getObservaciones());
		inventarioEdit.setProyecto(inventario.getProyecto());
		inventarioEdit.setFase(inventario.getFase());
		
			
		return this.inventarioRepository.save(inventarioEdit);
		
	}


	public Inventario procesando(Long idInventario) {
		Inventario inventarioEdit = inventarioRepository.getOne(idInventario);	
				
		inventarioEdit.setFase(EstadoJustificacionEnum.PROCESANDO);		
			
		return this.inventarioRepository.save(inventarioEdit);
		
	}
	
	public Inventario procesar(Long idInventario) {
		Inventario inventarioEdit = inventarioRepository.getOne(idInventario);	
				
		inventarioEdit.setFase(EstadoJustificacionEnum.PROCESADO);		
			
		return this.inventarioRepository.save(inventarioEdit);
		
	}
	
	public List<Inventario> inventariosProcesando(){
		
		return inventarioRepository.inventariosProcesando();
	}
	
	


	public Inventario getOne(long idInventario) {
		// TODO Auto-generated method stub
		return inventarioRepository.getOne(idInventario);
	}

}

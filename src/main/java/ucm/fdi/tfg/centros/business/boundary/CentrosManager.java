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
package ucm.fdi.tfg.centros.business.boundary;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucm.fdi.tfg.centros.business.control.CentroRepository;
import ucm.fdi.tfg.centros.business.entity.Centro;

@Service
@Transactional
public class CentrosManager {

	private CentroRepository repository;

	@Autowired 
	public CentrosManager(CentroRepository repository) {
		this.repository = repository;
	}
	
	public List<Centro> getAll() {
		return repository.findAll();
	}

	public Centro nuevoCentro(Centro nuevoCentro) {
		return	repository.save(nuevoCentro);	
	}

}

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
package ucm.fdi.tfg.investigacion.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.inventarios.business.boundary.InventariosManager;
import ucm.fdi.tfg.inventarios.business.entity.Inventario;
import ucm.fdi.tfg.pagos.business.boundary.PagosManager;
import ucm.fdi.tfg.pagos.business.entity.Pago;
import ucm.fdi.tfg.viajes.business.boundary.JustificacionViajesManager;
import ucm.fdi.tfg.viajes.business.entity.JustificacionViaje;

@Controller
public class InvestigacionController {
	
	
	private InventariosManager inventarios;
	private PagosManager pagos;
	private JustificacionViajesManager viajes;
	
	@Autowired
	public InvestigacionController(InventariosManager inventarios, PagosManager pagos, JustificacionViajesManager viajes){
		this.inventarios = inventarios;
		this.pagos = pagos;
		this.viajes = viajes;
	}
	
	
	@RequestMapping(value = "/investigacion", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_GESTOR')")
	public ModelAndView listarProcesando() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		//Tabla de inventarios
		List<Inventario> inventariosProcesando = inventarios.inventariosProcesando();		
		model.put("inventariosProcesando", inventariosProcesando);				
		
		//Tabla de pagos
		List<Pago> pagosProcesando = pagos.pagosProcesando();
		model.put("pagosProcesando", pagosProcesando);		
		
		//Tabla de viajes
		List<JustificacionViaje> viajesProcesando = viajes.viajesProcesando();
		model.put("viajesProcesando", viajesProcesando);	
		
		ModelAndView view = new ModelAndView("investigacion/listarProcesando", model);
		
		return view;
		
	}
	

}

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
package ucm.fdi.tfg.centros.business.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.centros.business.boundary.CentrosManager;
import ucm.fdi.tfg.centros.business.entity.Centro;
import ucm.fdi.tfg.proyecto.business.boundary.NuevoProyectoDTO;
import ucm.fdi.tfg.proyecto.business.boundary.ProyectosManager;
import ucm.fdi.tfg.unidadesGestoras.business.boundary.UnidadesGestorasManager;
import ucm.fdi.tfg.users.business.boundary.UserManager;

@Controller
public class CentroController {
	
	private UserManager users;
	private CentrosManager centros;

	@Autowired
	public CentroController(UserManager users,CentrosManager centros ) {
		this.users = users;
		this.centros = centros;
	}
	
	
	@RequestMapping(value = "/centros", method = RequestMethod.GET)
	public ModelAndView listarCentros() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("centros", centros.getAll());
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("centros/listarCentros", model);

		return view;
	}
	
	@RequestMapping(value = "/crearCentro", method = RequestMethod.GET)
	public ModelAndView altaCentro() {

		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("gerentes", users.findAllGerentes());
		model.put("decanos", users.findAllDecanos());
		model.put("recursosHumanosList", users.findAllRRHH());
		model.put("nuevoCentro",  new Centro());
		model.put("modoTitulo", "Alta");
		model.put("modo", "crearCentro");	
		model.put("idCentro", "");
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		ModelAndView view = new ModelAndView("centros/centroForm", model);

		return view;
	}
	
	@RequestMapping(value = "/crearCentro", method = RequestMethod.POST)
	public ModelAndView añadirCentro(@ModelAttribute("nuevoCentro") @Valid Centro  nuevoCentro, BindingResult errors) {
		
		ModelAndView view = null;		
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		if (errors.hasErrors()) {
			model.put("gerentes", users.findAllGerentes());
			model.put("decanos", users.findAllDecanos());
			model.put("recursosHumanosList", users.findAllRRHH());
			model.put("nuevoCentro",  nuevoCentro);
			model.put("modoTitulo", "Alta");
			model.put("modo", "crearCentro");	
			model.put("idCentro", "");
			model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
			view = new ModelAndView("centros/centroForm", model);

		} else {
			centros.nuevoCentro(nuevoCentro);
			view = new ModelAndView("redirect:/centros");
		}
		
		return view;		
	}

}

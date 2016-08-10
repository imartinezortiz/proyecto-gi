package ucm.fdi.tfg.departamentos.business.web;

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

import ucm.fdi.tfg.departamentos.business.boundary.DepartamentosManager;
import ucm.fdi.tfg.departamentos.business.entity.Departamento;
import ucm.fdi.tfg.users.business.boundary.UserManager;

@Controller
public class DepartamentoController {
	
	private UserManager users;
	private DepartamentosManager departamentos;

	@Autowired
	public DepartamentoController(UserManager users,DepartamentosManager departamentos ) {
		this.users = users;
		this.departamentos = departamentos;
	}
	
	
	@RequestMapping(value = "/departamentos", method = RequestMethod.GET)
	public ModelAndView listarDepartamentos() {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("departamentos", departamentos.getAll());
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());

		ModelAndView view = new ModelAndView("departamentos/listarDepartamentos", model);

		return view;
	}
	
	@RequestMapping(value = "/crearDepartamento", method = RequestMethod.GET)
	public ModelAndView altaDepartamento() {

		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("directores", users.findAllDirectorDepartamentos());
		model.put("nuevoDepartamento",  new Departamento());
		model.put("modoTitulo", "Alta");
		model.put("modo", "crearDepartamento");	
		model.put("idDepartamento", "");
		model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
		
		ModelAndView view = new ModelAndView("departamentos/departamentoForm", model);

		return view;
	}
	
	@RequestMapping(value = "/crearDepartamento", method = RequestMethod.POST)
	public ModelAndView añadirCentro(@ModelAttribute("nuevoDepartamento") @Valid Departamento  nuevoDepartamento, BindingResult errors) {
		
		ModelAndView view = null;		
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		if (errors.hasErrors()) {
			model.put("nuevoDepartamento",  nuevoDepartamento);
			model.put("directores", users.findAllDirectorDepartamentos());
			model.put("modoTitulo", "Alta");
			model.put("modo", "crearDepartamento");	
			model.put("idDepartamento", "");
			model.put("usuario", SecurityContextHolder.getContext().getAuthentication().getName());
			view = new ModelAndView("departamentos/departamentoForm", model);

		} else {
			departamentos.nuevoDepartamento(nuevoDepartamento);
			view = new ModelAndView("redirect:/departamentos");
		}
		
		return view;		
	}

}

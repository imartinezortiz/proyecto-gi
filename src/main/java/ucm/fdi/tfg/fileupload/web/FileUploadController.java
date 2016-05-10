package ucm.fdi.tfg.fileupload.web;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ucm.fdi.tfg.fileupload.business.boundary.AttachmentManager;
import ucm.fdi.tfg.fileupload.business.boundary.NewFileCommand;
import ucm.fdi.tfg.fileupload.business.entity.Attachment;
import ucm.fdi.tfg.inventarios.business.boundary.InventariosManager;
import ucm.fdi.tfg.inventarios.business.entity.Inventario;

@Controller
public class FileUploadController {

	private AttachmentManager manager;
	
	private InventariosManager inventarioManager;

	@Autowired
	public FileUploadController(AttachmentManager manager,InventariosManager inventarioManager) {
		this.manager = manager;
		this.inventarioManager = inventarioManager;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "proyectos/{idProyecto}/inventario/{idInventario}/adjunto/{id}")
	public ModelAndView deleteAttachment(@PathVariable("id") long id ,  @PathVariable("idInventario") long idInventario, @PathVariable("idProyecto") long idProyecto) {
		
		Inventario inventario = inventarioManager.findOneInventario(idInventario);
		
		Iterator<Attachment> it = inventario.getAdjuntos().iterator();
		while(it.hasNext()){
			if (it.next().getId() == id)
				it.remove();
		}
		
		inventarioManager.save(inventario);
		manager.deleteAttachment(id);
		System.out.println(inventario.getAdjuntos());
		return new ModelAndView("redirect:/proyectos/"+idProyecto+"/inventario/"+idInventario+"/adjuntos");
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "proyectos/{idProyecto}/inventario/{idInventario}/adjuntos")
	public ModelAndView filesPrueba(@PathVariable("idInventario") long idInventario) {
		ModelAndView view = new ModelAndView("files");
		view.addObject("files", inventarioManager.findOneInventario(idInventario).getAdjuntos());
		view.addObject("command", new NewFileCommand());
		return view;
	}

	@RequestMapping(method = RequestMethod.POST, value = "proyectos/{idProyecto}/inventario/{idInventario}/adjuntos")
	public ModelAndView addAttachmentPrueba(@PathVariable("idInventario") long idInventario ,@PathVariable("idProyecto") long idProyecto, @ModelAttribute("command") @Validated NewFileCommand command,
			BindingResult result) throws IOException {
		
		if (result.hasErrors()) {
			ModelAndView view = new ModelAndView("files");
			view.addObject("files", manager.getAttachments());
			view.addObject("command", command);
			return view;
		}

		Inventario inv = inventarioManager.findOneInventario(idInventario);
		inv.getAdjuntos().add(manager.addAttachment(command));
		inventarioManager.save(inv);
		return new ModelAndView("redirect:/proyectos/"+idProyecto+"/inventario/"+idInventario+"/adjuntos");
	}
	
	
	
	
	
	
	
	
	
}

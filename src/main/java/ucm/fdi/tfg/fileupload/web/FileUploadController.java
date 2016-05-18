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
import ucm.fdi.tfg.pagos.business.boundary.PagosManager;
import ucm.fdi.tfg.pagos.business.entity.Pago;
import ucm.fdi.tfg.viajes.business.boundary.ViajesManager;
import ucm.fdi.tfg.viajes.business.entity.Viaje;

@Controller
public class FileUploadController {

	private AttachmentManager manager;
	
	private InventariosManager inventarioManager;
	
	private PagosManager pagoManager;
	
	private ViajesManager viajeManager;

	@Autowired
	public FileUploadController(AttachmentManager manager,InventariosManager inventarioManager, PagosManager pagoManager,ViajesManager viajeManager) {
		this.manager = manager;
		this.inventarioManager = inventarioManager;
		this.pagoManager = pagoManager;
		this.viajeManager = viajeManager;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "proyectos/{idProyecto}/inventario/{idInventario}/adjunto/{id}")
	public ModelAndView deleteAttachmentInventario(@PathVariable("id") long id ,  @PathVariable("idInventario") long idInventario, @PathVariable("idProyecto") long idProyecto) {
		
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
	public ModelAndView filesInventario(@PathVariable("idInventario") long idInventario) {
		ModelAndView view = new ModelAndView("files");
		view.addObject("files", inventarioManager.findOneInventario(idInventario).getAdjuntos());
		view.addObject("command", new NewFileCommand());
		return view;
	}

	@RequestMapping(method = RequestMethod.POST, value = "proyectos/{idProyecto}/inventario/{idInventario}/adjuntos")
	public ModelAndView addAttachmentInventario(@PathVariable("idInventario") long idInventario ,@PathVariable("idProyecto") long idProyecto, @ModelAttribute("command") @Validated NewFileCommand command,
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
	
	/*
	 * PARA PAGOS
	 * 
	 */
	
	@RequestMapping(method = RequestMethod.DELETE, value = "proyectos/{idProyecto}/pago/{idPago}/adjunto/{id}")
	public ModelAndView deleteAttachmentPago(@PathVariable("id") long id ,  @PathVariable("idPago") long idPago, @PathVariable("idProyecto") long idProyecto) {
		
		Pago pago = pagoManager.findOnePago(idPago);
		
		Iterator<Attachment> it = pago.getAdjuntos().iterator();
		while(it.hasNext()){
			if (it.next().getId() == id)
				it.remove();
		}
		
		pagoManager.save(pago);
		manager.deleteAttachment(id);
		return new ModelAndView("redirect:/proyectos/"+idProyecto+"/pago/"+idPago+"/adjuntos");
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "proyectos/{idProyecto}/pago/{idPago}/adjuntos")
	public ModelAndView filesPago(@PathVariable("idPago") long idPago) {
		ModelAndView view = new ModelAndView("files");
		view.addObject("files", pagoManager.findOnePago(idPago).getAdjuntos());
		view.addObject("command", new NewFileCommand());
		return view;
	}

	@RequestMapping(method = RequestMethod.POST, value = "proyectos/{idProyecto}/pago/{idPago}/adjuntos")
	public ModelAndView addAttachmentPago(@PathVariable("idPago") long idPago ,@PathVariable("idProyecto") long idProyecto, @ModelAttribute("command") @Validated NewFileCommand command,
			BindingResult result) throws IOException {
		
		if (result.hasErrors()) {
			ModelAndView view = new ModelAndView("files");
			view.addObject("files", manager.getAttachments());
			view.addObject("command", command);
			return view;
		}

		Pago pago = pagoManager.findOnePago(idPago);
		pago.getAdjuntos().add(manager.addAttachment(command));
		pagoManager.save(pago);
		return new ModelAndView("redirect:/proyectos/"+idProyecto+"/pago/"+idPago+"/adjuntos");
	}
	
	/*
	 * PARA VIAJES
	 * 
	 */
	
	@RequestMapping(method = RequestMethod.DELETE, value = "proyectos/{idProyecto}/viaje/{idViaje}/adjunto/{id}")
	public ModelAndView deleteAttachmentViaje(@PathVariable("id") long id ,  @PathVariable("idViaje") long idViaje, @PathVariable("idProyecto") long idProyecto) {
		
		Viaje viaje = viajeManager.findOneViaje(idViaje);
		
		Iterator<Attachment> it = viaje.getAdjuntos().iterator();
		while(it.hasNext()){
			if (it.next().getId() == id)
				it.remove();
		}
		
		viajeManager.save(viaje);
		manager.deleteAttachment(id);
		return new ModelAndView("redirect:/proyectos/"+idProyecto+"/viaje/"+idViaje+"/adjuntos");
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "proyectos/{idProyecto}/viaje/{idViaje}/adjuntos")
	public ModelAndView filesViaje(@PathVariable("idViaje") long idViaje) {
		ModelAndView view = new ModelAndView("files");
		view.addObject("files", viajeManager.findOneViaje(idViaje).getAdjuntos());
		view.addObject("command", new NewFileCommand());
		return view;
	}

	@RequestMapping(method = RequestMethod.POST, value = "proyectos/{idProyecto}/viaje/{idViaje}/adjuntos")
	public ModelAndView addAttachmentViaje(@PathVariable("idViaje") long idViaje ,@PathVariable("idProyecto") long idProyecto, @ModelAttribute("command") @Validated NewFileCommand command,
			BindingResult result) throws IOException {
		
		if (result.hasErrors()) {
			ModelAndView view = new ModelAndView("files");
			view.addObject("files", manager.getAttachments());
			view.addObject("command", command);
			return view;
		}

		Viaje viaje = viajeManager.findOneViaje(idViaje);
		viaje.getAdjuntos().add(manager.addAttachment(command));
		viajeManager.save(viaje);
		return new ModelAndView("redirect:/proyectos/"+idProyecto+"/viaje/"+idViaje+"/adjuntos");
	}
	
	
	@RequestMapping("/proyectos/{idProyecto}/avisoAdjuntos")
	public String avisoAdjuntos() {
			return "avisoAdjuntos";
	}
				
	
	
		
	
	
	
	
	
	
	
	
	
}

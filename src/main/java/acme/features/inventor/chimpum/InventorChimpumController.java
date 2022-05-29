package acme.features.inventor.chimpum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chimpums.Chimpum;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorChimpumController extends AbstractController<Inventor, Chimpum> {
	
	@Autowired
	InventorChimpumListService listService;
	
	@Autowired
	InventorChimpumShowService showService;
	
	@Autowired
	InventorChimpumCreateService createService;
	
	@Autowired
	InventorChimpumDeleteService deleteService;
	
	@Autowired
	InventorChimpumUpdateService updateService;

	@PostConstruct
	protected void initialise() {
		
		super.addCommand("list", "list",this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		
		
		
	}
}

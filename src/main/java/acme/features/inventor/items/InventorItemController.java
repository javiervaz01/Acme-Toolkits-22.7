package acme.features.inventor.items;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.items.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item>{

	@Autowired
	protected InventorItemListOwnService listOwnService;
	
	@Autowired
	protected InventorItemShowService showService;
	
	@Autowired
	protected InventorItemListByToolkitService listByToolkitService;
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("show", this.showService);
		
		super.addCommand("list-own","list", this.listOwnService);
		super.addCommand("list-by-toolkit","list", this.listByToolkitService);
	}
}

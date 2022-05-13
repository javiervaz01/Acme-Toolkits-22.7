package acme.features.any.items;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.items.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;


@Controller
public class AnyItemController extends AbstractController<Any, Item>{

	@Autowired
	protected AnyItemListService listAllService;
	
	@Autowired
	protected AnyItemListByToolkitService listByToolkitService;
	
	@Autowired
	protected AnyItemShowService showService;
	
	@PostConstruct
	protected void initialize() {
		super.addCommand("show", this.showService);
		
		super.addCommand("list-all","list", this.listAllService);
		super.addCommand("list-by-toolkit","list", this.listByToolkitService);
	}
}

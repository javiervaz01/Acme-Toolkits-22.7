package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.items.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;


@Controller
public class AnyItemController extends AbstractController<Any, Item> {

	@Autowired
	protected AnyItemListService listService;
	
	@Autowired
	protected AnyItemShowService showService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
	}
}

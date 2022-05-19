package acme.features.any.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.quantities.Quantity;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyQuantityController extends AbstractController<Any, Quantity> {

	@Autowired
	protected AnyQuantityListService listService;

	@Autowired
	protected AnyQuantityShowService showService;

	@PostConstruct
	protected void initialize() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}
}
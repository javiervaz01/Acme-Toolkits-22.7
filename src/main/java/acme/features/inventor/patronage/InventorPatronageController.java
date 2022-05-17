package acme.features.inventor.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronages.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronageController extends AbstractController<Inventor, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageListService listService;

	@Autowired
	protected InventorPatronageAcceptService acceptService;

	@Autowired
	protected InventorPatronageDenyService denyService;

	@Autowired
	protected InventorPatronageListMineProposedService listProposedService;

	@Autowired
	protected InventorPatronageShowService showService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);

		super.addCommand("list-proposed", "list", this.listProposedService);
		super.addCommand("accept", "update", this.acceptService);
		super.addCommand("deny", "update", this.denyService);
	}
}
package acme.features.inventor.patronagereport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronagereports.PatronageReport;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronageReportController extends AbstractController<Inventor, PatronageReport>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected InventorPatronageReportListService listService;
	
	@Autowired
	protected InventorPatronageReportListByPatronageService listByPatronageService;

	@Autowired
	protected InventorPatronageReportShowService showService;
	
	@Autowired
	protected InventorPatronageReportCreateService createService;
	
	// Constructors -----------------------------------------------------------
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		
		super.addCommand("list-by-patronage", "list", this.listByPatronageService);
		super.addCommand("create", this.createService);
	}
	
	
}

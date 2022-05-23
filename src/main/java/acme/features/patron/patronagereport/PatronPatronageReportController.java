package acme.features.patron.patronagereport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronagereports.PatronageReport;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronPatronageReportController extends AbstractController<Patron, PatronageReport>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageReportListService listService;

	@Autowired
	protected PatronPatronageReportListByPatronageService listByPatronageService;
	
	@Autowired
	protected PatronPatronageReportShowService showService;
	
	@Autowired
	protected PatronPatronageReportCreateService createService;


	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		
		super.addCommand("list-by-patronage", "list", this.listByPatronageService);
		
	}
}
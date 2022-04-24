package acme.features.patron.patronagereports;

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
	protected PatronPatronageReportShowService showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);

		super.addCommand("list", "list", this.listService);
	}


}
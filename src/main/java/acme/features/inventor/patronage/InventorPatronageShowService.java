package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronagereports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.features.inventor.patronagereports.InventorPatronageReportRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageRepository repository;
	
	@Autowired
	protected InventorPatronageReportRepository repositoryReport;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		// Check that the inventor trying to see the patronage is the one linked to it
		
		final int patronId = request.getPrincipal().getActiveRoleId();
		final int patronageId = request.getModel().getInteger("id");
		final int patronageInventorId = this.repository.findOnePatronageById(patronageId).getInventor().getId();
		
		return  patronId == patronageInventorId; 
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findOnePatronageById(id);
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate", "startDate", "endDate", "info", "patron.identity.name", "patron.identity.surname", "patron.identity.email", "patron.company", "patron.statement", "patron.info");
	
		final Collection<PatronageReport> reports = this.repository.findPatronageReportsByPatronageCode(entity.getCode());
		model.setAttribute("reports", reports);
	}

}

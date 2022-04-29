package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.features.patron.patronagereport.PatronPatronageReportRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected PatronPatronageReportRepository repositoryReport;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request !=null;
		
		final int patronId = request.getPrincipal().getActiveRoleId();
		final int patronageId = request.getModel().getInteger("id");
		final int patronageInventorId = this.repository.findOnePatronageById(patronageId).getPatron().getId();
		
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
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate", "startDate", "endDate", "info", "inventor.identity.name", "inventor.identity.surname", "inventor.identity.email", "inventor.company", "inventor.statement", "inventor.info");
		
		final int masterId = request.getModel().getInteger("id");
		model.setAttribute("masterId", masterId);
	}
}

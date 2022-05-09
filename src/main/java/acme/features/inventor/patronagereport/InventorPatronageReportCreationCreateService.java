package acme.features.inventor.patronagereport;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronagereports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreationCreateService implements AbstractCreateService<Inventor, PatronageReport>{

	@Autowired
	InventorPatronageReportCreationRepository repository;
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		
		return true;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "sequenceNumber","creationTime","memorandum","info");
		
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber","creationTime","memorandum","info");
		
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		Date creationTime;

		creationTime = new Date(System.currentTimeMillis() - 1);

		result = new PatronageReport();
		result.setCreationTime(creationTime);


		return result;
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;
		Patronage patronage;
		Integer masterId;
		Date creationTime;
		masterId = request.getModel().getInteger("masterId");
		patronage = this.repository.findPatronageById(masterId);
		
		entity.setPatronage(patronage);

		creationTime = new Date(System.currentTimeMillis() - 1);
		entity.setCreationTime(creationTime);
		this.repository.save(entity);
		
	}

}

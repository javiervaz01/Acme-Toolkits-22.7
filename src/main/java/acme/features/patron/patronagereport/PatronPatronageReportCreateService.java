
package acme.features.patron.patronagereport;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronagereports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportCreateService implements AbstractCreateService<Patron, PatronageReport> {

	@Autowired
	PatronPatronageReportRepository repository;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		final int patronId = request.getPrincipal().getActiveRoleId();
		final int patronageId = request.getModel().getInteger("masterId");
		final Patronage patronage = this.repository.findOnePatronageById(patronageId);
		final int patronagePatronId = patronage.getPatron().getId();

		return patronId == patronagePatronId && !patronage.isDraftMode();
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "memorandum", "info");

		Integer masterId;
		Patronage patronage;

		masterId = request.getModel().getInteger("masterId");
		patronage = this.repository.findOnePatronageById(masterId);
		entity.setPatronage(patronage);
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "memorandum", "info");

		Integer masterId;

		masterId = request.getModel().getInteger("masterId");

		model.setAttribute("masterId", masterId);
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		Integer masterId;
		Patronage patronage;
		int numberOfReports;
		String sequenceNumber;
		Date creationTime;

		masterId = request.getModel().getInteger("masterId");
		patronage = this.repository.findOnePatronageById(masterId);
		numberOfReports = this.repository.countPatronageReportsInPatronageById(patronage.getId());
		sequenceNumber = patronage.getCode() + String.format(":%04d", numberOfReports + 1);
		creationTime = new Date(System.currentTimeMillis() - 1);

		result = new PatronageReport();
		result.setCreationTime(creationTime);
		result.setSequenceNumber(sequenceNumber);
		result.setPatronage(patronage);

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

		// Update the creationTime to make it the moment the user actually pressed the
		// "Create" button. It had to be set on the instantiate method(), otherwise the
		// submit button would do nothing, even if we set the date here too. There are
		// no relevant debug messages either
		final Date creationTime = new Date(System.currentTimeMillis() - 1);
		entity.setCreationTime(creationTime);

		this.repository.save(entity);
	}
}

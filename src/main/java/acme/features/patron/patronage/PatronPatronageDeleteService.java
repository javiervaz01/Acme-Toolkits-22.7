package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronagereports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Patron;

@Service
public class PatronPatronageDeleteService implements AbstractDeleteService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		int masterId;
		final Patronage patronage;
		final Patron patron;

		masterId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(masterId);
		patron = patronage.getPatron();
		result = patronage.isDraftMode() && request.isPrincipal(patron);

		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "legalStuff", "budget", "startDate", "endDate", "info");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate", "startDate", "endDate", "info","draftMode");
		
		final int masterId = request.getModel().getInteger("id");
		model.setAttribute("masterId", masterId);
		
		final String inventorName = entity.getInventor().getIdentity().getName();
		final String inventorSurname = entity.getInventor().getIdentity().getSurname();
		final String inventorEmail = entity.getInventor().getIdentity().getEmail();
		final String inventorCompany = entity.getInventor().getCompany();
		final String inventorStatement = entity.getInventor().getStatement();
		final String inventorInfo = entity.getInventor().getInfo();
		
		model.setAttribute("inventorName", inventorName);
		model.setAttribute("inventorSurname", inventorSurname);
		model.setAttribute("inventorEmail", inventorEmail);
		model.setAttribute("inventorCompany", inventorCompany);
		model.setAttribute("inventorStatement", inventorStatement);
		model.setAttribute("inventorInfo", inventorInfo);
	}
	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);

		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		final Collection<PatronageReport> reports = this.repository.findPatronageReportsByPatronageCode(entity.getCode());
		
		for (final PatronageReport report : reports) {
			this.repository.delete(report);
		}
		
		this.repository.delete(entity);
	}

}

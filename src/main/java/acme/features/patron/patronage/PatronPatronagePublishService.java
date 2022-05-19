package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronagePublishService implements AbstractUpdateService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		final int patronageId;
		final Patronage patronage;
		final Patron patron;

		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(patronageId);
		patron = patronage.getPatron();
		result = patronage.isDraftMode() && request.isPrincipal(patron);

		return result;
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
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "legalStuff", "budget", "startDate", "endDate", "info");
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate", "startDate", "endDate",
				"info", "draftMode");

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
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		entity.setDraftMode(false);
		this.repository.save(entity);
	}

}

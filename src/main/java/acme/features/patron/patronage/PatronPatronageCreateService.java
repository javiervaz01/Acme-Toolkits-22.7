package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		Patron patron;
		Date creationDate;
		int inventorId;
		Inventor inventor;

		inventorId = request.getModel().getInteger("inventorId");
		inventor = this.repository.findOneInventorById(inventorId);
		creationDate = new Date(System.currentTimeMillis() - 1);
		patron = this.repository.findOnePatronById(request.getPrincipal().getActiveRoleId());

		result = new Patronage();
		result.setDraftMode(true);
		result.setStatus(Status.PROPOSED);
		result.setPatron(patron);
		result.setCreationDate(creationDate);
		result.setInventor(inventor);

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

		if (!errors.hasErrors("startDate")) {
			// The patronage must start, at least, one month after its creation
			Calendar creationDate;
			Calendar startDate;

			creationDate = Calendar.getInstance();
			creationDate.setTime(entity.getCreationDate());

			startDate = Calendar.getInstance();
			startDate.setTime(entity.getStartDate());

			startDate.add(Calendar.MONTH, -1);

			errors.state(request, startDate.after(creationDate), "startDate",
					"patron.patronage.form.error.start-date-too-early");
		}

		if (!errors.hasErrors("startDate") && !errors.hasErrors("endDate")) {
			// The patronage must be, at least, one month long
			Calendar startDate;
			Calendar endDate;

			startDate = Calendar.getInstance();
			startDate.setTime(entity.getStartDate());

			endDate = Calendar.getInstance();
			endDate.setTime(entity.getEndDate());

			endDate.add(Calendar.MONTH, -1);

			errors.state(request, endDate.after(startDate), "endDate",
					"patron.patronage.form.error.end-date-too-early");
		}

		if (!errors.hasErrors("code")) {
			Patronage existing;

			existing = this.repository.findOnePatronageByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code",
					"patron.patronage.form.error.duplicated");
		}

		if (!errors.hasErrors("budget")) {
			Double budget;
			String currency;

			budget = entity.getBudget().getAmount();
			currency = entity.getBudget().getCurrency();

			errors.state(request, this.repository.isAcceptedCurrency(currency), "budget",
					"patron.patronage.form.error.not-accepted-currency");
			errors.state(request, budget > 0.0, "budget", "patron.patronage.form.error.negative-budget");
		}
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate", "startDate", "endDate",
				"info", "draftMode");

		final Integer inventorId = request.getModel().getInteger("inventorId");
		model.setAttribute("inventorId", inventorId);
	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}

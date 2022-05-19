package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageRepository repository;

	@Autowired
	protected ExchangeService exchangeService;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		final int patronId = request.getPrincipal().getActiveRoleId();
		final int patronageId = request.getModel().getInteger("id");
		final Patronage patronage = this.repository.findOnePatronageById(patronageId);
		final int patronageInventorId = patronage.getInventor().getId();

		return patronId == patronageInventorId && !patronage.isDraftMode();
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		int id;

		id = request.getModel().getInteger("id");

		return this.repository.findOnePatronageById(id);
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate", "startDate", "endDate",
				"info");

		final int masterId = request.getModel().getInteger("id");
		model.setAttribute("masterId", masterId);

		final String patronName = entity.getPatron().getIdentity().getName();
		final String patronSurname = entity.getPatron().getIdentity().getSurname();
		final String patronEmail = entity.getPatron().getIdentity().getEmail();
		final String patronCompany = entity.getPatron().getCompany();
		final String patronStatement = entity.getPatron().getStatement();
		final String patronInfo = entity.getPatron().getInfo();
		final Money exchange = this.exchangeService.getExchange(entity.getBudget());

		model.setAttribute("patronName", patronName);
		model.setAttribute("patronSurname", patronSurname);
		model.setAttribute("patronEmail", patronEmail);
		model.setAttribute("patronCompany", patronCompany);
		model.setAttribute("patronStatement", patronStatement);
		model.setAttribute("patronInfo", patronInfo);
		model.setAttribute("exchange", exchange);
	}
}

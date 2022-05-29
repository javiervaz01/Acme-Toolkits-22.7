package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.chimpums.Chimpum;
import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, Chimpum> {

	@Autowired
	InventorChimpumRepository repository;

	@Autowired
	protected ExchangeService exchangeService;

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		int inventorId;
		int chimpumId;
		Item item;

		chimpumId = request.getModel().getInteger("id");
		item = this.repository.findOneItemByChimpumId(chimpumId);
		inventorId = request.getPrincipal().getActiveRoleId();

		return item.getInventor().getId() == inventorId;
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;

		return this.repository.findOne(request.getModel().getInteger("id"));
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "creationDate", "title", "description", "startDate", "endDate", "budget",
				"info");

		final Item item = this.repository.findOneItemByChimpumId(entity.getId());
		model.setAttribute("itemName", item.getName());
		model.setAttribute("itemId", item.getId());

		final Money exchange = this.exchangeService.getExchange(entity.getBudget());
		model.setAttribute("exchange", exchange);
	}

}

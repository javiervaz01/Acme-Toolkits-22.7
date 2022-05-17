package acme.features.any.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyItemShowService implements AbstractShowService<Any, Item> {

	@Autowired
	protected AnyItemRepository repository;

	@Autowired
	protected ExchangeService exchangeService;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		int id;
		Item item;

		id = request.getModel().getInteger("id");
		item = this.repository.findOneItemById(id);

		return !item.isDraftMode();
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		int id;

		id = request.getModel().getInteger("id");

		return this.repository.findOneItemById(id);
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "info", "type");

		final Money exchange = this.exchangeService.getExchange(entity.getRetailPrice());
		model.setAttribute("exchange", exchange);
	}
}

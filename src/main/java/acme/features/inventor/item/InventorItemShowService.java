package acme.features.inventor.item;

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
public class InventorItemShowService implements AbstractShowService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository repository;

	@Autowired
	protected ExchangeService exchangeService;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int id;
		Item item;

		id = request.getModel().getInteger("id");
		item = this.repository.findItemById(id);

		result = (!item.isDraftMode() || request.isPrincipal(item.getInventor()));

		return result;
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		int id;
		Item result;

		id = request.getModel().getInteger("id");
		result = this.repository.findItemById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "info", "type",
				"draftMode");

		int id;
		Money exchange;
		Chimpum chimpum;

		id = request.getModel().getInteger("id");
		exchange = this.exchangeService.getExchange(entity.getRetailPrice());
		chimpum = this.repository.findOneChimpumByItemId(id);

		model.setAttribute("id", id);
		model.setAttribute("exchange", exchange);
		model.setAttribute("chimpum", chimpum);

	}
}

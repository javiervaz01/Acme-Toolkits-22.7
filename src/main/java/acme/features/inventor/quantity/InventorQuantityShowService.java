package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.items.Item;
import acme.entities.quantities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorQuantityShowService implements AbstractShowService<Inventor, Quantity> {

	@Autowired
	protected InventorQuantityRepository repository;

	@Autowired
	protected ExchangeService exchangeService;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;
		int id;
		Item item;

		id = request.getModel().getInteger("id");

		item = this.repository.findOneQuantityById(id).getItem();

		result = item != null && (!item.isDraftMode() || request.isPrincipal(item.getInventor()));

		return result;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;

		int id;
		Quantity result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "toolkit.title", "number");

		int toolkitId;
		Item selectedItem;
		String toolkitCurrency;
		Collection<Item> items;
		Collection<Item> itemsInToolkit;

		selectedItem = entity.getItem();
		toolkitId = entity.getToolkit().getId();
		itemsInToolkit = this.repository.findManyItemByToolkitId(toolkitId);
 		
 		if (itemsInToolkit.isEmpty()) {
			items = this.repository.findAllItems();
		} else {
			toolkitCurrency = itemsInToolkit.iterator().next().getRetailPrice().getCurrency();
			items = this.repository.findItemsByCurrency(toolkitCurrency);
		}
		model.setAttribute("items", items);
		model.setAttribute("selected", selectedItem);
		model.setAttribute("draftMode", entity.getToolkit().isDraftMode());
	}
}

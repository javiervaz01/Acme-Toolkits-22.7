package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.items.ItemType;
import acme.entities.quantities.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityUpdateService implements AbstractUpdateService<Inventor, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;
		int id;
		Toolkit toolkit;

		id = request.getModel().getInteger("id");
		toolkit = this.repository.findOneQuantityById(id).getToolkit();
		result = (toolkit != null && toolkit.isDraftMode() && request.isPrincipal(toolkit.getInventor()));

		return result;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		Quantity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(id);

		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "toolkit.title", "number");

		Model model;
		Item selectedItem;

		model = request.getModel();
		selectedItem = this.repository.findOneItemByCode(model.getString("items"));

		entity.setItem(selectedItem);
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Model model;
		Integer quantity;
		Item selectedItem;

		model = request.getModel();
		quantity = model.getInteger("number");
		selectedItem = entity.getItem();

		model.setAttribute("selected", selectedItem);

		if (!errors.hasErrors("number")) {
			errors.state(request, selectedItem.getType().equals(ItemType.COMPONENT) || quantity == 1, "number",
					"inventor.quantity.form.error.repeated-tool");
		}

		if (!errors.hasErrors("items")) {
			int toolkitId;
			Collection<Item> itemsInToolkit;
			String newItemCurrency;
			Item previousItem;

			newItemCurrency = selectedItem.getRetailPrice().getCurrency();

			// Take the currency of an existing item in the toolkit. Can be null if the
			// toolkit is empty
			toolkitId = entity.getToolkit().getId();
			itemsInToolkit = this.repository.findManyItemByToolkitId(toolkitId);
			previousItem = this.repository.findOneItemByQuantityId(entity.getId());

			errors.state(request,
					itemsInToolkit.isEmpty()
							|| itemsInToolkit.iterator().next().getRetailPrice().getCurrency().equals(newItemCurrency),
					"retailPrice", "inventor.quantity.form.error.wrong-currency");

			errors.state(request, !itemsInToolkit.contains(selectedItem) || selectedItem.equals(previousItem), "*",
					"inventor.quantity.form.error.repeated-item");

			errors.state(request, !selectedItem.isDraftMode(), "*", "inventor.quantity.form.error.draft-mode-item"); // Protection
																														// against
																														// hacking
		}
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "toolkit.title", "number");

		int toolkitId;
		Collection<Item> itemsInToolkit;
		Collection<Item> items;
		Item selectedItem;
		String toolkitCurrency;

		selectedItem = entity.getItem();
		toolkitId = entity.getToolkit().getId();
		itemsInToolkit = this.repository.findManyItemByToolkitId(toolkitId);
 		
 		if (itemsInToolkit.isEmpty()) {
			items = this.repository.findAllItems();
		} else {
			toolkitCurrency = itemsInToolkit.iterator().next().getRetailPrice().getCurrency();
			items = this.repository.findItemsByCurrency(toolkitCurrency);
		}
 		
		// Moreover, remove from the list the repeated items, which are not
		// allowed either in the toolkit
		items.removeAll(itemsInToolkit);

		// But keep the one that is being edited
		items.add(selectedItem);
		
		model.setAttribute("items", items);
		model.setAttribute("selected", selectedItem);
		model.setAttribute("draftMode", entity.getToolkit().isDraftMode());
	}

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
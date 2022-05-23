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
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityCreateService implements AbstractCreateService<Inventor, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;
		int toolkitId;
		Toolkit toolkit;

		toolkitId = request.getModel().getInteger("toolkitId");
		toolkit = this.repository.findOneToolkitById(toolkitId);
		result = (toolkit != null && toolkit.isDraftMode() && request.isPrincipal(toolkit.getInventor()));

		return result;
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;

		Quantity result;
		int toolkitId;
		Toolkit toolkit;

		toolkitId = request.getModel().getInteger("toolkitId");
		toolkit = this.repository.findOneToolkitById(toolkitId);

		result = new Quantity();
		result.setToolkit(toolkit);

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

		if (!errors.hasErrors("number")) {
			errors.state(request, selectedItem.getType().equals(ItemType.COMPONENT) || quantity == 1, "number",
					"inventor.quantity.form.error.repeated-tool");
		}

		if (!errors.hasErrors("items")) {
			int toolkitId;
			Collection<Item> itemsInToolkit;
			String newItemCurrency;

			newItemCurrency = selectedItem.getRetailPrice().getCurrency();

			// Take the currency of an existing item in the toolkit. Can be null if the
			// toolkit is empty
			toolkitId = request.getModel().getInteger("toolkitId");
			itemsInToolkit = this.repository.findManyItemByToolkitId(toolkitId);

			errors.state(request,
					itemsInToolkit.isEmpty()
							|| itemsInToolkit.iterator().next().getRetailPrice().getCurrency().equals(newItemCurrency),
					"*", "inventor.quantity.form.error.wrong-currency");

			errors.state(request, !itemsInToolkit.contains(selectedItem), "*",
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
		toolkitId = request.getModel().getInteger("toolkitId");
		itemsInToolkit = this.repository.findManyItemByToolkitId(toolkitId);

		// If the toolkit is empty, show all items. If it already has items,
		// only show the items with the same currency as the rest of the
		// toolkit's items (custom constraint above)
 		if (itemsInToolkit.isEmpty()) {
			items = this.repository.findAllItems();
		} else {
			toolkitCurrency = itemsInToolkit.iterator().next().getRetailPrice().getCurrency();
			items = this.repository.findItemsByCurrency(toolkitCurrency);
		}
		
		// Moreover, remove from the list the repeated items, which are not
		// allowed either in the toolkit
		items.removeAll(itemsInToolkit);

		model.setAttribute("items", items);
		model.setAttribute("selected", selectedItem);
		model.setAttribute("toolkitId", toolkitId);
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}

package acme.features.inventor.item;

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
public class InventorItemUpdateService implements AbstractUpdateService<Inventor, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;

	@Autowired
	protected InventorQuantityRepository quantityRepository;


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int masterId;
		Toolkit toolkit;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		result = (toolkit != null && toolkit.isDraftMode() && request.isPrincipal(toolkit.getInventor()));

		return result;
	}

	@Override
	public Item findOne(final Request<Item> request) {

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findItemById(id);

		return result;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "info", "type", "quantity");
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Item existing;
			Integer id;
			
			existing = this.repository.findOneItemByCode(entity.getCode());
			id = request.getModel().getInteger("id");
			
			errors.state(request, existing == null || existing.getId() == id, "code", "inventor.item.form.error.duplicated");
		}

		if (!errors.hasErrors("quantity")) {
			Integer quantity;

			quantity = request.getModel().getInteger("quantity");
			
			errors.state(request, quantity != null && quantity >= 1, "quantity", "inventor.item.form.error.no-quantity");
			errors.state(request, entity.getType().equals(ItemType.COMPONENT) || quantity == 1, "quantity", "inventor.item.form.error.repeated-tool");
		}
		
		if (!errors.hasErrors("retailPrice")) {
			int masterId;
			Collection<Item> itemsInToolkit;
			String newItemCurrency;

			newItemCurrency = entity.getRetailPrice().getCurrency();
			
			// Take the currency of an existing item in the toolkit. Can be null if the toolkit is empty
			masterId = request.getModel().getInteger("masterId");
			itemsInToolkit = this.repository.findItemsByToolkitId(masterId);
			
			errors.state(request, !itemsInToolkit.isEmpty() && itemsInToolkit.iterator().next().getRetailPrice().getCurrency().equals(newItemCurrency), "retailPrice", "inventor.item.form.error.wrong-currency");
		}
	}


	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "info", "type");
		model.setAttribute("masterId", request.getModel().getInteger("masterId"));
	}

	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		// TODO If the system currency changes, there will be problems (new items will have
		// a different currency). The solution is to force every subsequent item to be of the
		// the same currency of the first one added (or transform it on save). The display
		// currency should be set via the user's locale by means of a Formatter (acme includes
		// LocalisedMoneyFormatter)
		
		this.repository.save(entity);

		// Create the Quantity entity on bind
		final Model model = request.getModel();
		final Integer id = entity.getId();
		
		final Quantity quantity = this.repository.findQuantityByItemId(id);
		quantity.setNumber((int)model.getAttribute("quantity"));
		
		this.quantityRepository.save(quantity);
	}
}

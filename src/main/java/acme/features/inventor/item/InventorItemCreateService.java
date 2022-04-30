
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
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorItemCreateService implements AbstractCreateService<Inventor, Item> {

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
	public Item instantiate(final Request<Item> request) {
		assert request != null;

		Item result;
		int masterId;
		Toolkit toolkit;
		Inventor inventor;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		inventor = toolkit.getInventor();

		result = new Item();
		result.setInventor(inventor);

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

			existing = this.repository.findOneItemByCode(entity.getCode());
			errors.state(request, existing == null, "code", "inventor.item.form.error.duplicated");
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
			
			errors.state(request, itemsInToolkit.isEmpty() || itemsInToolkit.iterator().next().getRetailPrice().getCurrency().equals(newItemCurrency), "retailPrice", "inventor.item.form.error.wrong-currency");
		}
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "info", "type", "quantity");
		
		model.setAttribute("masterId", request.getModel().getInteger("masterId"));
		model.setAttribute("draftMode", true);
	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
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
		final Integer masterId = request.getModel().getInteger("masterId");
		final Toolkit toolkit = this.repository.findOneToolkitById(masterId);
		
		final Quantity quantity = new Quantity();
		quantity.setItem(entity);
		quantity.setToolkit(toolkit);
		quantity.setNumber(model.getInteger("quantity"));
		
		this.quantityRepository.save(quantity);
	}
}

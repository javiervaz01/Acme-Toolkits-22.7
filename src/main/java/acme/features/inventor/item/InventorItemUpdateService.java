
package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.components.SpamService;
import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorItemUpdateService implements AbstractUpdateService<Inventor, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;

	@Autowired
	protected ExchangeService exchangeService;

	@Autowired
	protected SpamService spamService;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int id;
		Item item;

		id = request.getModel().getInteger("id");
		item = this.repository.findItemById(id);

		result = (item.isDraftMode() && request.isPrincipal(item.getInventor()));

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

		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "info", "type");
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

			errors.state(request, existing == null || existing.getId() == id, "code",
					"inventor.item.form.error.duplicated");
		}

		if (!errors.hasErrors("retailPrice")) {
			Double retailPrice;
			String currency;

			retailPrice = entity.getRetailPrice().getAmount();
			currency = entity.getRetailPrice().getCurrency();

			errors.state(request, this.repository.isAcceptedCurrency(currency), "retailPrice",
					"inventor.item.form.error.not-accepted-currency");
			errors.state(request, retailPrice > 0.0, "retailPrice", "inventor.item.form.error.negative-price");
		}
		if (!errors.hasErrors("technology")) {
			errors.state(request, !this.spamService.isSpam(entity.getTechnology()), "technology",
					"inventor.item.form.error.spam");
		}
		if (!errors.hasErrors("description")) {
			errors.state(request, !this.spamService.isSpam(entity.getDescription()), "description",
					"inventor.item.form.error.spam");
		}
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "info", "type",
				"draftMode");

		final Money exchange = this.exchangeService.getExchange(entity.getRetailPrice());
		model.setAttribute("exchange", exchange);
	}

	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}

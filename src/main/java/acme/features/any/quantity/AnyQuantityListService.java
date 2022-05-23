package acme.features.any.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.items.Item;
import acme.entities.quantities.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.helpers.CollectionHelper;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyQuantityListService implements AbstractListService<Any, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyQuantityRepository repository;

	@Autowired
	protected ExchangeService exchangeService;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		int id;
		Toolkit requested;

		id = request.getModel().getInteger("toolkitId");
		requested = this.repository.findOneToolkitById(id);

		return !requested.isDraftMode();
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;

		int id;
		Collection<Quantity> result;

		id = request.getModel().getInteger("toolkitId");
		result = this.repository.findManyQuantityByToolkitId(id);

		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entities,
			final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;

		int id;

		id = request.getModel().getInteger("toolkitId");

		model.setAttribute("toolkitId", id);
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "number");

		Money exchange;
		Item item;

		item = entity.getItem();
		model.setAttribute("name", item.getName());
		model.setAttribute("toolkitTitle", entity.getToolkit().getTitle());
		model.setAttribute("code", item.getCode());
		model.setAttribute("technology", item.getTechnology());
		model.setAttribute("description", item.getDescription());
		model.setAttribute("retailPrice", item.getRetailPrice());
		model.setAttribute("info", item.getInfo());
		model.setAttribute("type", item.getType());

		exchange = this.exchangeService.getExchange(entity.getItem().getRetailPrice());

		model.setAttribute("exchange", exchange);
	}
}

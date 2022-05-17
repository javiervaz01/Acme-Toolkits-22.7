package acme.features.any.quantity;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.items.Item;
import acme.entities.quantities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyQuantityShowService implements AbstractShowService<Any, Quantity> {

	@Autowired
	protected AnyQuantityRepository repository;

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

		result = item != null && !item.isDraftMode();

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

		Item item;
		Money exchange;

		item = entity.getItem();
		model.setAttribute("items", Arrays.asList(item));
		model.setAttribute("draftMode", entity.getToolkit().isDraftMode());

		exchange = this.exchangeService.getExchange(item.getRetailPrice());
		model.setAttribute("exchange", exchange);
	}
}

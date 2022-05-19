package acme.features.any.quantity;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.items.Item;
import acme.entities.quantities.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
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
		Toolkit toolkit;

		id = request.getModel().getInteger("id");

		toolkit = this.repository.findOneQuantityById(id).getToolkit();

		result = toolkit != null && !toolkit.isDraftMode();

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

		request.unbind(entity, model, "number");

		Item item;

		model.setAttribute("toolkitTitle", entity.getToolkit().getTitle());

		// The items dropdown is inmutable in /any/quantity/show so we don't bring the
		// list of all the items since it would be both inefficient and useless here
		item = entity.getItem();
		model.setAttribute("items", Arrays.asList(item));

	}
}

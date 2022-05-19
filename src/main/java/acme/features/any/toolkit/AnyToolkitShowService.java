package acme.features.any.toolkit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;



@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit>{

	@Autowired
	protected AnyToolkitRepository repository;

	@Autowired
	protected ExchangeService exchangeRepository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		int id;
		Toolkit toolkit;
		
		id = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(id);
		
		return !toolkit.isDraftMode();
	}
	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
	
		assert request != null;

		int id;
		
		id = request.getModel().getInteger("id");

		return this.repository.findToolkitById(id);
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final int id = request.getModel().getInteger("id");
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "draftMode");

		String currency = this.repository.findRetailPriceCurrencyByToolkitId(id);
		double amount;

		if (currency == null) { // If there are no items in the toolkit
			amount = 0.0;
			currency = ""; // To avoid showing "null" in the view
		} else {
			amount = this.repository.findRetailPriceAmountByToolkitId(id);
		}

		final Money retailPrice = new Money();
		retailPrice.setAmount(amount);
		retailPrice.setCurrency(currency);

		model.setAttribute("retailPrice", retailPrice);

		final Money exchange = this.exchangeRepository.getExchange(retailPrice);
		model.setAttribute("exchange", exchange);
	}
}
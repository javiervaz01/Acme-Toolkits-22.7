package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.features.inventor.moneyExchange.InventorMoneyExchangePerform;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		// Check that the inventor trying to see the toolkit is the one linked to it
		
		final int inventorId = request.getPrincipal().getActiveRoleId();
		
		final Collection<Toolkit> toolkits = this.repository.findToolkitsByInventorId(inventorId);
		
		final Toolkit requested = this.findOne(request);
		
		return toolkits.contains(requested);
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findOneToolkitById(id);
	}
	public Money getInternationalizedMoney(final int id) {
		final Collection<String> currencies = this.repository.currencies();
		Double finalAmount=0.;
		for(final String currency: currencies) {
			
			final Double amount = this.repository.findRetailPriceByToolkitId(id,currency);
			final Money retailPrice = new Money();
			retailPrice.setAmount(amount);	retailPrice.setCurrency(currency);
			final Money price = InventorMoneyExchangePerform.computeMoneyExchange(retailPrice, "EUR");
			finalAmount+=price.getAmount();
		}
		final Money res = new Money();
		res.setAmount(finalAmount);	res.setCurrency("EUR");
		return res;
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int id = request.getModel().getInteger("id");
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link");
		
		final Money retailPrice = this.getInternationalizedMoney(id);
		
		model.setAttribute("retailPrice", retailPrice);
		
	}

}

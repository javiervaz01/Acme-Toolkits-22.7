package acme.features.any.toolkits;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
	
		assert request != null;

		final int id = request.getModel().getInteger("id");

		return this.repository.findToolkitById(id);
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final int id = request.getModel().getInteger("id");
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "draftMode");
		
		Double amount = 0.;
		/*final Collection<Item> items = this.repository.findItemsByToolkitId(id);
		for(final Item item: items) {
			if(!item.getRetailPrice().getCurrency().isEmpty()) {
				amount += item.getRetailPrice().getAmount();
			} else {
				amount=0.;
				break;
			}
		}*/
		
		
		final Double foundRetailPriceOfToolkit = this.repository.findRetailPriceByToolkitId(id);
		if(foundRetailPriceOfToolkit != null) amount = foundRetailPriceOfToolkit;
		final String currency = this.repository.findCurrencyByToolkitId(id);
		final Money retailPrice = new Money();
		retailPrice.setAmount(amount);
		retailPrice.setCurrency(currency);
		if(retailPrice.getCurrency() != null) {
			model.setAttribute("retailPrice", retailPrice);
		} else {
			model.setAttribute("retailPrice", 0.0);
		}
		
	}



}
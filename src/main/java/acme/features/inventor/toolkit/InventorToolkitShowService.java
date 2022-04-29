package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
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

		int id;
		final Toolkit toolkit;
		
		id = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(id);
		return !toolkit.isDraftMode() || request.isPrincipal(toolkit.getInventor());
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findOneToolkitById(id);
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int id = request.getModel().getInteger("id");
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "draftMode");
		
		Double amountEUR = 0.;
		final Collection<Item> items = this.repository.findItemsByToolkitId(id);
		for(final Item item : items) {
			if(item.getRetailPrice().getCurrency().equals("EUR")) {
				amountEUR+=item.getRetailPrice().getAmount();
			}
			else {
				amountEUR=0.;
				break;
			}
		}
		
		final Money retailPriceEUR = new Money();
		retailPriceEUR.setAmount(amountEUR);	retailPriceEUR.setCurrency("EUR");
		
		model.setAttribute("retailPrice", retailPriceEUR);
		
	}

}

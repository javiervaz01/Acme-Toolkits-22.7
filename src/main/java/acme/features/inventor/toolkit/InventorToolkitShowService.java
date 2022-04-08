package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int id = request.getModel().getInteger("id");
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link");
		
		final Collection<String> currencies = this.repository.currencies();
		//for(final String currency: currencies) {
		//	final Double amount = this.repository.findRetailPriceByToolkitId(id,currency);
		//}
		
		final Double amountEUR = this.repository.findRetailPriceByToolkitId(id,"EUR");
		//final Double amountUSD = this.repository.findRetailPriceByToolkitId(id,"USD");
		//final Double amountGBP = this.repository.findRetailPriceByToolkitId(id,"GBP");
		
		final Money retailPriceEUR = new Money();
		retailPriceEUR.setAmount(amountEUR);	retailPriceEUR.setCurrency("EUR");
		//final Money retailPriceUSD = null;
		//retailPriceUSD.setAmount(amountUSD);	retailPriceUSD.setCurrency("USD");
		//final Money retailPriceGBP = null;
		//retailPriceGBP.setAmount(amountGBP);	retailPriceGBP.setCurrency("GBP");
		
		model.setAttribute("retailPrice", retailPriceEUR);
		
	}

}

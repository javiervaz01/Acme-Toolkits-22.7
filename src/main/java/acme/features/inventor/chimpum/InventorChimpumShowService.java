package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.chimpums.Chimpum;
import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, Chimpum>{
	
	@Autowired
	InventorChimpumRepository repository;
	
	@Autowired
	protected ExchangeService exchangeService;

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		
		final int inventorId;
		final Chimpum chimpum;
		
		chimpum = this.repository.findOne(request.getModel().getInteger("id"));
		
		inventorId = request.getPrincipal().getActiveRoleId();
		
		
		
		return chimpum.getInventor().getId() == inventorId;
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;
		
		
		return this.repository.findOne(request.getModel().getInteger("id"));
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "creationDate", "title", "description", "startDate","endDate","budget","info");
		
		final Item item = this.repository.findItemByChimpum(entity.getId());
		final String itemName = item.getName();
		model.setAttribute("item", itemName);

		final Money exchange = this.exchangeService.getExchange(entity.getBudget());
		model.setAttribute("exchange", exchange);
		
	}

}

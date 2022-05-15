package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;



@Service
public class InventorPatronageListMineProposedService implements AbstractListService<Inventor, Patronage> {
	
	@Autowired
	protected InventorPatronageRepository repository;
	
	@Autowired
	protected ExchangeService exchangeService;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Patronage> findMany(final Request<Patronage> request) {
		final int id = request.getPrincipal().getActiveRoleId();
		
		Collection<Patronage> patronages;
		
		patronages = this.repository.findProposedPatronagesByInventorId(id);
		
		return patronages;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate", "startDate", "endDate", "info");
		
		Money exchange;
		
		exchange = this.exchangeService.getExchange(entity.getBudget());
		
		model.setAttribute("exchange", exchange);
	}

}

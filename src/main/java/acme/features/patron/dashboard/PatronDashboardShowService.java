package acme.features.patron.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected PatronDashboardRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		return null;
		//TODO
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, 
			"numberOfProposedPatronages","numberOfAcceptedPatronages", 
			"numberOfDeniedPatronages", "stastBudgetofProposedPatronages",
			"stastBudgetofAcceptedPatronages", "stastBudgetofDeniedPatronages");
		
	}
	
	
}

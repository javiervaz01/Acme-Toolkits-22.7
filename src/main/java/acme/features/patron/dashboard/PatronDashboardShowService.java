package acme.features.patron.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Status;
import acme.forms.patron.Dashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronDashboardRepository repository;

	// AbstractShowService<Patron, PatronDashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		
		Dashboard result;
		int numberOfProposedPatronages;
		final int numberOfAcceptedPatronages;
		final int numberOfDeniedPatronages;
		
		numberOfProposedPatronages=this.repository.numberOfPatronages(Status.PROPOSED);
		numberOfAcceptedPatronages=this.repository.numberOfPatronages(Status.ACCEPTED);
		numberOfDeniedPatronages=this.repository.numberOfPatronages(Status.DENIED);
		
		
		List<PatronDashboardItem> statsBudgetofProposedPatronages;
		List<PatronDashboardItem> statsBudgetofAcceptedPatronages;
		List<PatronDashboardItem> statsBudgetofDeniedPatronages;	
		
		
		statsBudgetofProposedPatronages=this.getStatistics(Status.PROPOSED);
		statsBudgetofAcceptedPatronages=this.getStatistics(Status.ACCEPTED);
		statsBudgetofDeniedPatronages=this.getStatistics(Status.DENIED);
		
		result= new Dashboard();
		result.setNumberOfProposedPatronages(numberOfProposedPatronages);
		result.setNumberOfAcceptedPatronages(numberOfAcceptedPatronages);
		result.setNumberOfDeniedPatronages(numberOfDeniedPatronages);
		result.setStatsBudgetofAcceptedPatronages(statsBudgetofAcceptedPatronages);
		result.setStatsBudgetofProposedPatronages(statsBudgetofProposedPatronages);
		result.setStatsBudgetofDeniedPatronages(statsBudgetofDeniedPatronages);
		
		return result;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberOfProposedPatronages", "numberOfAcceptedPatronages", "numberOfDeniedPatronages", "statsBudgetofProposedPatronages", "statsBudgetofAcceptedPatronages", "statsBudgetofDeniedPatronages");
		
	}	
	
	
	private List<PatronDashboardItem> getStatistics(final Status status){
		
		final List<String> currencies= (List<String>) this.repository.currencies();
		final List<PatronDashboardItem> res = new ArrayList<PatronDashboardItem>();
		
		for(int i=0;i<currencies.size();i++) {
			final String currency = currencies.get(i);
			
			final PatronDashboardItem itemStats = new PatronDashboardItem();
			itemStats.currency=currency;
			itemStats.average= this.repository.averagePatronage(status, currency);
			itemStats.deviation= this.repository.deviationPatronage(status, currency);
			itemStats.min=this.repository.minimunPatronage(status, currency);
			itemStats.max=this.repository.maximunPatronage(status, currency);
			
			res.add(itemStats);
		}
		
		return res;
		
	}

}

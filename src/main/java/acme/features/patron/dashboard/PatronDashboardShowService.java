package acme.features.patron.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Status;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronDashboardRepository repository;

	// AbstractShowService<Patron, PatronDashboard> interface ----------------


	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;
		
		PatronDashboard result;
		int numberOfProposedPatronages;
		int numberOfAcceptedPatronages;
		int numberOfDeniedPatronages;
		
		numberOfProposedPatronages=this.repository.numberOfProposedPatronages();
		numberOfAcceptedPatronages=this.repository.numberOfAcceptedPatronages();
		numberOfDeniedPatronages=this.repository.numberOfDeniedPatronages();
		
		
		List<Map<String, Double>> stastBudgetofProposedPatronages;
		List<Map<String, Double>> stastBudgetofAcceptedPatronages;
		List<Map<String, Double>> statsBudgetofDeniedPatronages;	
		
		
		stastBudgetofProposedPatronages=this.getListStatistics(Status.PROPOSED);
		stastBudgetofAcceptedPatronages=this.getListStatistics(Status.ACCEPTED);
		statsBudgetofDeniedPatronages=this.getListStatistics(Status.DENIED);
		
		result= new PatronDashboard();
		result.setNumberOfProposedPatronages(numberOfProposedPatronages);
		result.setNumberOfAcceptedPatronages(numberOfAcceptedPatronages);
		result.setNumberOfDeniedPatronages(numberOfDeniedPatronages);
		result.setStastBudgetofAcceptedPatronages(stastBudgetofAcceptedPatronages);
		result.setStastBudgetofProposedPatronages(stastBudgetofProposedPatronages);
		result.setStastBudgetofDeniedPatronages(statsBudgetofDeniedPatronages);
		
		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"averageNumberOfJobsPerEmployer", "averageNumberOfApplicationsPerWorker", // 
			"avegageNumberOfApplicationsPerEmployer", "ratioOfPendingApplications", //
			"ratioOfRejectedApplications", "ratioOfAcceptedApplications");
	}
	
	
	private List<Map<String, Double>> getListStatistics(final Status status){
		final HashMap<String, Double> averages= new HashMap<String, Double>();
		final HashMap<String, Double> deviations= new HashMap<String, Double>();
		final HashMap<String, Double> minimuns=new HashMap<String, Double>();
		final HashMap<String, Double> maximuns=new HashMap<String, Double>(); 
		
		final List<String> currencies= (List<String>) this.repository.currencies();
		for(int i=0;i<currencies.size();i++) {
			final String coin=currencies.get(i);
			
			final double average = this.repository.averagePatronage(status, coin);
			averages.put(coin, average);
			
			final double deviation = this.repository.deviationPatronage(status, coin);
			deviations.put(coin, deviation);
			
			final double min=this.repository.minimunPatronage(status, coin);
			minimuns.put(coin, min);
			
			final double max=this.repository.maximunPatronage(status, currencies.get(i));
			maximuns.put(coin, max);
			
		}
		
		final List<Map<String, Double>> res=new ArrayList<Map<String,Double>>();
		res.add(averages);
		res.add(deviations);
		res.add(minimuns);
		res.add(maximuns);
		return res;
		
		
	}

}

package acme.features.patron.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		
		List<Map<String, Double>> statsBudgetofProposedPatronages;
		List<Map<String, Double>> statsBudgetofAcceptedPatronages;
		List<Map<String, Double>> statsBudgetofDeniedPatronages;	
		
		
		statsBudgetofProposedPatronages=this.getStatistics(Status.PROPOSED);
		statsBudgetofAcceptedPatronages=this.getStatistics(Status.ACCEPTED);
		statsBudgetofDeniedPatronages=this.getStatistics(Status.DENIED);
		
		result= new Dashboard();
		result.setNumberOfProposedPatronages(numberOfProposedPatronages);
		result.setNumberOfAcceptedPatronages(numberOfAcceptedPatronages);
		result.setNumberOfDeniedPatronages(numberOfDeniedPatronages);
		result.setStastBudgetofAcceptedPatronages(statsBudgetofAcceptedPatronages);
		result.setStastBudgetofProposedPatronages(statsBudgetofProposedPatronages);
		result.setStastBudgetofDeniedPatronages(statsBudgetofDeniedPatronages);
		
		return result;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberOfProposedPatronages", "numberOfAcceptedPatronages", "numberOfDeniedPatronages");
		
		final List<PatronDashboardItem> proposedItems = this.getStatisticsList(entity.getStastBudgetofProposedPatronages());
		model.setAttribute("proposedItems", proposedItems);

		final List<PatronDashboardItem> acceptedItems = this.getStatisticsList(entity.getStastBudgetofAcceptedPatronages());
		model.setAttribute("acceptedItems", acceptedItems);
		
		final List<PatronDashboardItem> deniedItems = this.getStatisticsList(entity.getStastBudgetofDeniedPatronages());
		model.setAttribute("deniedItems", deniedItems);
	}
	
	
	private List<PatronDashboardItem> getStatisticsList(final List<Map<String,Double>> statsOfPatronages){
		//List with the currencies in an specific order
		final List<String> currencies= new ArrayList<String>();
		currencies.addAll(statsOfPatronages.get(0).keySet());
		
		final List<PatronDashboardItem> res= new ArrayList<PatronDashboardItem>();
		
		final Map<String,Double> averageMap = statsOfPatronages.get(0);
		final Map<String,Double> deviationMap = statsOfPatronages.get(1);
		final Map<String,Double> minMap = statsOfPatronages.get(2);
		final Map<String,Double> maxMap = statsOfPatronages.get(3);
		
		
		for(int i=0; i<currencies.size();i++) {
			final String currency=currencies.get(i);
			final PatronDashboardItem item = new PatronDashboardItem();
			
			item.currency=currency;
			item.average=averageMap.get(currency);
			item.deviation=deviationMap.get(currency);
			item.min=minMap.get(currency);
			item.max=maxMap.get(currency);
			
			res.add(item);
		}
		
		return res;
	}
	
	
	
	
	private List<Map<String, Double>> getStatistics(final Status status){
		final HashMap<String, Double> averages= new HashMap<String, Double>();
		final HashMap<String, Double> deviations= new HashMap<String, Double>();
		final HashMap<String, Double> minimums=new HashMap<String, Double>();
		final HashMap<String, Double> maximums=new HashMap<String, Double>(); 
		
		final List<String> currencies= (List<String>) this.repository.currencies();
		for(int i=0;i<currencies.size();i++) {
			final String coin=currencies.get(i);
			
			final Double average = this.repository.averagePatronage(status, coin);
			averages.put(coin, average);
			
			final Double deviation = this.repository.deviationPatronage(status, coin);
			deviations.put(coin, deviation);
			
			final Double min=this.repository.minimunPatronage(status, coin);
			minimums.put(coin, min);
			
			final Double max=this.repository.maximunPatronage(status, currencies.get(i));
			maximums.put(coin, max);
			
		}
		
		final List<Map<String, Double>> res=new ArrayList<Map<String,Double>>();
		res.add(averages);
		res.add(deviations);
		res.add(minimums);
		res.add(maximums);
		return res;
		
		
	}

}

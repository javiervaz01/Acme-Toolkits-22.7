package acme.features.patron.patrondashboard;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.patronages.Status;
import acme.forms.patron.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronDashboardRepository repository;

	@Autowired
	protected ExchangeService exchangeService;
	
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
		
		result= new PatronDashboard();
		result.setNumberOfProposedPatronages(numberOfProposedPatronages);
		result.setNumberOfAcceptedPatronages(numberOfAcceptedPatronages);
		result.setNumberOfDeniedPatronages(numberOfDeniedPatronages);
		result.setStatsBudgetofAcceptedPatronages(statsBudgetofAcceptedPatronages);
		result.setStatsBudgetofProposedPatronages(statsBudgetofProposedPatronages);
		result.setStatsBudgetofDeniedPatronages(statsBudgetofDeniedPatronages);
		
		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberOfProposedPatronages", "numberOfAcceptedPatronages", "numberOfDeniedPatronages", "statsBudgetofProposedPatronages", "statsBudgetofAcceptedPatronages", "statsBudgetofDeniedPatronages");
	}	
	
	
	private List<PatronDashboardItem> getStatistics(final Status status){
		return this.repository.currencies().stream().map(currency -> this.itemStats(status, currency)).collect(Collectors.toList());
	}
	
	private PatronDashboardItem itemStats(final Status status, final String currency) {
		final PatronDashboardItem itemStats = new PatronDashboardItem();
		itemStats.currency = currency;
		itemStats.average = this.repository.averagePatronage(status, currency);
		itemStats.exchangeAverage = this.getExchange(itemStats.average, currency);
		itemStats.deviation = this.repository.deviationPatronage(status, currency);
		itemStats.min = this.repository.minimumPatronage(status, currency);
		itemStats.exchangeMin= this.getExchange(itemStats.min, currency);
		itemStats.max = this.repository.maximumPatronage(status, currency);
		itemStats.exchangeMax = this.getExchange(itemStats.max, currency);
		return itemStats;
	}
	
	private String getExchange(final Double amount, final String Currency) {
		final Money aux= new Money();
		aux.setAmount(amount);
		aux.setCurrency(Currency);
		
		final Money exchange=this.exchangeService.getExchange(aux);
		final Double roundedAmount= Math.round(exchange.getAmount()*100.0)/100.0;
		
		return exchange.getCurrency()+" "+roundedAmount;
	}
}

package acme.features.administrator.administratordashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.ExchangeService;
import acme.entities.patronages.Status;
import acme.forms.administrator.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorAdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorAdministratorDashboardRepository repository;
	
	@Autowired
	protected ExchangeService exchangeService;
	

	// AbstractShowService<Patron, PatronDashboard> interface ----------------


	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;
		
		AdministratorDashboard result;
		final int numberOfProposedPatronages;
		final int numberOfAcceptedPatronages;
		final int numberOfDeniedPatronages;
		
		final int numberOfTools;
		
		final int numberOfComponents;
		
		numberOfProposedPatronages=this.repository.numberOfPatronages(Status.PROPOSED);
		numberOfAcceptedPatronages=this.repository.numberOfPatronages(Status.ACCEPTED);
		numberOfDeniedPatronages=this.repository.numberOfPatronages(Status.DENIED);
		
		numberOfTools = this.repository.numberOfTools();
		
		numberOfComponents = this.repository.numberOfComponents();
		
		
		List<AdministratorDashboardItem> statsBudgetofProposedPatronages;
		List<AdministratorDashboardItem> statsBudgetofAcceptedPatronages;
		List<AdministratorDashboardItem> statsBudgetofDeniedPatronages;	
		
		List<AdministratorDashboardItem> statsRetailPriceofTools;
		
		List<AdministratorDashboardComponentItem> statsRetailPriceofComponents;
		
		
		statsBudgetofProposedPatronages=this.getStatisticsOfPatronages(Status.PROPOSED);
		statsBudgetofAcceptedPatronages=this.getStatisticsOfPatronages(Status.ACCEPTED);
		statsBudgetofDeniedPatronages=this.getStatisticsOfPatronages(Status.DENIED);
		
		statsRetailPriceofTools=this.getStatisticsOfTools();
		
		statsRetailPriceofComponents=this.getStatisticsOfComponents();
		
		result= new AdministratorDashboard();
		result.setNumberOfProposedPatronages(numberOfProposedPatronages);
		result.setNumberOfAcceptedPatronages(numberOfAcceptedPatronages);
		result.setNumberOfDeniedPatronages(numberOfDeniedPatronages);
		result.setStatsBudgetofAcceptedPatronages(statsBudgetofAcceptedPatronages);
		result.setStatsBudgetofProposedPatronages(statsBudgetofProposedPatronages);
		result.setStatsBudgetofDeniedPatronages(statsBudgetofDeniedPatronages);
		
		result.setNumberOfTools(numberOfTools);
		result.setStatsRetailPriceOfTools(statsRetailPriceofTools);
		
		result.setNumberOfComponents(numberOfComponents);
		result.setStatsRetailPriceOfComponents(statsRetailPriceofComponents);
		
		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberOfProposedPatronages", "numberOfAcceptedPatronages", "numberOfDeniedPatronages", "statsBudgetofProposedPatronages", "statsBudgetofAcceptedPatronages", "statsBudgetofDeniedPatronages","numberOfTools","statsRetailPriceOfTools","numberOfComponents","statsRetailPriceOfComponents");
		
	}	
	
	
	private List<AdministratorDashboardItem> getStatisticsOfPatronages(final Status status){
		
		final List<Object> currencies= this.exchangeService.getAceptedCurrencyList();
		final List<AdministratorDashboardItem> res = new ArrayList<AdministratorDashboardItem>();
		
		for(int i=0;i<currencies.size();i++) {
			final String currency = (String) currencies.get(i);
			
			final AdministratorDashboardItem itemStats = new AdministratorDashboardItem();
			itemStats.currency = currency;
			itemStats.average = this.repository.averagePatronage(status, currency);
			itemStats.exchangeAverage = this.getExchange(itemStats.average, currency);
			itemStats.deviation = this.repository.deviationPatronage(status, currency);
			itemStats.min = this.repository.minimumPatronage(status, currency);
			itemStats.exchangeMin= this.getExchange(itemStats.min, currency);
			itemStats.max = this.repository.maximumPatronage(status, currency);
			itemStats.exchangeMax = this.getExchange(itemStats.max, currency);
	
			res.add(itemStats);
		}
		
		return res;
		
	}
	
	private List<AdministratorDashboardItem> getStatisticsOfTools(){
		
		final List<Object> currencies= this.exchangeService.getAceptedCurrencyList();
		final List<AdministratorDashboardItem> res = new ArrayList<AdministratorDashboardItem>();
		
		for(int i=0;i<currencies.size();i++) {
			final String currency = (String) currencies.get(i);
			
			final AdministratorDashboardItem itemStats = new AdministratorDashboardItem();
			itemStats.currency=currency;
			itemStats.average= this.repository.averageRetailPriceOfTools(currency);
			itemStats.exchangeAverage = this.getExchange(itemStats.average, currency);
			itemStats.deviation= this.repository.deviationRetailPriceOfTools(currency);
			itemStats.min=this.repository.minimumRetailPriceOfTools(currency);
			itemStats.exchangeMin= this.getExchange(itemStats.min, currency);
			itemStats.max=this.repository.maximumRetailPriceOfTools(currency);
			itemStats.exchangeMax = this.getExchange(itemStats.max, currency);
			
			res.add(itemStats);
		}
		
		return res;
		
	}
	
	private List<AdministratorDashboardComponentItem> getStatisticsOfComponents(){
		
		final List<Object> currencies= this.exchangeService.getAceptedCurrencyList();
		final List<AdministratorDashboardComponentItem> res = new ArrayList<AdministratorDashboardComponentItem>();
		
		final List<String> technologies= (List<String>) this.repository.technologies();
		
		for(int j = 0;j<technologies.size();j++) {
			final String technology = technologies.get(j);
		
		
			for(int i=0;i<currencies.size();i++) {
				final String currency = (String) currencies.get(i);
				
				final AdministratorDashboardComponentItem itemStats = new AdministratorDashboardComponentItem();
				itemStats.technology = technology;
				itemStats.currency=currency;
				itemStats.average= this.repository.averageRetailPriceOfComponents(currency,technology);
				itemStats.exchangeAverage = this.getExchange(itemStats.average, currency);
				itemStats.deviation= this.repository.deviationRetailPriceOfComponents(currency,technology);
				itemStats.min=this.repository.minumumRetailPriceOfComponents(currency,technology);
				itemStats.exchangeMin= this.getExchange(itemStats.min, currency);
				itemStats.max=this.repository.maximumRetailPriceOfComponents(currency,technology);
				itemStats.exchangeMax = this.getExchange(itemStats.max, currency);
				
				res.add(itemStats);
			}
		}
		return res;
		
	}
	
	private String getExchange(final Double amount, final String Currency) {
		final Money source= new Money();
		source.setAmount(amount);
		source.setCurrency(Currency);
		if(source.getAmount()==null || source.getCurrency()==null) {
			return null;
		}else {
			final Money exchange=this.exchangeService.getExchange(source);
			final Double roundedAmount= Math.round(exchange.getAmount()*100.0)/100.0;
			return exchange.getCurrency()+" "+roundedAmount;
		}	
	}
	

}

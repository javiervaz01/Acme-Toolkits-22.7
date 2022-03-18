/*
 * The system must handle administrator dashboards with the following indicators:
 * 
 * total number of components;
 * average, deviation, minimum, and maximum retail price of components, grouped by technology and currency;
 * total number of tools;
 * average, deviation, minimum, and maximum retail price of tools, grouped by currency;
 * total number of proposed/accepted/denied patronages;
 * aver-age, deviation, minimum, and maximum budget of proposed/accepted/denied patronages.
 */

package acme.forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	/* TODO: group average, deviation, minimum, maximum in
	 * a single attribute in each case, so
	 * we have 1 attribute instead of four.
	 * */
	
	int numberOfComponents;
	// We could also do it the other way around. 
	// Group by technology (key), whose value is
	// a Map for which I have the average of each currency.

	// POSSIBILITIES:
	
	// Map<String, Map<String,Double>>
	// {"Java":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Python:{"THB":76.00,"AUS":12.00}}
	
	// Map<Pair<String, String>, Double>
	// {(Java, THB):1000.00, (Python, AUS):12.00, (Java, AUS):302.00} 
	// We would have to use an OrderedMap in that case, so the data is always displayed
	// the same way to the final user in the view
	
	Map<String, Map<String,Double>> averageRetailPriceOfComponentsPerTechnology;
	Map<String, Map<String,Double>> deviationRetailPriceOfComponentsPerTechnology;
	Map<String, Map<String,Double>> minumumRetailPriceOfComponentsPerTechnology;
	Map<String, Map<String,Double>> maximumRetailPriceOfComponentsPerTechnology;
	
	// Map<String, Map<String,List<Double>>> statsOfComponentsPerTechnology;

	// IDEA:
		//		Map<String, Double>
		//      {"EUR": 28.39, "GBP: 102.00", "USD":0.01}
	// We have to round to 2 decimal places.
	
	Map<String,Double> averageRetailPriceOfComponentsPerCurrency;
	Map<String,Double> deviationRetailPriceOfComponentsPerCurrency;
	Map<String,Double> minumumRetailPriceOfComponentsPerCurrency;
	Map<String,Double> maximumRetailPriceOfComponentsPerCurrency;
	
	int numberOfTools;
	
	// Same as averageRetailPriceOfComponentsPerTechnology and such, but grouping by money.currency
	Map<String, Map<String,Double>> averageRetailPriceOfToolsPerCurrency;
	Map<String, Map<String,Double>> deviationRetailPriceOfToolsPerCurrency;
	Map<String, Map<String,Double>> minumumRetailPriceOfToolsPerCurrency;
	Map<String, Map<String,Double>> maximumRetailPriceOfToolsPerCurrency;
	
	
	int numberOfProposedPatronages;
	int numberOfAcceptedPatronages;
	int numberOfDeniedPatronages;
	
	// TODO: this is all grouped by currency I think he said.
	// we can't mix currencies in the statistics so we need to
	// group by EUR, USD, THB...
	
	// TODO: maybe use ...groupBy category instead of having three
	// attributes which are exactly the same? So we could debloat this
	// class when we have to use it
	
	// IDEA:
	//		Map<String, Double>
	//      {"EUR": 28.39, "GBP: 102.00", "USD":0.01}
	// We have to round to 2 decimal places.
	
	Map<String, Double> averageBudgetOfProposedPatronages;
	Map<String, Double> deviationBudgetOfProposedPatronages;
	Map<String, Double> minumumBudgetOfProposedPatronages;
	Map<String, Double> maximumBudgetOfProposedPatronages;
	
	Map<String, Double> averageBudgetOfAcceptedPatronages;
	Map<String, Double> deviationBudgetOfAcceptedPatronages;
	Map<String, Double> minumumBudgetOfAcceptedPatronages;
	Map<String, Double> maximumBudgetOfAcceptedPatronages;
	
	Map<String, Double> averageBudgetOfDeniedPatronages;
	Map<String, Double> deviationBudgetOfDeniedPatronages;
	Map<String, Double> minumumBudgetOfDeniedPatronages;
	Map<String, Double> maximumBudgetOfDeniedPatronages;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}

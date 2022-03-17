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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Double numberOfComponents;
	
	Double averageRetailPriceOfComponentsPerTechnology;
	Double deviationRetailPriceOfComponentsPerTechnology;
	Double minumumRetailPriceOfComponentsPerTechnology;
	Double maximumRetailPriceOfComponentsPerTechnology;
	
	Double averageRetailPriceOfComponentsPerCurrency;
	Double deviationRetailPriceOfComponentsPerCurrency;
	Double minumumRetailPriceOfComponentsPerCurrency;
	Double maximumRetailPriceOfComponentsPerCurrency;
	
	
	Double numberOfTools;
	
	Double averageRetailPriceOfToolsPerCurrency;
	Double deviationRetailPriceOfToolsPerCurrency;
	Double minumumRetailPriceOfToolsPerCurrency;
	Double maximumRetailPriceOfToolsPerCurrency;
	
	
	Double numberOfProposedPatronages;
	Double numberOfAcceptedPatronages;
	Double numberOfDeniedPatronages;
	
	
	Double averageBudgetOfProposedPatronages;
	Double deviationBudgetOfProposedPatronages;
	Double minumumBudgetOfProposedPatronages;
	Double maximumBudgetOfProposedPatronages;
	
	Double averageBudgetOfAcceptedPatronages;
	Double deviationBudgetOfAcceptedPatronages;
	Double minumumBudgetOfAcceptedPatronages;
	Double maximumBudgetOfAcceptedPatronages;
	
	Double averageBudgetOfDeniedPatronages;
	Double deviationBudgetOfDeniedPatronages;
	Double minumumBudgetOfDeniedPatronages;
	Double maximumBudgetOfDeniedPatronages;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}

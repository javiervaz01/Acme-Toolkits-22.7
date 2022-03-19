package acme.forms;

import java.io.Serializable;
import java.util.Map;

public class PatronDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	/* TODO: group average, deviation, minimum, maximum in
	 * a single attribute in each case, so
	 * we have 1 attribute instead of four. */
	
	/* TODO: Use the appropiate datatypes for the query returns,
	 * following the examples in AdministratorDashboard
	 * */
	
	int							numberOfProposedPatronages;
	int							numberOfAcceptedPatronages;
	int							numberOfDeniedPatronages;
	
	// TODO: maybe use ...groupBy category instead of having three
	// attributes which are exactly the same? So we could debloat this
	// class when we have to use it
	
	Map<String,Double>						averageBudgetofProposedPatronages;
	Map<String,Double>						deviationBudgetofProposedPatronages;
	Map<String,Double>						maximumBudgetofProposedPatronages;
	Map<String,Double>						minimumBudgetofProposedPatronages;
	
	Map<String,Double>						averageBudgetofAcceptedPatronages;
	Map<String,Double>						deviationBudgetofAcceptedPatronages;
	Map<String,Double>						maximumBudgetofAcceptedPatronages;
	Map<String,Double>						minimumBudgetofAcceptedPatronages;
	
	Map<String,Double>						averageBudgetofDeniedPatronages;
	Map<String,Double>						deviationBudgetofDeniedPatronages;
	Map<String,Double>						maximumBudgetofDeniedPatronages;
	Map<String,Double>						minimumBudgetofDeniedPatronages;


	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}

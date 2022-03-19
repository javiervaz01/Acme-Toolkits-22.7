package acme.forms;

import java.io.Serializable;

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
	
	Double						averageBudgetofProposedPatronages;
	Double						deviationBudgetofProposedPatronages;
	Double						maximumBudgetofProposedPatronages;
	Double						minimumBudgetofProposedPatronages;
	
	Double						averageBudgetofAcceptedPatronages;
	Double						deviationBudgetofAcceptedPatronages;
	Double						maximumBudgetofAcceptedPatronages;
	Double						minimumBudgetofAcceptedPatronages;
	
	Double						averageBudgetofDeniedPatronages;
	Double						deviationBudgetofDeniedPatronages;
	Double						maximumBudgetofDeniedPatronages;
	Double						minimumBudgetofDeniedPatronages;


	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}

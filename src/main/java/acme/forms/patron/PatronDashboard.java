package acme.forms.patron;

import java.io.Serializable;
import java.util.List;

import acme.features.patron.patrondashboard.PatronDashboardItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	int numberOfProposedPatronages;
	int numberOfAcceptedPatronages;
	int numberOfDeniedPatronages;

	// [
	// 	{"THB":1000.00,"CAD":247.09,"AUS":302.00}, ---> Average
	// 	{"THB":1000.00,"CAD":247.09,"AUS":302.00}, --> Deviation
	// 	{"THB":1000.00,"CAD":247.09,"AUS":302.00}, ---> Min
	// 	{"THB":1000.00,"CAD":247.09,"AUS":302.00} ---> Max
	// ]
	// We have to round to 2 decimal places.
	
	List<PatronDashboardItem> statsBudgetofProposedPatronages;
	List<PatronDashboardItem> statsBudgetofAcceptedPatronages;
	List<PatronDashboardItem> statsBudgetofDeniedPatronages;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}

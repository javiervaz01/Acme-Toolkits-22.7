package acme.forms;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PatronDashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	int	numberOfProposedPatronages;
	int	numberOfAcceptedPatronages;
	int	numberOfDeniedPatronages;
	
	// [
	//	{"WhatsApp 2":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Awesome Computer":{"THB":76.00,"AUS":12.00}}, ---> Average
	//	{"WhatsApp 2":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Awesome Computer":{"THB":76.00,"AUS":12.00}}, ---> Deviation
	//	{"WhatsApp 2":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Awesome Computer":{"THB":76.00,"AUS":12.00}}, ---> Min
	//	{"WhatsApp 2":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Awesome Computer":{"THB":76.00,"AUS":12.00}}  ---> Max
	// ]
	// We have to round to 2 decimal places.
	
	List<Map<String,Double>> stastBudgetofProposedPatronages;
	List<Map<String,Double>> stastBudgetofAcceptedPatronages;
	List<Map<String,Double>> stastBudgetofDeniedPatronages;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}

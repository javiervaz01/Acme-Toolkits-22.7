package acme.forms;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
	

	// Map Key=Currency, Value=[Average, Deviation, Min, Max]
	//
	//	{"THB":[1000.00, 500.00, 247.09, 2000.00],
	//	"CAD":[1000.00, 500.00, 247.09, 2000.00],
	//	"AUS":[1000.00, 500.00, 247.09, 2000.00]}
	//
	// We have to round to 2 decimal places.
	
	Map<String, List<Double>> stastBudgetofProposedPatronages;
	Map<String, List<Double>> stastBudgetofAcceptedPatronages;
	Map<String, List<Double>> stastBudgetofDeniedPatronages;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}

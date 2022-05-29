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

package acme.forms.administrator;

import java.io.Serializable;
import java.util.List;

import acme.features.administrator.administratordashboard.AdministratorDashboardChimpum;
import acme.features.administrator.administratordashboard.AdministratorDashboardComponentItem;
import acme.features.administrator.administratordashboard.AdministratorDashboardItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	int numberOfComponents;
	
	// [
	//	{"Java":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Python":{"THB":76.00,"AUS":12.00}}, ---> Average
	//	{"Java":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Python":{"THB":76.00,"AUS":12.00}}, ---> Deviation
	//	{"Java":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Python":{"THB":76.00,"AUS":12.00}}, ---> Min
	//	{"Java":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Python":{"THB":76.00,"AUS":12.00}}  ---> Max
	// ]
	
	List<AdministratorDashboardComponentItem> statsRetailPriceOfComponents;
	
	int numberOfTools;
	
	// [
	//	{"Hammer":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Saw":{"THB":76.00,"AUS":12.00}}, ---> Average
	//	{"Hammer":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Saw":{"THB":76.00,"AUS":12.00}}, ---> Deviation
	//	{"Hammer":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Saw":{"THB":76.00,"AUS":12.00}}, ---> Min
	//	{"Hammer":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Saw":{"THB":76.00,"AUS":12.00}}  ---> Max
	// ]
	
	List<AdministratorDashboardItem> statsRetailPriceOfTools;
	
	int numberOfProposedPatronages;
	int numberOfAcceptedPatronages;
	int numberOfDeniedPatronages;
		
	// [
	//	{"WhatsApp 2":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Awesome Computer":{"THB":76.00,"AUS":12.00}}, ---> Average
	//	{"WhatsApp 2":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Awesome Computer":{"THB":76.00,"AUS":12.00}}, ---> Deviation
	//	{"WhatsApp 2":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Awesome Computer":{"THB":76.00,"AUS":12.00}}, ---> Min
	//	{"WhatsApp 2":{"THB":1000.00,"CAD":247.09,"AUS":302.00},"Awesome Computer":{"THB":76.00,"AUS":12.00}}  ---> Max
	// ]
	// We have to round to 2 decimal places.
	List<AdministratorDashboardItem> statsBudgetofProposedPatronages;
	List<AdministratorDashboardItem> statsBudgetofAcceptedPatronages;
	List<AdministratorDashboardItem> statsBudgetofDeniedPatronages;

	
	double ratioOfChimpums;
	List<AdministratorDashboardChimpum> statsChimpums;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}

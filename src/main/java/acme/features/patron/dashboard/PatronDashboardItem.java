package acme.features.patron.dashboard;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboardItem {

	public String currency;
	
	public Double average;
	
	public Double deviation;
	
	public Double min;
	
	public Double max;
	
}

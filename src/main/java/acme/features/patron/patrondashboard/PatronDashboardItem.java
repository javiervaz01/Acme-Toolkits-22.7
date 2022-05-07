package acme.features.patron.patrondashboard;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboardItem {

	public String currency;
	
	public Double average;
	
	public String exchangeAverage;
	
	public Double deviation;
	
	public Double min;
	
	public String exchangeMin;
	
	public Double max;
	
	public String exchangeMax;
	
}

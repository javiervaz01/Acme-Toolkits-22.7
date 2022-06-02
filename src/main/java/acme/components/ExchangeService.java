package acme.components;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.framework.datatypes.Money;

@Service
public class ExchangeService {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ExchangeRepository repository;

	public Money getExchange(final Money budget) {

		final String systemCurrency = this.repository.getSystemConfiguration().getCurrency();

		return budget.getCurrency().equals(systemCurrency) ? budget : this.getInSystemCurrency(budget);
	}
	
	public List<Object> getAceptedCurrencyList(){
		final String[] coins= this.repository.getSystemConfiguration().getAcceptedCurrencies().split(",");
		return Arrays.asList(coins);
	}

	private Money getInSystemCurrency(final Money budget) {
		final String sourceCurrency = budget.getCurrency();
		final String targetCurrency = this.repository.getSystemConfiguration().getCurrency();
		final Double sourceAmount = budget.getAmount();
		final Double targetAmount;

		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);

		final Date oneDayAgo = calendar.getTime();

		Double rate = this.repository.getRecentExchangeRate(oneDayAgo, sourceCurrency, targetCurrency);
		
		if(sourceAmount == null) {
			return null;
		}
		
		if (rate == null) { // If there is no recent exchange
			// Delete exchange rates that are not considered recent anymore, in case they
			// exist, to prevent the database from storing useless data as the time goes by
			final ExchangeCache oldExchange =this.repository.findOneExchangeRate(sourceCurrency, targetCurrency);
			if (oldExchange != null) this.repository.delete(oldExchange);
			
			// And now call the API for requesting (and caching) a fresh exchange rate
			rate = this.getCurrencyFromAPI(sourceCurrency, targetCurrency);
		}

		final Money result = new Money();
		targetAmount = rate * sourceAmount;
		result.setAmount(targetAmount);
		result.setCurrency(targetCurrency);

		return result;
	}

	private double getCurrencyFromAPI(final String sourceCurrency, final String targetCurrency) {
		final RestTemplate api;
		final ExchangeRate record;
		double result;

		try {
			api = new RestTemplate();

			record = api.getForObject("https://api.exchangerate.host/latest?base={0}&symbols={1}", ExchangeRate.class,
					sourceCurrency, targetCurrency);

			assert record != null;

			result = record.getRates().get(targetCurrency);

			// Save the performed exchange in the cache for future requests
			final ExchangeCache cache = new ExchangeCache();
			final Date now = Calendar.getInstance().getTime();
			cache.setDate(now);
			cache.setRate(result);
			cache.setSourceCurrency(sourceCurrency);
			cache.setTargetCurrency(targetCurrency);
			this.repository.save(cache);

		} catch (final Throwable oops) {
			result = 0;
		}

		return result;
	}

}
